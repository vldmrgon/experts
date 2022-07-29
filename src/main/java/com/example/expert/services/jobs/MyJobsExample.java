package com.example.expert.services.jobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@DisallowConcurrentExecution
public class MyJobsExample implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("WORK MY JOBS EXAMPLE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
