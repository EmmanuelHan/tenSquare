package com.tensquareRabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "Queue2",
        durable = "true"),
        exchange = @Exchange(value = "Exchange1",
                ignoreDeclarationExceptions = "true"),
        key = "Queue2"))
public class Customer2 {

    @RabbitHandler
    public void getMessage(String message){
        System.out.println("dataOpeartionTask队列消息:["+message+ "]");
    }
}
