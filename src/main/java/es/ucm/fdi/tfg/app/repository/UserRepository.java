package es.ucm.fdi.tfg.app.repository;

import org.springframework.data.repository.CrudRepository;
import es.ucm.fdi.tfg.app.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

}