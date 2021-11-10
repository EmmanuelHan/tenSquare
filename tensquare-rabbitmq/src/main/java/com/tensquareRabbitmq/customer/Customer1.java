package com.tensquareRabbitmq.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "Queue1",
        durable = "true"),
        exchange = @Exchange(value = "Exchange1",
                ignoreDeclarationExceptions = "true"),
        key = "Queue1"))
public class Customer1 {

    @RabbitHandler
    public void getMessage(String message) {
        log.info("copyDataManager队列消息[{}]", message);
    }
}
