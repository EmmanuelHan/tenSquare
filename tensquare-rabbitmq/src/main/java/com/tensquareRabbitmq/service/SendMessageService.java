package com.tensquareRabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SendMessageService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(){
        rabbitTemplate.convertAndSend("Queue1","直接模式测试");
    }

}
