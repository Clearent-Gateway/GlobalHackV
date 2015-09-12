package com.globalhackv.app.repository;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "violations", path = "violations")
public interface ViolationRepository extends PagingAndSortingRepository<Violation, Long> {
    List<Person> findById(@Param("id") String id);
}