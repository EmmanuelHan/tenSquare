package com.tensquare.common.util;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Quartz 工具类 <br>
 * Date: 2016年6月22日 <br>
 * Copyright (c) 2016 xyw10000@163.com <br>
 *
 * @author xuyw
 */
public class QuartzUtil {
    private static SchedulerFactory schedulerFactory;
    private static Scheduler scheduler = null;

    static {
        try {
            schedulerFactory = new StdSchedulerFactory();
            scheduler = schedulerFactory.getScheduler();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加job
     */
    public static void addJob(Job job, String triggerName, String triggerGroup, String cronExpression) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(job.getClass())
                    .withIdentity(triggerName, triggerGroup).build();
            CronTrigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(triggerName, triggerGroup)
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule(cronExpression))
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
            startJobs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新job执行时间
     */
    public static void modifyJobCron(String triggerName, String triggerGroup, String cronExpression) {
        try {
            TriggerKey triggerKey = new TriggerKey(triggerName,
                    triggerGroup);
            CronTrigger trigger = (CronTrigger) scheduler
                    .getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            String oldCron = trigger.getCronExpression();
            if (!oldCron.equalsIgnoreCase(cronExpression)) {
                // 修改时间
                trigger = trigger
                        .getTriggerBuilder()
                        .withIdentity(triggerKey)
                        .withSchedule(
                                CronScheduleBuilder.cronSchedule(cronExpression))
                        .build();
                // 重启触发器
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 暂停任务
     */
    public static void pauseJob(String jobName, String groupName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, groupName);
        scheduler.pauseJob(jobKey);
    }

    /**
     * 移除job
     */
    public static void removeJob(String jobName, String groupName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, groupName);
        scheduler.resumeJob(jobKey);
    }

    /**
     * 恢复job
     */
    public static void recoverJob(String jobName, String groupName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, groupName);
        scheduler.resumeJob(jobKey);
    }


    public static void startJobs() {
        try {
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shutDownJobs() {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
