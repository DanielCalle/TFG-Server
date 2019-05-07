package es.ucm.fdi.tfg.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import es.ucm.fdi.tfg.app.entity.Film;

public interface FilmRepository extends PagingAndSortingRepository<Film, Long> {

    public Iterable<Film> findByNameContainingIgnoreCase(String name);

}