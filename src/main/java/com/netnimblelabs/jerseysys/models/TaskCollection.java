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
@Table(name = "TaskCollections")
public class TaskCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer collectionId;

    private LocalDateTime createdAt;

    private String status;

    private Integer totalBatches;

    private Integer completedBatches;

    private Integer failedBatches;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Getters and Setters

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
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

    public Integer getTotalBatches() {
        return totalBatches;
    }

    public void setTotalBatches(Integer totalBatches) {
        this.totalBatches = totalBatches;
    }

    public Integer getCompletedBatches() {
        return completedBatches;
    }

    public void setCompletedBatches(Integer completedBatches) {
        this.completedBatches = completedBatches;
    }

    public Integer getFailedBatches() {
        return failedBatches;
    }

    public void setFailedBatches(Integer failedBatches) {
        this.failedBatches = failedBatches;
    }
}

