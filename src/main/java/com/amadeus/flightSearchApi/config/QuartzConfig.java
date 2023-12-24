package com.amadeus.flightSearchApi.config;

import com.amadeus.flightSearchApi.service.impl.FlightInfoJob;
import org.quartz.SchedulerException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.Scheduler;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(FlightInfoJob.class);
        factoryBean.setDurability(true);
        return factoryBean;
    }

    @Bean
    public SimpleTriggerFactoryBean trigger(JobDetail jobDetail) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setRepeatInterval(24 * 60 * 60 * 1000); // Her 24 saatte bir çalışacak
        factoryBean.setStartDelay(0);
        return factoryBean;
    }

    @Bean
    public Scheduler scheduler(Trigger trigger, JobDetail jobDetail) throws SchedulerException {
        return new org.quartz.impl.StdSchedulerFactory().getScheduler();
    }
}

