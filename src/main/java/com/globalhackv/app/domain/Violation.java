package com.globalhackv.app.domain;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
* Created by jwillard on 9/11/2015.
*/
@Entity
public class Violation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long citationNumber;
    private String violationNumber;
    private String violationDescription;
    private boolean warrantStatus;
    private String warrantNumber;
    private String status;
    private String statusDate;
    private BigDecimal fineAmount;
    private BigDecimal courtCosts;

    public Violation() {

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCitationNumber() {
        return citationNumber;
    }

    public void setCitationNumber(long citationNumber) {
        this.citationNumber = citationNumber;
    }

    public String getViolationNumber() {
        return violationNumber;
    }

    public void setViolationNumber(String violationNumber) {
        this.violationNumber = violationNumber;
    }

    public String getViolationDescription() {
        return violationDescription;
    }

    public void setViolationDescription(String violationDescription) {
        this.violationDescription = violationDescription;
    }

    public boolean isWarrantStatus() {
        return warrantStatus;
    }

    public void setWarrantStatus(boolean warrantStatus) {
        this.warrantStatus = warrantStatus;
    }

    public String getWarrantNumber() {
        return warrantNumber;
    }

    public void setWarrantNumber(String warrantNumber) {
        this.warrantNumber = warrantNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(String statusDate) {
        this.statusDate = statusDate;
    }

    public BigDecimal getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(BigDecimal fineAmount) {
        this.fineAmount = fineAmount;
    }

    public BigDecimal getCourtCosts() {
        return courtCosts;
    }

    public void setCourtCosts(BigDecimal courtCosts) {
        this.courtCosts = courtCosts;
    }



}
