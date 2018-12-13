package com.example.demo.ToolUtil;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * 多定时任务
 */
@Component
public class SchedulerAllJob {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    public void scheduleJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        /**
         *多个任务配置
         */
        scheduleJob1(scheduler);
       /* scheduleJob2(scheduler);*/
    }

    /**
     * 配置Job1
     * 此处的任务可以配置可以放到properties或者是放到数据库中
     * 如果此时需要做到动态的定时任务，请参考：http://blog.csdn.net/liuchuanhong1/article/details/60873295
     *  博客中的ScheduleRefreshDatabase类
     * @param scheduler
     * @throws SchedulerException
     */
    private void scheduleJob1(Scheduler scheduler) throws SchedulerException{
        /*
         *  此处可以先通过任务名查询数据库，如果数据库中存在该任务，则按照ScheduleRefreshDatabase类中的方法，更新任务的配置以及触发器
         *  如果此时数据库中没有查询到该任务，则按照下面的步骤新建一个任务，并配置初始化的参数，并将配置存到数据库中
         *  TestQuartz定时任务类
         */
        JobDetail jobDetail = JobBuilder.newJob(TestQuartz.class) .withIdentity("job1", "group1").build();
        // 每天上午10.33执行一次
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 33 10 * * ?");

        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1") .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }

    /**
     * 配置Job
     * @param scheduler
     * @throws SchedulerException
     */
    private void scheduleJob2(Scheduler scheduler) throws SchedulerException{
        /*JobDetail jobDetail = JobBuilder.newJob(TestQuartz.class) .withIdentity("job2", "group1").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
        // 每10s执行一次
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1") .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);*/
    }

}
