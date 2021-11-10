package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import util.StringUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ${author}
 * @Date   ${date}
 */
@Slf4j
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Resource
    private ${table.mapperName} ${table.mapperName ? uncap_first};

    @Override
    public List<${entity}> list() {

       return ${table.mapperName ? uncap_first}.selectList(null);
    }

    /**
    * 分页查询
    *
    * @param ${entity ? uncap_first}  page  limit
    * @return jsonResponse
    */
    @Override
    public Result findByParam(${entity} ${entity ? uncap_first},Integer page , Integer limit) {

        if (!ObjectUtils.isEmpty(page)) {
            page = StringUtil.START_PAGE;
        }
        if (!ObjectUtils.isEmpty(limit)) {
            limit = StringUtil.PAGE_SIZE;
        }
        //开启分页
        IPage<${entity}> ${entity ? uncap_first}Page = new Page<>(page,limit);
        //查询构造器
        QueryWrapper<${entity}> wrapper = new QueryWrapper<>();

    <#list table.fields as field>
        if(${entity ? uncap_first}.get${field.propertyName ? cap_first}()!=null && !"".equals(${entity ? uncap_first}.get${field.propertyName ? cap_first}())){
            wrapper.eq("${field.name}",${entity ? uncap_first}.get${field.propertyName ? cap_first}());
        }
    </#list>
        IPage<${entity}> ${entity ? uncap_first}IPage = page(${entity ? uncap_first}Page, wrapper);

        return new Result(${entity ? uncap_first}IPage);
    }

}
</#if>
