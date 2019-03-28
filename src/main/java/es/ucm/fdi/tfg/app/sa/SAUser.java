package es.ucm.fdi.tfg.app.sa;

import java.util.List;

import es.ucm.fdi.tfg.app.transfer.TFriendship;
import es.ucm.fdi.tfg.app.transfer.TPlan;
import es.ucm.fdi.tfg.app.transfer.TUser;
import es.ucm.fdi.tfg.app.transfer.TUserFilm;
import es.ucm.fdi.tfg.app.transfer.TFilm;


public interface SAUser {
	public TUser create(TUser tUser);
	
	public TUser update(TUser tUser);
	
	public String delete(String uuid);
	
	public TUser read(String uuid);
	
	public List<TUser> readAll();
	
	public List<TFriendship> getFriends(String uuid);
	
	public List<TPlan> getPlans(String uuid);
	
	public List<TFilm> getFilms(String uuid);
}
