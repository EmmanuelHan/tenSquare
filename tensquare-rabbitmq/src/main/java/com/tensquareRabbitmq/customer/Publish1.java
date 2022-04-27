package com.tensquareRabbitmq.customer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.core.io.ClassPathResource;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;


public class Publish1 {

    // RabbitMQ服务端地址、端口、用户名、密码
    private static final String ADDRESS = "10.0.31.100";
    private static final int PORT = 5671;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root123@eCloud";

    private static final String DEFAULT_EXCHANGE = "TEST";
    private static final String QUEUE_NAME = "Queue1";

    // 使用tls-gen工具生成证书文件时设置的私钥密码
    private static final String CLIENT_KEYSTORE_PASSWORD = "root123@eCloud";
    // 客户端证书文件client_key.p12路径
    private static final String CLIENT_KEYSTORE_PATH = "client_localhost_key.p12";
    // 使用keytool生成证书文件时填写的密码
    private static final String SERVER_KEYSTORE_PASSWORD = "root123@eCloud";
    // 使用keytool生成的服务端证书文件路径
    private static final String SERVER_KEYSTORE_PATH = "server.keystore";


    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(ADDRESS);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);

        // 启用SSL\TLS
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(new ClassPathResource(CLIENT_KEYSTORE_PATH).getFile()), CLIENT_KEYSTORE_PASSWORD.toCharArray());
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, CLIENT_KEYSTORE_PASSWORD.toCharArray());

        KeyStore tks = KeyStore.getInstance("JKS");
        tks.load(new FileInputStream(new ClassPathResource(SERVER_KEYSTORE_PATH).getFile()), SERVER_KEYSTORE_PASSWORD.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(tks);

        SSLContext ctx = SSLContext.getInstance("TLSv1.2");
        ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        factory.useSslProtocol(ctx);

        // 发送消息，测试连接
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(DEFAULT_EXCHANGE, BuiltinExchangeType.DIRECT);
//        channel.queueBind(QUEUE_NAME,DEFAULT_EXCHANGE,QUEUE_NAME);
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        byte[] message = ("test message").getBytes();
        channel.basicPublish(DEFAULT_EXCHANGE, QUEUE_NAME, null, message);

        channel.close();
        connection.close();
    }

}
