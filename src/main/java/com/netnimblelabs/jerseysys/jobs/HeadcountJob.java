/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.jerseysys.jobs;

import com.netnimblelabs.jerseysys.models.TaskBatch;
import com.netnimblelabs.jerseysys.models.TaskCompanyRequest;
import com.netnimblelabs.jerseysys.util.TaskDBUtils;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author admin
 */
public class HeadcountJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        int collectionId = context.getJobDetail().getJobDataMap().getInt("collectionId");
        int batchId = context.getJobDetail().getJobDataMap().getInt("batchId");

        System.out.println("************** STARTING COMPANY HOUSE JOB **************");

        try {
            TaskBatch batch = TaskDBUtils.getBatch(batchId);
            if (batch != null) {
                processBatch(batch);
                System.out.println("Batch processed");
                JobManager.prepareAndScheduleNextBatch(collectionId);
            } else {
                System.out.println("No batch found or all batches are processed.");
            }
            System.out.println("************** FETCH JOB COMPLETE **************");

        } catch (Exception e) {
            System.out.println("************** FETCH JOB EXCEPTION **************");
            System.out.println("Exception occurred: " + e.getLocalizedMessage());
            throw new JobExecutionException(e);
        }
    }

    private void processBatch(TaskBatch batch) throws Exception {
        List<TaskCompanyRequest> requests = TaskDBUtils.getRequests(batch.getBatchId());

        for (TaskCompanyRequest request : requests) {
            try {
                fetchCompanyProfile(request);
                request.setStatus("Completed");
            } catch (Exception e) {
                request.setStatus("Failed");
                request.setErrorMessage(e.getMessage());
                request.setAttempts(request.getAttempts() + 1);
            }
            TaskDBUtils.updateRequest(request);
        }
        updateBatchStatus(batch);
    }

    private void fetchCompanyProfile(TaskCompanyRequest request) throws Exception {
        // Logic to fetch company profile using API
        String orgNumber = request.getOrgNumber();
        System.out.println("Done for Number: " + orgNumber);
//        CHServiceImplementation.getCompanyProfileInfo(orgNumber);
    }

    private void updateBatchStatus(TaskBatch batch) throws Exception {
        List<TaskCompanyRequest> requests = TaskDBUtils.getRequests(batch.getBatchId());
        long completedCount = requests.stream().filter(r -> "Completed".equals(r.getStatus())).count();
        long failedCount = requests.stream().filter(r -> "Failed".equals(r.getStatus())).count();

        batch.setProcessedCompanies((int) completedCount);
        batch.setFailedCompanies((int) failedCount);
        if (failedCount > 0) {
            batch.setStatus("Failed");
        } else if (completedCount == batch.getTotalCompanies()) {
            batch.setStatus("Completed");
        }
        TaskDBUtils.updateBatch(batch);
    }
}
