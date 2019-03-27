package es.ucm.fdi.tfg.app.sa;

import java.util.List;

import es.ucm.fdi.tfg.app.transfer.TUserFilm;

public interface SAUserFilm {
	public TUserFilm create(TUserFilm tUserFilm);
	
	public String delete(String userUuid, String filmUuid);
	
	public TUserFilm read(String userUuid, String filmUuid);
	
	public List<TUserFilm> readAll();
}
