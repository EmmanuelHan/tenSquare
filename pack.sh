#!/bin/bash
LANG=C
>/tmp/cdmone-director.log

MY_SHELL_PATH="$(dirname $0)"
MY_SHELL_ABS_PATH="$(cd $MY_SHELL_PATH; pwd )"
MY_SHELL_NAME="$(basename $0)"
FRESH_INSTALL=0
repo_path=${MY_SHELL_ABS_PATH}/repo
. /root/.bash_profile


function log(){
    logfname=/tmp/cdmone-director.log
    datetime=`date +"%y-%m-%d %H:%M:%S"`
    message="$1"
    if [ $2 == "INFO" ]; then
        loglevel="INFO"
        echo "${datetime} ${loglevel} : ${message}"
        echo "${datetime} ${loglevel} : ${message}" >> "${logfname}"
    fi
    if [ $2 == "WARN" ]; then
        loglevel="$2"
        echo -e "\033[33m${datetime} ${loglevel} : ${message}\033[0m"
        echo -e "\033[33m${datetime} ${loglevel} : ${message}\033[0m" >> "${logfname}"
    fi
    if [ $2 == "ERROR" ]; then
        loglevel="$2"
        echo -e "\033[31m${datetime} ${loglevel} : ${message}\033[0m"
        echo -e "\033[31m${datetime} ${loglevel} : ${message}\033[0m" >> "${logfname}"
    fi

}

log_error()
{
    log "$1" "ERROR"
}

log_info()
{
    log "$1" "INFO"
}

log_warn()
{
    log "$1" "WARN"
}

JAVAPATH=$(which java)

if [ -z $JAVAPATH ];then
    log_error "Could not find jre/jdk, plz install it first"
    exit 2
fi

check_return_code()
{
    retcode="$1"
    action="$2"
    msg="$3"
    if [ "${retcode}" != "0" ]; then
        log_error "${action} failed! Error : ${msg}"
        exit 2
    else
        log_info "${action} succeed"
    fi
}


chk_rc_noexit()
{
    retcode="$1"
    action="$2"
    msg="$3"
    if [ "${retcode}" != "0" ]; then
        log_error "${action} failed! Error : ${msg}"
    else
        log_info "${action} succeed"
    fi
}

install_rpm()
{
    rpmname=$1
    filename=$2

    existed=$(rpmquery -a | grep ${rpmname})
    if [ "${existed}" == "" ]; then
        msg=$(rpm -i ${filename} 2>&1)
        check_return_code $? "Install ${rpmname}" "${msg}"
    else
        log_info "Rpm ${rpmname} is already installed!"
    fi
    return 0
}

yum_rpm()
{
    rpmname=$@
    existed=$(rpmquery $1 | grep -w  "is not installed")
    if [ "${existed}" != "" ]; then
        if [ -z $CDMONE_USE_LOCAL_ISO ];then
            msg=$(yum install -y --disablerepo=\* --enablerepo=cdmone-director  ${rpmname} 2>&1)
            check_return_code $? "Install ${rpmname}" "${msg}"
        else
            msg=$(yum install -y   ${rpmname} 2>&1)
            check_return_code $? "Install ${rpmname}" "${msg}"
        fi
    else
        log_info "Rpm ${rpmname} is already installed!"
    fi
    return 0
}

install_repo()
{
    msg=$(cat  >/etc/yum.repos.d/cdmone-director.repo <<EOF
[cdmone-director]
name=cdmone-director
baseurl=file://${repo_path}
enabled=1
gpgcheck=0
EOF
)
    check_return_code $? "Preparing cdmone-director repo:" "${msg}"

}

install_deb()
{
    debname=$1
    filename=$2

    existed=$(dpkg -l | awk '{print $2}' | awk -F : '{print $1}' | grep -w "^$debname$" 2>&1)
    if [ "${existed}" == "" ]; then
        msg=$(dpkg -i ${filename} 2>&1)
        check_return_code $? "Install ${debname}" "${msg}"
    else
        log_info "${debname} is already installed!"
    fi
    return 0
}


install_rabbitmq()
{
    existed=$(rpm -qa | grep -i "rabbitmq-server")
    if [ "${existed}" == "" ];then

        yum_rpm rabbitmq-server

        msg=$(rabbitmq-plugins enable rabbitmq_management)
        check_return_code $? "Enabling rabbitmq_management:" "${msg}"
        msg=$(systemctl restart rabbitmq-server.service)
        check_return_code $? "Restarting rabbitmq-server:" "${msg}"

        msg=$(rabbitmqctl add_user  root  root123@eCloud)
        check_return_code $? "Adding rabbitmq-server root user:" "${msg}"

        msg=$(rabbitmqctl set_user_tags  root administrator)
        check_return_code $? "Adding rabbitmq-server root tags:" "${msg}"

        msg=$(rabbitmqctl set_permissions  -p / root ".*" ".*" ".*")
        check_return_code $? "Setting rabbitmq-server root permissions:" "${msg}"
        echo -e "channel_max = 0\nhandshake_timeout = 60000\nheartbeat = 120" > /etc/rabbitmq/rabbitmq.conf
        msg=$(systemctl restart rabbitmq-server.service)
        check_return_code $? "Restarting rabbitmq-server:" "${msg}"
        echo -e "channel_max = 0\nhandshake_timeout = 60000\nheartbeat = 120" > /etc/rabbitmq/rabbitmq.conf
        msg=$(systemctl enable rabbitmq-server.service)
        check_return_code $? "Enabling rabbitmq-server:" "${msg}"
    else
        log_warn "RabbitMQ has been installed, if it's not issued by cdmone, please remove it then re-run this scripts"
    fi
}

install_mysql()
{

    #checking mysql is runing ?
    existed=$(rpm -qa | egrep -i "mysql|mariadb" | grep -v mariadb-libs)
    if [ "${existed}" != "" ];then
        log_warn "mysql|has been installed, if it's not issued by cdmone, please remove it then re-run this scripts"
        exit 2
    fi
    existed=$(ps -ef| grep mysqld | grep -v grep)
    if [ "${existed}" == "" ];then
        log_info "Starting MySQL installation"
        yum_rpm mysql-community-server
        cat <<EOF > /etc/my.cnf
# For advice on how to change settings please see
# http://dev.mysql.com/doc/refman/5.7/en/server-configuration-defaults.html

[mysqld]
#
datadir=/var/lib/mysql
socket=/var/lib/mysql/mysql.sock
character-set-server=utf8
collation-server=utf8_general_ci
lower_case_table_names=1
sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION
# Disabling symbolic-links is recommended to prevent assorted security risks
symbolic-links=0
log-error=/var/log/mysqld.log
pid-file=/var/run/mysqld/mysqld.pid
[client]
default-character-set=utf8
EOF
        msg=$(systemctl  start mysqld)
        check_return_code $? "First starting up MySQL:" "${msg}"
        oldpass=$(echo `grep 'temporary password' /var/log/mysqld.log |awk -F': ' '{print $NF}'` | awk '{print $NF}')
        msg=$(mysqladmin -uroot -p${oldpass} password "root123@eCloud")
        check_return_code $? "Mysql root passwd reset:"

        log_info "Set MYSQL PID file ..."
        mkdir -p /var/lib/mysqld
        touch /var/lib/mysqld/mysqld.pid
        chown mysql.mysql /var/lib/mysqld
        chown mysql.mysql /var/lib/mysqld/mysqld.pid
        sed -i 's/\/var\/run\/mysqld\/mysqld.pid/'"\/var\/lib\/mysqld\/mysqld.pid"'/g' /etc/my.cnf
        sed -i 's/\/var\/run\/mysqld\/mysqld.pid/'"\/var\/lib\/mysqld\/mysqld.pid"'/g' /usr/lib/systemd/system/mysqld.service

        msg=$(systemctl  restart mysqld)
        check_return_code $? "Restarting MySQL:" "${msg}"
        msg=$(systemctl enable mysqld)
        check_return_code $? "Enabling MySQL auto startup:" "${msg}"
    fi

}

extra_director_file(){
    cd $MY_SHELL_ABS_PATH
    if [ -d /usr/local/cdmone/cdmone-director ];then
        msg=$(unzip -qo apps/cdmone-director.zip -d /usr/local/)
        check_return_code $? "Updating director app" "${msg}"
    else
        FRESH_INSTALL=1
        msg=$(unzip -qo apps/cdmone-director.zip -d /usr/local/)
        check_return_code $? "Installing director app" "${msg}"
    fi
}

enable_director_startup(){
    msg=$(cat > /usr/lib/systemd/system/cdmone-director-ux.service  << EOF
[Unit]
Description=cdmone-director-ux.service
After=cdmone-director-eureka.service

[Service]
ExecStart=${JAVAPATH} -jar -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms512m -Xmx512m -Xmn256m -Xss512k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC /usr/local/cdmone/cdmone-director/bin/cdmone-director-ux.jar --spring.config.location=/usr/local/cdmone/cdmone-director/etc/application.properties -Duser.timezone=Asia/Shanghai

[Install]
WantedBy=multi-user.target
EOF
)
    check_return_code $? "Enabling cdmone-director-ux service" "${msg}"
    msg=$(cat > /usr/lib/systemd/system/cdmone-director-engine.service  << EOF
[Unit]
Description=cdmone-director-engine.service
After=cdmone-director-eureka.service

[Service]
ExecStart=${JAVAPATH} -jar -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms512m -Xmx512m -Xmn256m -Xss512k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC /usr/local/cdmone/cdmone-director/bin/cdmone-director-engine.jar -Duser.timezone=Asia/Shanghai

[Install]
WantedBy=multi-user.target
EOF
)
    check_return_code $? "Enabling cdmone-director-engine service" "${msg}"
    msg=$(cat > /usr/lib/systemd/system/cdmone-director-eureka.service  << EOF
[Unit]
Description=cdmone-director-eureka.service

[Service]
ExecStart=${JAVAPATH} -jar -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms512m -Xmx512m -Xmn256m -Xss512k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC /usr/local/cdmone/cdmone-director/bin/cdmone-director-eureka.jar -Duser.timezone=Asia/Shanghai

[Install]
WantedBy=multi-user.target
EOF
)
    check_return_code $? "Enabling cdmone-director-eureka service" "${msg}"

    msg=$(cat > /usr/lib/systemd/system/cdmone-director-gateway.service  << EOF
[Unit]
Description=cdmone-director-gateway.service
After=cdmone-director-engine.service

[Service]
ExecStart=${JAVAPATH} -jar -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms512m -Xmx512m -Xmn256m -Xss512k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC /usr/local/cdmone/cdmone-director/bin/cdmone-director-gateway.jar -Duser.timezone=Asia/Shanghai

[Install]
WantedBy=multi-user.target
EOF
)
    check_return_code $? "Enabling cdmone-director-gateway service" "${msg}"
    systemctl daemon-reload
    if [ $FRESH_INSTALL = 1 ];then
        log_info "First Installation，auto startup director service"

        msg=$(systemctl enable cdmone-director-eureka && systemctl start cdmone-director-eureka)
        check_return_code $? "Starting cdmone-director-eureka service" "${msg}"

        msg=$(systemctl enable cdmone-director-ux && systemctl start cdmone-director-ux)
        check_return_code $? "Starting cdmone-director-ux service" "${msg}"

        msg=$(systemctl enable cdmone-director-engine && systemctl start cdmone-director-engine)
        check_return_code $? "Starting cdmone-director-engine service" "${msg}"

        msg=$(systemctl enable cdmone-director-gateway && systemctl start cdmone-director-gateway)
        check_return_code $? "Starting cdmone-director-gateway service" "${msg}"
    else
        log_info "Updating Completed, Please manual restart director service"
    fi
}


init_db_user(){
    # if cdmeone user existed, skip  or create it
    msg=$(mysql -u root -proot123@eCloud -sse "SELECT EXISTS(SELECT 1 FROM mysql.user WHERE user = 'cdmone');" 2>/dev/null)
    if [ "$msg" = 1 ];then
        log_warn "cdmone user is existed, skipped"
    else
        msg=$(mysql -u root -proot123@eCloud -sse "create user cdmone identified by 'root123@eCloud';" 2>&1)
        check_return_code $? "Adding cdmone_saas user" "${msg}"
    fi
}


init_db(){
    # if database existed , skip  or create it
    msg=$(mysql -u root -proot123@eCloud -sse "SELECT EXISTS(SELECT 1 FROM information_schema.schemata WHERE schema_name = 'cdmone_saas')" 2>/dev/null)
    if [ "$msg" = 1 ];then
        log_warn "DATABASE: cdmone_saas is existed, skipped"
    else
        # create db, grant priv, source init sql
        msg=$(mysql -u root -proot123@eCloud -sse "CREATE DATABASE cdmone_saas CHARACTER SET utf8 COLLATE utf8_general_ci;" 2>&1)
        check_return_code $? "Creating Database cdmone_saas:" "${msg}"
        grant_priv
        cd $MY_SHELL_ABS_PATH
        msg=$(LANG=en_US.utf8 mysql -u root -proot123@eCloud -sse "use cdmone_saas;source script/cdmone-director.sql;" 2>&1)
        check_return_code $? "Sourcing Database cdmone_saas:" "${msg}"
    fi
}

grant_priv(){
        msg=$(mysql -u root -proot123@eCloud -sse "grant all privileges on cdmone_saas.* to 'cdmone'@'%' identified by 'root123@eCloud';flush privileges;" 2>&1)
        check_return_code $? "Granting privileges on cdmone_saas on user [cdmone]:" "${msg}"
}

install_repo
if [ "$1" == "standalone" ];then
    echo ">>>>>RABBITMQ Skipped<<<<<<"
else
    log_info ">>>>>>>>RABBITMQ<<<<<<<<<"
    install_rabbitmq
fi
log_info ">>>>>>>>>>MYSQL<<<<<<<<<<"
install_mysql
if [ "$1" == "nosaas" ];then
    echo ">>>>>DIRECTOR APP SKipped<<<<"
else
    log_info ">>>>>DIRECTOR APP<<<<<<<<"
    extra_director_file
    enable_director_startup
    log_info ">>>>>INIT DIRECTOR DB<<<<"
    init_db_user
    init_db
fi
