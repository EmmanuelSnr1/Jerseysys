/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.jerseysys.util;

import com.netnimblelabs.jerseysys.models.TaskBatch;
import com.netnimblelabs.jerseysys.models.TaskCollection;
import com.netnimblelabs.jerseysys.models.TaskCompanyRequest;
import java.util.List;

/**
 *
 * @author admin
 */
public class TaskDBUtils {

    public static int createCollection(List<String> orgNumbers) throws Exception {
        return SessionUtil.executeStatelessTransaction(session -> {
            TaskCollection collection = new TaskCollection();
            collection.setStatus("Pending");
            collection.setTotalBatches((orgNumbers.size() + 99) / 100);
            session.insert(collection);

            int collectionId = collection.getCollectionId();
            int batchCount = 0;

            for (int i = 0; i < orgNumbers.size(); i += 100) {
                TaskBatch batch = new TaskBatch();
                batch.setTaskCollectionId(collectionId);
                batch.setStatus("Pending");
                batch.setTotalCompanies(Math.min(100, orgNumbers.size() - i));
                session.insert(batch);

                int batchId = batch.getBatchId();
                for (int j = i; j < i + 100 && j < orgNumbers.size(); j++) {
                    TaskCompanyRequest request = new TaskCompanyRequest();
                    request.setTaskBatchId(batchId);
                    request.setOrgNumber(orgNumbers.get(j));
                    request.setStatus("Pending");
                    request.setAttempts(0);
                    session.insert(request);
                }

                batchCount++;
            }

            collection.setTotalBatches(batchCount);
            session.update(collection);

            return collectionId;
        });
    }

    public static TaskBatch getBatch(int batchId) throws Exception {
        return (TaskBatch) SessionUtil.executeStatelessTransaction(session -> session.get(TaskBatch.class, batchId));
    }

    public static List<TaskBatch> getPendingBatches(int collectionId, int limit) throws Exception {
        return SessionUtil.executeStatelessTransaction(session -> {
            String hql = "FROM TaskBatch WHERE taskCollectionId = :taskCollectionId AND status = 'Pending'";
            return session.createQuery(hql)
                    .setParameter("taskCollectionId", collectionId)
                    .setMaxResults(limit)
                    .list();
        });
    }

    public static List<TaskCompanyRequest> getRequests(int batchId) throws Exception {
        return SessionUtil.executeStatelessTransaction(session -> {
            String hql = "FROM TaskCompanyRequest WHERE taskBatchId = :taskBatchId";
            return session.createQuery(hql)
                    .setParameter("taskBatchId", batchId)
                    .list();
        });
    }

    public static void updateRequest(TaskCompanyRequest request) throws Exception {
        SessionUtil.executeStatelessTransaction(session -> {
            session.update(request);
            return null;
        });
    }

    public static void updateBatch(TaskBatch batch) throws Exception {
        SessionUtil.executeStatelessTransaction(session -> {
            session.update(batch);
            return null;
        });
    }

    public static void updateCollectionStatus(int collectionId, String status) throws Exception {
        SessionUtil.executeStatelessTransaction(session -> {
            TaskCollection collection = (TaskCollection) session.get(TaskCollection.class, collectionId);
            if (collection != null) {
                collection.setStatus(status);
                session.update(collection);
            }
            return null;
        });
    }

}
