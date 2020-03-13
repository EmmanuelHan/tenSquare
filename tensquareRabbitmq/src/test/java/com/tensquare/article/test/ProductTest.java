package com.tensquare.article.test;

import com.tensquareRabbitmq.RabbitmqApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqApplication.class)
public class ProductTest {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 直接模式
     */
    @Test
    public void sendMessage(){
        rabbitTemplate.convertAndSend("copyDataManager","直接模式测试");
    }

    /**
     * 分裂模式
     */
    @Test
    public void sendMessage2(){
        rabbitTemplate.convertAndSend("dataOperationTask","","分裂模式测试");
    }

    /**
     * 主题模式
     */
    @Test
    public void sendMessage3(){
        rabbitTemplate.convertAndSend("topic112","good.log","主题模式测试");
    }


}
