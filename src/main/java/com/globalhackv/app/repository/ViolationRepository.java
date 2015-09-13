package com.globalhackv.app.repository;

        import com.globalhackv.app.domain.Citation;
        import com.globalhackv.app.domain.Violation;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
        import org.springframework.data.repository.query.Param;
        import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface ViolationRepository extends JpaRepository<Violation,Long>, JpaSpecificationExecutor {
    List<Violation> findById(@Param("id") String id);
    List<Violation> findByCitationNumber(long citationNumber);
    
    @Modifying
    @Transactional
    @Query("update Violation v set v.fineAmount= ?1 , v.status= ?2 where v.violationNumber = ?3")
    void updateViolationAmountAndStatus(BigDecimal fineAmount, String status, String violationNumber);
    
}
