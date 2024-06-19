/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.jerseysys.jobs;

import com.netnimblelabs.jerseysys.config.MyScheduler;
import java.util.UUID;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.apache.log4j.*;

import java.util.List;
import java.util.UUID;

/**
 *
 * @author admin
 */


public class JobManager {
    private static final Logger logger = Logger.getLogger(JobManager.class);

//    public void doFetchCompanyProfileInfoJob() {
//        try {
//            Scheduler scheduler = MyScheduler.getInstance();
//
//            // Generate unique IDs for the job and trigger
//            String jobId = UUID.randomUUID().toString();
//            String triggerId = UUID.randomUUID().toString();
//
//            // Define the job and tie it to our FetchCompanyDataJob class
//            JobDetail job = JobBuilder.newJob(FetchCompanyDataJob.class)
//                    .withIdentity("fetchCompanyDetailsJob-" + jobId, "group1")
//                    .build();
//
//            // Trigger the job to run every 5 minutes
//            Trigger trigger = TriggerBuilder.newTrigger()
//                    .withIdentity("fetchCompanyDetailsTrigger-" + triggerId, "group1")
//                    .startNow()
//                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                            .withIntervalInMinutes(5)
//                            .repeatForever())
//                    .build();
//
//            // Schedule the job using the trigger
//            scheduler.scheduleJob(job, trigger);
//            logger.info("Scheduled FetchCompanyDetailsJob with ID: " + jobId + " to run every 5 minutes.");
//        } catch (SchedulerException e) {
//            logger.error("Error scheduling FetchCompanyDetailsJob", e);
//        }
//    }

    public void checkRunningJobs() {
        try {
            Scheduler scheduler = MyScheduler.getInstance();

            List<JobExecutionContext> currentlyExecutingJobs = scheduler.getCurrentlyExecutingJobs();

            if (currentlyExecutingJobs.isEmpty()) {
                logger.info("There are currently no running Jobs");
            }

            for (JobExecutionContext jobExecutionContext : currentlyExecutingJobs) {
                logger.info("Running Job: " + jobExecutionContext.getJobDetail().getKey());
                // Additional details can be logged here
            }
        } catch (SchedulerException e) {
            logger.error("Error checking running jobs", e);
        }
    }

    public void checkJobDetails(String jobName, String jobGroup) {
        try {
            Scheduler scheduler = MyScheduler.getInstance();
            JobKey jobKey = new JobKey(jobName, jobGroup);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);

            if (jobDetail != null) {
                logger.info("Job Details: " + jobDetail);
                // Additional details can be logged here
            } else {
                logger.warn("Job not found.");
            }
        } catch (SchedulerException e) {
            logger.error("Error checking job details", e);
        }
    }

    public void checkJobState(String jobName, String jobGroup) {
        try {
            Scheduler scheduler = MyScheduler.getInstance();
            JobKey jobKey = new JobKey(jobName, jobGroup);
            TriggerKey triggerKey = new TriggerKey(jobName + "Trigger", jobGroup);
            Trigger trigger = scheduler.getTrigger(triggerKey);

            if (trigger != null) {
                Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
                logger.info("Job State: " + triggerState);
            } else {
                logger.warn("Trigger not found for the job.");
            }
        } catch (SchedulerException e) {
            logger.error("Error checking job state", e);
        }
    }

    public void checkAllJobs() {
        try {
            Scheduler scheduler = MyScheduler.getInstance();
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                    List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                    for (Trigger trigger : triggers) {
                        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                        logger.info("Job: " + jobDetail.getKey() + " is in state: " + triggerState);
                    }
                }
            }
        } catch (SchedulerException e) {
            logger.error("Error checking all jobs", e);
        }
    }
}

