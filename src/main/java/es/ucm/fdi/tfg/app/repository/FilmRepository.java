package es.ucm.fdi.tfg.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import es.ucm.fdi.tfg.app.entity.Film;

public interface FilmRepository extends CrudRepository<Film, String> {

    public Iterable<Film> findByNameContainingIgnoreCase(String name);

}