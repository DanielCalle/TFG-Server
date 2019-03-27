package es.ucm.fdi.tfg.app.sa;

import java.util.List;

import es.ucm.fdi.tfg.app.transfer.TFriendship;

public interface SAFriendship {
	public TFriendship create(TFriendship tFriendship);
	
	public TFriendship accept(TFriendship tFriendship);
	
	public String delete(String uuid1, String uuid2);
	
	public TFriendship read(String uuid1, String uuid2);
	
	public List<TFriendship> readAll();
}
