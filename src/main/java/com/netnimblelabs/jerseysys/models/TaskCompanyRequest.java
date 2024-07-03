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
@Table(name = "TaskCompanyRequests")
public class TaskCompanyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @Column(name = "taskBatchId", nullable = false)
    private Integer taskBatchId;

    private String orgNumber;

    private String status;

    private Integer attempts;

    private LocalDateTime lastAttemptAt;

    private String errorMessage;

    @PrePersist
    protected void onCreate() {
        lastAttemptAt = LocalDateTime.now();
    }

    // Getters and Setters

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getTaskBatchId() {
        return taskBatchId;
    }

    public void setTaskBatchId(Integer taskBatchId) {
        this.taskBatchId = taskBatchId;
    }

    public String getOrgNumber() {
        return orgNumber;
    }

    public void setOrgNumber(String orgNumber) {
        this.orgNumber = orgNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public LocalDateTime getLastAttemptAt() {
        return lastAttemptAt;
    }

    public void setLastAttemptAt(LocalDateTime lastAttemptAt) {
        this.lastAttemptAt = lastAttemptAt;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

