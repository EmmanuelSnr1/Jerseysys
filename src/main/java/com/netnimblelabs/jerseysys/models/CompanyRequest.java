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

@Entity
@Table(name = "CompanyRequests")
public class CompanyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    
    private Integer batchId;

    private String orgNumber;

    private String status;

    private int attempts;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastAttemptAt;

    private String errorMessage;

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
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

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public Date getLastAttemptAt() {
        return lastAttemptAt;
    }

    public void setLastAttemptAt(Date lastAttemptAt) {
        this.lastAttemptAt = lastAttemptAt;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    
}

