package com.tensquareRabbitmq.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "${spring.rabbitmq.listener.feedback.queue.name}",
        durable = "${spring.rabbitmq.listener.feedback.queue.durable}"),
        exchange = @Exchange(value = "${spring.rabbitmq.listener.feedback.exchange.name}",
                durable = "${spring.rabbitmq.listener.feedback.exchange.durable}",
                type = "${spring.rabbitmq.listener.feedback.exchange.type}",
                ignoreDeclarationExceptions = "${spring.rabbitmq.listener.feedback.exchange.ignoreDeclarationExceptions}"),
        key = "${spring.rabbitmq.listener.feedback.exchange.key}"))
public class Customer1 {

    @RabbitHandler
    public void getMessage(String message){
        log.info("收到字符串型消息：{}",message);
    }
}
