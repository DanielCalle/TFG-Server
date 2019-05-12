package es.ucm.fdi.tfg.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import es.ucm.fdi.tfg.app.entity.Plan;

public interface PlanRepository extends PagingAndSortingRepository<Plan, Long> {

    // Find by title column (is acceptable when the value contains string title)
    public Iterable<Plan> findByTitleContainingIgnoreCase(String title);

    // Finds all plans where the user is involved
    @Query(
        value = "select * from plan where creator_id = :userId or id in (select plan_joined_users.plan_id from plan_joined_users where joined_users_id = :userId)",
        nativeQuery = true
    )
    public Page<Plan> getPlansByUserId(@Param("userId") Long userId, Pageable pageable);
}
