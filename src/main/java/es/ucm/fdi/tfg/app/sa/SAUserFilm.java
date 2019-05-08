package es.ucm.fdi.tfg.app.sa;

import java.util.List;

import es.ucm.fdi.tfg.app.entity.UserFilmId;
import es.ucm.fdi.tfg.app.transfer.TFilm;
import es.ucm.fdi.tfg.app.transfer.TUserFilm;

public interface SAUserFilm {
	public TUserFilm create(TUserFilm tUserFilm);
	
	public UserFilmId delete(Long userId, Long filmId);
	
	public TUserFilm read(Long userId, Long filmId);
	
	public List<TUserFilm> readAll();
	
	public TUserFilm save(TUserFilm tUserFilm);

	public TUserFilm rate(Long userId, Long filmId, float rating);

	public List<TFilm> getTredingFilms();
}
