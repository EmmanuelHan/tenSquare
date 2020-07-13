package com.tensquareRabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "Queue1",
        durable = "true"),
        exchange = @Exchange(value = "Exchange1",
                ignoreDeclarationExceptions = "true"),
        key = "Queue1"))
public class Customer1 {

    @RabbitHandler
    public void getMessage(String message){
        System.out.println("copyDataManager队列消息[:"+message + "]");
    }
}
