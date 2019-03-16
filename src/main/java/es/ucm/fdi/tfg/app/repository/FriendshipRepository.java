package es.ucm.fdi.tfg.app.repository;

import org.springframework.data.repository.CrudRepository;

import es.ucm.fdi.tfg.app.entity.Friendship;

public interface FriendshipRepository extends CrudRepository<Friendship, String> {

}