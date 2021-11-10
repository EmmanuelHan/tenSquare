package com.tensquareRabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "Queue3",
        durable = "true"),
        exchange = @Exchange(value = "Exchange1",
                ignoreDeclarationExceptions = "true"),
        key = "Queue3"))
public class Customer3 {

    @RabbitHandler
    public void getMessage(String message){
        System.out.println("dataBackupJob消息:["+message + "]");
    }
}
