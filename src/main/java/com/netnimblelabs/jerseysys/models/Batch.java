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
@Table(name = "Batches")
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchId;

    
    private Collection collection;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private String status;

    private int totalCompanies;

    private int processedCompanies;

    private int failedCompanies;

    private List<CompanyRequest> companyRequests;

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
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

    public int getTotalCompanies() {
        return totalCompanies;
    }

    public void setTotalCompanies(int totalCompanies) {
        this.totalCompanies = totalCompanies;
    }

    public int getProcessedCompanies() {
        return processedCompanies;
    }

    public void setProcessedCompanies(int processedCompanies) {
        this.processedCompanies = processedCompanies;
    }

    public int getFailedCompanies() {
        return failedCompanies;
    }

    public void setFailedCompanies(int failedCompanies) {
        this.failedCompanies = failedCompanies;
    }

    public List<CompanyRequest> getCompanyRequests() {
        return companyRequests;
    }

    public void setCompanyRequests(List<CompanyRequest> companyRequests) {
        this.companyRequests = companyRequests;
    }

    
}

