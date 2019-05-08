package es.ucm.fdi.tfg.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import es.ucm.fdi.tfg.app.entity.Recommendation;
import es.ucm.fdi.tfg.app.entity.RecommendationId;

public interface RecommendationRepository extends PagingAndSortingRepository<Recommendation, RecommendationId> {

    public Iterable<Recommendation> findAllByUserId(@Param("user_id") long id);

}