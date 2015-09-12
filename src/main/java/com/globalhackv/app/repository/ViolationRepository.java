package com.globalhackv.app.repository;

        import com.globalhackv.app.domain.Citation;
        import com.globalhackv.app.domain.Violation;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
        import org.springframework.data.repository.PagingAndSortingRepository;
        import org.springframework.data.repository.query.Param;
        import org.springframework.data.rest.core.annotation.RepositoryRestResource;

        import java.util.List;

public interface ViolationRepository extends JpaRepository<Violation,Long>, JpaSpecificationExecutor {
    List<Violation> findById(@Param("id") String id);
    List<Violation> findByCitationNumber(long citationNumber);
}