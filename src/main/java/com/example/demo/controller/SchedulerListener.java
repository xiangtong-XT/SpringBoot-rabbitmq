package com.example.demo.controller;

import com.example.demo.ToolUtil.SchedulerAllJob;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/*@Configuration
@EnableScheduling
@Component*/
/**
 * 多定时任务
 */
public class SchedulerListener {

    @Autowired
    public SchedulerAllJob schedulerAllJob;

    /**
     * 每天上午10.33执行一次
     *  启动的时候执行该方法，或者是使用ApplicationListener，在启动的时候执行该方法
     *  具体使用见：http://blog.csdn.net/liuchuanhong1/article/details/77568187
     * @throws SchedulerException
     */

    @Scheduled(cron="0 33 10 * * ?")
    public void schedule() throws SchedulerException {
        schedulerAllJob.scheduleJobs();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        return schedulerFactoryBean;
    }

}
