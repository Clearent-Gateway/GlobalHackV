package com.globalhackv.app.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

/**
 * Created by jwillard on 9/12/2015.
 */
@StaticMetamodel(Violation.class)
public class Violation_ {
    public static volatile SingularAttribute<Violation, Long> id;
    public static volatile SingularAttribute<Violation, Long> citationNumber;
    public static volatile SingularAttribute<Violation, String> violationNumber;
    public static volatile SingularAttribute<Violation, String> violationDescription;
    public static volatile SingularAttribute<Violation, Boolean> warrantStatus;
    public static volatile SingularAttribute<Violation, String> warrantNumber;
    public static volatile SingularAttribute<Violation, String> status;
    public static volatile SingularAttribute<Violation, String> statusDate;
    public static volatile SingularAttribute<Violation, BigDecimal> fineAmount;
    public static volatile SingularAttribute<Violation, BigDecimal> courtCosts;

}
