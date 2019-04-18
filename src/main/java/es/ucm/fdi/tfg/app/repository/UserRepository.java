package es.ucm.fdi.tfg.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.ucm.fdi.tfg.app.entity.UserApp;

public interface UserRepository extends CrudRepository<UserApp, String> {

    public Optional<UserApp> findByEmail(@Param("email") String email);

}