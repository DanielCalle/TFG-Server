package es.ucm.fdi.tfg.app.repository;

import org.springframework.data.repository.CrudRepository;
import es.ucm.fdi.tfg.app.entity.Film;

public interface FilmRepository extends CrudRepository<Film, String> {

}