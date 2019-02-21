package es.ucm.fdi.tfg.app.repository;

import org.springframework.data.repository.CrudRepository;

import es.ucm.fdi.tfg.app.entity.UserFilm;

public interface UserFilmRepository extends CrudRepository<UserFilm, String> {

}