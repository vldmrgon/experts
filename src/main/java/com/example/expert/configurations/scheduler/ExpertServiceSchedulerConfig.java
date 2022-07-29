package com.example.expert.configurations.scheduler;

import com.example.expert.services.jobs.MyJobsExample;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.function.Function;

@Slf4j
@Configuration
@ConditionalOnProperty(value = "application.scheduling.enable", havingValue = "true")
public class ExpertServiceSchedulerConfig {

    @Bean
    public JobDetail jobDetailMyExample() {
        return jobDetail(MyJobsExample.class, "myJobsExample");
    }

    @Bean
    public Trigger myJobTrigger(@Qualifier("jobDetailMyExample") JobDetail jobDetail) {
        return trigger(jobDetail, "MY Job Example", 1000, 1000);
    }

    @Bean
    @SneakyThrows
    public void startScheduler() {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetailMyExample(), myJobTrigger(jobDetailMyExample()));
    }

    private <T extends Job> JobDetail jobDetail(Class<T> jobClass, String jobIdentifier) {
        return JobBuilder.newJob(jobClass).withIdentity(jobIdentifier).storeDurably().build();
    }

    private Trigger trigger(JobDetail jobDetail, String triggerIdentifier, int triggerJobInitialDelay, int triggerInterval) {

        Date triggerStartTime = getJobInitialDelay(triggerJobInitialDelay);
        return TriggerBuilder
                .newTrigger()
                .forJob(jobDetail)
                .withIdentity(triggerIdentifier)
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInMilliseconds(triggerInterval)
                        .repeatForever())
                .startAt(triggerStartTime)
                .build();
    }

    private Date getJobInitialDelay(int initialDelay) {
        return new Date(System.currentTimeMillis() + initialDelay);
    }
}
