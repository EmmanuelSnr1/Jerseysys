/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.jerseysys.models;

/**
 *
 * @author admin
 */
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Collections")
public class Collection {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long collectionId;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    private String status;
    
    private int totalBatches;
    
    private int completedBatches;
    
    private int failedBatches;

    private List<Batch> batches;

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalBatches() {
        return totalBatches;
    }

    public void setTotalBatches(int totalBatches) {
        this.totalBatches = totalBatches;
    }

    public int getCompletedBatches() {
        return completedBatches;
    }

    public void setCompletedBatches(int completedBatches) {
        this.completedBatches = completedBatches;
    }

    public int getFailedBatches() {
        return failedBatches;
    }

    public void setFailedBatches(int failedBatches) {
        this.failedBatches = failedBatches;
    }

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }

    
}

