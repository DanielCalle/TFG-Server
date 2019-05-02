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
	
	public Long delete(Long id);
	
	public TUser read(Long id);
	
	public List<TUser> readAll();
	
	public List<TFriendship> getFriends(Long id);
	
	public List<TPlan> getPlans(Long id);
	
	public List<TFilm> getFilms(Long id);

	public TUser login(String email, String password);

	public List<TUser> getUsers(List<Long> ids);

	public List<TUser> searchLikeByName(String name);
}
