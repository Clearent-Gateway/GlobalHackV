package com.globalhackv.app.repository;

import com.globalhackv.app.domain.Citation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CitationRepository extends JpaRepository<Citation,Long>, JpaSpecificationExecutor {

    List<Citation> findByCitationNumber(long citationNumber);

}
