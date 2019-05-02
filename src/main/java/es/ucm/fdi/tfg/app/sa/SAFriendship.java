package es.ucm.fdi.tfg.app.sa;

import java.util.List;

import es.ucm.fdi.tfg.app.entity.FriendshipId;
import es.ucm.fdi.tfg.app.transfer.TFriendship;

public interface SAFriendship {
	public TFriendship create(TFriendship tFriendship);
	
	public TFriendship accept(Long friendId, Long requesterId);
	
	public FriendshipId delete(Long id1, Long id2);
	
	public TFriendship read(Long id1, Long id2);
	
	public List<TFriendship> readAll();
}
