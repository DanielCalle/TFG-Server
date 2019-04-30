package es.ucm.fdi.tfg.app.repository;

import org.springframework.data.repository.CrudRepository;

import es.ucm.fdi.tfg.app.entity.Plan;

public interface PlanRepository extends CrudRepository<Plan, Long> {

    public Iterable<Plan> findByTitleContainingIgnoreCase(String title);

}
