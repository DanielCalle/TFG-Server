package es.ucm.fdi.tfg.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import es.ucm.fdi.tfg.app.entity.UserFilm;
import es.ucm.fdi.tfg.app.entity.UserFilmId;

public interface UserFilmRepository extends PagingAndSortingRepository<UserFilm, UserFilmId> {

}