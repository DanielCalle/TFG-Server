package es.ucm.fdi.tfg.app.repository;

import org.springframework.data.repository.CrudRepository;
import es.ucm.fdi.tfg.app.entity.UserApp;

public interface UserRepository extends CrudRepository<UserApp, String> {

}