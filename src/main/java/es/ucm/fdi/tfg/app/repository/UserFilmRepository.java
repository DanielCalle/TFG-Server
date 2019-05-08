package es.ucm.fdi.tfg.app.repository;

import java.math.BigInteger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import es.ucm.fdi.tfg.app.entity.UserFilm;
import es.ucm.fdi.tfg.app.entity.UserFilmId;

public interface UserFilmRepository extends PagingAndSortingRepository<UserFilm, UserFilmId> {

    @Query(
        value = "select film_id from user_film group by film_id having count(film_id) >= (:num) order by avg(rating) desc", 
        nativeQuery = true
    )
    public Page<BigInteger> getTredingFilms(@Param("num") int num, Pageable pageable);

}