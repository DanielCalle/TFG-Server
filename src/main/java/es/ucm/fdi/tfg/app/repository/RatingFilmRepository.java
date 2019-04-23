package es.ucm.fdi.tfg.app.repository;

import org.springframework.data.repository.CrudRepository;

import es.ucm.fdi.tfg.app.entity.RatingFilm;
import es.ucm.fdi.tfg.app.entity.RatingFilmId;

public interface RatingFilmRepository extends CrudRepository<RatingFilm, RatingFilmId> {

}