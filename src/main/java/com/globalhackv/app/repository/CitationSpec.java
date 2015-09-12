package com.globalhackv.app.repository;

import com.globalhackv.app.domain.Citation;
import com.globalhackv.app.domain.Citation_;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CitationSpec implements Specification<Citation> {

    private final Citation example;

    public CitationSpec(Citation example) {
        this.example = example;
    }

    @Override
    public Predicate toPredicate(Root<Citation> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<Predicate>();

        if (StringUtils.isNotBlank(example.getLastName())){
            predicates.add(cb.like(cb.lower(root.get(Citation_.lastName)), example.getLastName().toLowerCase() + "%"));
        }

        if (StringUtils.isNotBlank(example.getFirstName())){
            predicates.add(cb.like(cb.lower(root.get(Citation_.firstName)), example.getFirstName().toLowerCase() + "%"));
        }

        if (StringUtils.isNotBlank(example.getDateOfBirth())){
            predicates.add(cb.like(cb.lower(root.get(Citation_.dateOfBirth)), example.getDateOfBirth().toLowerCase() + "%"));
        }

        if (StringUtils.isNotBlank(example.getDriversLiscense())){
            predicates.add(cb.like(cb.lower(root.get(Citation_.driversLicense)), example.getDriversLicense().toLowerCase() + "%"));
        }

        if (StringUtils.isNotBlank(example.getAddress())){
            predicates.add(cb.like(cb.lower(root.get(Citation_.address)), example.getAddress().toLowerCase() + "%"));
        }

        return andTogether(predicates, cb);
    }

    private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
        return cb.and(predicates.toArray(new Predicate[0]));
    }

}
