package com.zcl.study.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.util.ArrayList;
import java.util.Date;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-4-16 .
 */
public class PrintJob implements Job {
    private String jobSays;
    private float myFloatValue;
    private ArrayList state = new ArrayList();

    public PrintJob() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
//		JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
//		JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        state.add(new Date());
        System.out.println("=======================Instance " + key + " of PrintJob says: " + jobSays + ", and val is: " + myFloatValue);
    }

    public void setJobSays(String jobSays) {
        this.jobSays = jobSays;
    }

    public void setMyFloatValue(float myFloatValue) {
        this.myFloatValue = myFloatValue;
    }

    public void setState(ArrayList state) {
        this.state = state;
    }
}
