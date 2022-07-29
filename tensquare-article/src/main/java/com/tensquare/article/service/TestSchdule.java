package com.tensquare.article.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestSchdule {

    @Scheduled(cron = "-")
    public void scheduleTest() {
        //每30秒执行一次
        //相关逻辑操作，如关闭订单，设置定时关单的时间（建议设置在配置文件中）
        //查询订单表的创建时间进行相关处理
        log.info("执行定时任务+++++++++++++++++++++");
    }

}
