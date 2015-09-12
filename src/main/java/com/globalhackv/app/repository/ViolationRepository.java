package com.globalhackv.app.repository;

import com.globalhackv.app.domain.Violation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "violations", path = "violations")
public interface ViolationRepository extends PagingAndSortingRepository<Violation, Long> {
    List<Violation> findById(@Param("id") String id);
}