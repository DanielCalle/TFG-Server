package es.ucm.fdi.tfg.app.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import es.ucm.fdi.tfg.app.entity.Film;

public interface FilmRepository extends PagingAndSortingRepository<Film, Long> {

    public Page<Film> findByNameContainingIgnoreCase(String name, Pageable pageable);

    public Optional<Film> findByUuid(@Param("uuid") String uuid);

}