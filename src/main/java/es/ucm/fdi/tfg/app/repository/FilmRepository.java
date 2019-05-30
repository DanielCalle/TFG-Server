package es.ucm.fdi.tfg.app.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import es.ucm.fdi.tfg.app.entity.Film;

public interface FilmRepository extends PagingAndSortingRepository<Film, Long> {

    // Find by name column (is acceptable when the value contains string name)
    public Page<Film> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // Find by column uuid
    public Optional<Film> findByUuid(@Param("uuid") String uuid);

    // Gets the recent movies
    @Query(
        value = "select * from film order by premiere desc", 
        nativeQuery = true
    )
    public Page<Film> getRecentFilms(Pageable pageable);

}