package com.tensquareRabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "copyDataManager")
public class Customer1 {

    @RabbitHandler
    public void getMessage(String message){
        System.out.println("111直接模式消费消息:"+message);
    }
}
