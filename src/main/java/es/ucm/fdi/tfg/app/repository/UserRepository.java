package es.ucm.fdi.tfg.app.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import es.ucm.fdi.tfg.app.entity.UserApp;

public interface UserRepository extends CrudRepository<UserApp, Long> {

    public Optional<UserApp> findByEmail(@Param("email") String email);

    public Iterable<UserApp> findByNameContainingIgnoreCase(String name);

}