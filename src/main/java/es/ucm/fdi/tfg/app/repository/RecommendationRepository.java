package es.ucm.fdi.tfg.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import es.ucm.fdi.tfg.app.entity.Recommendation;
import es.ucm.fdi.tfg.app.entity.RecommendationId;

public interface RecommendationRepository extends PagingAndSortingRepository<Recommendation, RecommendationId> {

    // Find by user id
    public Iterable<Recommendation> findAllByUserId(@Param("user_id") long id);

    // Find by user id with paging config
    public Page<Recommendation> findAllByUserId(@Param("user_id") long id, Pageable pageable);

}