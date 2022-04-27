package com.tensquareRabbitmq.controller;

import com.tensquareRabbitmq.service.SendMessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private SendMessageService sendMessageService;

    @PostMapping("/sendMessage")
    public String sendMessage(){
        sendMessageService.sendMessage();
        return "发送成功";
    }

}
