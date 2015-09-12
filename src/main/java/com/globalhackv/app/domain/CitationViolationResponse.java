package com.globalhackv.app.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jwillard on 9/12/2015.
 */
public class CitationViolationResponse {

    private Citation citation;
    private BigDecimal totalAmount=new BigDecimal(0);

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    private List<Violation> violations;

    public Citation getCitation() {
        return citation;
    }

    public void setCitation(Citation citation) {
        this.citation = citation;
    }

    public List<Violation> getViolations() {
        return violations;
    }

    public void setViolations(List<Violation> violations) {
        this.violations = violations;
    }
}
