package es.ucm.fdi.tfg.app.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import es.ucm.fdi.tfg.app.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    // Find by email column
    public Optional<User> findByEmail(@Param("email") String email);

    // Find by uuid column
    public Optional<User> findByUuid(@Param("uuid") String uuid);

    // Find by name column (is acceptable when the value contains string name)
    public Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);

}