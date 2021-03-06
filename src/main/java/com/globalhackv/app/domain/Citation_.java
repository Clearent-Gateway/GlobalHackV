package com.globalhackv.app.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


// *should* be generated by Hibernate metamodel, but I don't know wtf I'm doing, so I coded it myself
// http://docs.jboss.org/hibernate/jpamodelgen/1.0/reference/en-US/html_single/#whatisit
@StaticMetamodel(Citation.class)
public class Citation_ {

    public static volatile SingularAttribute<Citation, Long> id;
    public static volatile SingularAttribute<Citation, String> citationNumber;
    public static volatile SingularAttribute<Citation, String> firstName;
    public static volatile SingularAttribute<Citation, String> lastName;
    public static volatile SingularAttribute<Citation, String> dateOfBirth;
    public static volatile SingularAttribute<Citation, String> driversLicense;
    public static volatile SingularAttribute<Citation, String> address;
    public static volatile SingularAttribute<Citation, String> city;
    public static volatile SingularAttribute<Citation, String> state;
    public static volatile SingularAttribute<Citation, String> municapality;
    public static volatile SingularAttribute<Citation, String> citationDate;
    public static volatile SingularAttribute<Citation, String> courtAddress;
    public static volatile SingularAttribute<Citation, String> courtDate;

}