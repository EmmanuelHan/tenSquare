package com.tensquare.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.tensquare.sms.util.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @Resource
    private SmsUtil smsUtil;

    @Value("${aliyun.sms.sign_name}")
    private String sign_name;

    @Value("${aliyun.sms.template_code}")
    private String template_code;

    @RabbitHandler
    public void executeSms(Map<String,String> data){
        String mobile = data.get("mobile");
        String checkCode = data.get("checkCode");
        System.out.println("手机号："+mobile);
        System.out.println("验证码："+checkCode);
        try {
            smsUtil.sendSms(mobile,template_code,sign_name,"{\"checkCode\":\""+checkCode+"\"}");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
