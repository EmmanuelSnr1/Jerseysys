/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.jerseysys.models;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 *
 * @author admin
 */


@Entity
@Table(name = "TaskBatches")
public class TaskBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer batchId;

    @Column(name = "taskCollectionId", nullable = false)
    private Integer taskCollectionId;

    private LocalDateTime createdAt;

    private String status;

    private Integer totalCompanies;

    private Integer processedCompanies;

    private Integer failedCompanies;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Getters and Setters

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Integer getTaskCollectionId() {
        return taskCollectionId;
    }

    public void setTaskCollectionId(Integer taskCollectionId) {
        this.taskCollectionId = taskCollectionId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalCompanies() {
        return totalCompanies;
    }

    public void setTotalCompanies(Integer totalCompanies) {
        this.totalCompanies = totalCompanies;
    }

    public Integer getProcessedCompanies() {
        return processedCompanies;
    }

    public void setProcessedCompanies(Integer processedCompanies) {
        this.processedCompanies = processedCompanies;
    }

    public Integer getFailedCompanies() {
        return failedCompanies;
    }

    public void setFailedCompanies(Integer failedCompanies) {
        this.failedCompanies = failedCompanies;
    }
}

