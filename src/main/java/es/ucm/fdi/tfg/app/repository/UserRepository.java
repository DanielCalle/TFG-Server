package es.ucm.fdi.tfg.app.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import es.ucm.fdi.tfg.app.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    public Optional<User> findByEmail(@Param("email") String email);

    public Iterable<User> findByNameContainingIgnoreCase(String name);

}