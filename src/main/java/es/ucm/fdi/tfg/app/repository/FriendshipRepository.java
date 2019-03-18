package es.ucm.fdi.tfg.app.repository;

import org.springframework.data.repository.CrudRepository;

import es.ucm.fdi.tfg.app.entity.Friendship;
import es.ucm.fdi.tfg.app.entity.FriendshipId;

public interface FriendshipRepository extends CrudRepository<Friendship, FriendshipId> {

}