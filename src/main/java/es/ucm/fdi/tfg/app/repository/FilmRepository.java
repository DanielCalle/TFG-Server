package es.ucm.fdi.tfg.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import es.ucm.fdi.tfg.app.entity.Film;

public interface FilmRepository extends PagingAndSortingRepository<Film, Long> {

    public Page<Film> findByNameContainingIgnoreCase(String name, Pageable pageable);

}