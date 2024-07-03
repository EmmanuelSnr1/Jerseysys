/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.jerseysys.jobs;

import com.netnimblelabs.jerseysys.config.MyScheduler;
import com.netnimblelabs.jerseysys.models.TaskBatch;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import com.netnimblelabs.jerseysys.util.TaskDBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.quartz.impl.matchers.GroupMatcher;

public class JobManager {

    private static AtomicInteger jobCounter = new AtomicInteger(0);
    private static AtomicInteger triggerCounter = new AtomicInteger(0);
    private static final String JOB_ID = "HeadcountJob";
    private static final String TRIGGER_ID = "HeadcountTrigger";
    private static final Logger logger = LoggerFactory.getLogger(JobManager.class);

    public static void checkAllJobs() throws SchedulerException {

        Scheduler scheduler = MyScheduler.getInstance();

        List<String> jobGroups = scheduler.getJobGroupNames();
        for (String group : jobGroups) {
            Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(group));
            for (JobKey jobKey : jobKeys) {
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                System.out.println("Job details: " + jobDetail);
            }
        }
    }

    public static void checkJobState(String jobName, String jobGroup) throws SchedulerException {
        Scheduler scheduler = MyScheduler.getInstance();

        JobKey jobKey = new JobKey(jobName, jobGroup);
        if (scheduler.checkExists(jobKey)) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                System.out.println("Trigger state for " + trigger.getKey() + ": " + triggerState);
            }
        } else {
            System.out.println("Job does not exist.");
        }
    }

    public static void checkJobDetails(String jobName, String jobGroup) throws SchedulerException {
        Scheduler scheduler = MyScheduler.getInstance();

        JobKey jobKey = new JobKey(jobName, jobGroup);
        if (scheduler.checkExists(jobKey)) {
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            System.out.println("Job details: " + jobDetail);
        } else {
            System.out.println("Job does not exist.");
        }
    }

    public static void checkRunningJobs() throws SchedulerException {
        Scheduler scheduler = MyScheduler.getInstance();

        List<JobExecutionContext> currentlyExecutingJobs = scheduler.getCurrentlyExecutingJobs();
        for (JobExecutionContext jobExecutionContext : currentlyExecutingJobs) {
            JobDetail jobDetail = jobExecutionContext.getJobDetail();
            System.out.println("Currently running job: " + jobDetail);
        }
    }

    // Existing methods: initializeJob, prepareAndScheduleNextBatch, addFetchHeadcountJob
    public static int initializeJob(List<String> orgNumbers) throws Exception {
        int collectionId = TaskDBUtils.createCollection(orgNumbers);
        logger.info("Created collection with ID: {} ", collectionId);
        prepareAndScheduleNextBatch(collectionId);
        return collectionId;
    }

    public static void prepareAndScheduleNextBatch(int collectionId) throws Exception {
        List<TaskBatch> pendingBatches = TaskDBUtils.getPendingBatches(collectionId, 1);
        if (!pendingBatches.isEmpty()) {
            int nextBatchId = pendingBatches.get(0).getBatchId();
            logger.info("Preparing to schedule next batch with ID: {}", nextBatchId);
            TaskDBUtils.updateCollectionStatus(collectionId, "Processing");
            addFetchHeadcountJob(collectionId, nextBatchId);
        } else {
            logger.info("No more pending batches. Marking collection as completed.");
            TaskDBUtils.updateCollectionStatus(collectionId, "Completed");
        }
    }

    public static void addFetchHeadcountJob(int collectionId, int batchId) throws SchedulerException {
        try {
            Scheduler scheduler = MyScheduler.getInstance();
            JobDetail jobDetail;

            // Check if the job already exists
            JobKey jobKey = new JobKey(JOB_ID);
            if (!scheduler.checkExists(jobKey)) {
                // Add collectionId and batchId to JobDataMap
                jobDetail = JobBuilder.newJob(HeadcountJob.class)
                        .withIdentity(JOB_ID)
                        .usingJobData("collectionId", collectionId)
                        .usingJobData("batchId", batchId)
                        .build();

                Trigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity(TRIGGER_ID)
                        .startNow()
                        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInMinutes(5)
                                .repeatForever())
                        .build();

                // Schedule the job using the trigger
                scheduler.scheduleJob(jobDetail, trigger);
                System.out.println("Scheduled Headcount Job with ID: " + JOB_ID + " to run every 5 minutes.");
            } else {
                // Update job data if the job already exists
                jobDetail = scheduler.getJobDetail(jobKey);
                JobDataMap dataMap = jobDetail.getJobDataMap();
                dataMap.put("collectionId", collectionId);
                dataMap.put("batchId", batchId);
                scheduler.addJob(jobDetail, true, true);
            }
        } catch (SchedulerException e) {
            System.err.println("Failed to schedule Headcount Job with ID: " + e);
            throw e; // Rethrow the exception to ensure it is handled appropriately
        }
    }

    public static class HeadcountJob implements Job {

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            JobDataMap dataMap = context.getJobDetail().getJobDataMap();
            int collectionId = dataMap.getInt("collectionId");
            int batchId = dataMap.getInt("batchId");
            // Process the batch
            System.out.println("Processing collection ID: " + collectionId + " with batch ID: " + batchId);
            // After processing, prepare and schedule the next batch
            try {
                prepareAndScheduleNextBatch(collectionId);
            } catch (Exception e) {
                throw new JobExecutionException(e);
            }
        }
    }
}
