package com.tensquareRabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 添加Mq监听，bindings 在mq中不存在该queue的时候，自动创建
 */
@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "${spring.rabbitmq.listener.feedback.queue.name}",
        durable = "${spring.rabbitmq.listener.feedback.queue.durable}"),
        exchange = @Exchange(value = "${spring.rabbitmq.listener.feedback.exchange.name}",
                durable = "${spring.rabbitmq.listener.feedback.exchange.durable}",
                type = "${spring.rabbitmq.listener.feedback.exchange.type}",
                ignoreDeclarationExceptions = "${spring.rabbitmq.listener.feedback.exchange.ignoreDeclarationExceptions}"),
        key = "${spring.rabbitmq.listener.feedback.exchange.key}"))
public class Customer4 {

    @RabbitHandler
    public void getMessage(String message){
        System.out.println("Worker2API消息:["+message + "]");
    }
}
