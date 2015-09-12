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

private Long citation_Numbers;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
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

    public Long getCitationNumbers() {
        return citationNumbers;
    }

    public void setCitationNumbers(Long citationNumbers) {
        this.citationNumbers = citation_Numbers;
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

    public void setFine_Amount(BigDecimal fineAmount) {
        this.fineAmount = fineAmount;
    }

    public BigDecimal getCourtCosts() {
        return courtCosts;
    }

    public void setCourtCosts(BigDecimal courtCosts) {
        this.courtCosts = courtCosts;
    }
}
