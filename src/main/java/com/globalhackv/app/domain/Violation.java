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
    private String violation_Number;
    private String violation_Description;
    private boolean warrant_Status;
    private String warrant_Number;
    private String status;
    private String status_Date;
    private BigDecimal fine_Amount;
    private BigDecimal court_Costs;

    public Violation() {

    }

    public Long getCitation_Numbers() {
        return citation_Numbers;
    }

    public void setCitation_Numbers(Long citation_Numbers) {
        this.citation_Numbers = citation_Numbers;
    }

    public String getViolation_Number() {
        return violation_Number;
    }

    public void setViolation_Number(String violation_Number) {
        this.violation_Number = violation_Number;
    }

    public String getViolation_Description() {
        return violation_Description;
    }

    public void setViolation_Description(String violation_Description) {
        this.violation_Description = violation_Description;
    }

    public boolean isWarrant_Status() {
        return warrant_Status;
    }

    public void setWarrant_Status(boolean warrant_Status) {
        this.warrant_Status = warrant_Status;
    }

    public String getWarrant_Number() {
        return warrant_Number;
    }

    public void setWarrant_Number(String warrant_Number) {
        this.warrant_Number = warrant_Number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_Date() {
        return status_Date;
    }

    public void setStatus_Date(String status_Date) {
        this.status_Date = status_Date;
    }

    public BigDecimal getFine_Amount() {
        return fine_Amount;
    }

    public void setFine_Amount(BigDecimal fine_Amount) {
        this.fine_Amount = fine_Amount;
    }

    public BigDecimal getCourt_Costs() {
        return court_Costs;
    }

    public void setCourt_Costs(BigDecimal court_Costs) {
        this.court_Costs = court_Costs;
    }
}
