package com.globalhackv.app.repository;

import com.globalhackv.app.domain.Violation;
import com.globalhackv.app.domain.Citation;
import com.globalhackv.app.domain.Citation_;
import com.globalhackv.app.domain.Violation_;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by jwillard on 9/12/2015.
 */
public class ViolationSpec implements Specification<Violation> {

    private final long citationNumber;

    public ViolationSpec(long citationNumber) {
        this.citationNumber = citationNumber;
    }

    @Override
    public Predicate toPredicate(Root<Violation> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(cb.equal(root.get(Violation_.citationNumber), citationNumber));

        return andTogether(predicates, cb);
    }

    private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
        return cb.and(predicates.toArray(new Predicate[0]));
    }

}