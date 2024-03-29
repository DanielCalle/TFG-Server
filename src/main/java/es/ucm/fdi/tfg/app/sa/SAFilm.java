package es.ucm.fdi.tfg.app.sa;

import java.util.List;

import es.ucm.fdi.tfg.app.transfer.TFilm;

/**
 * Application Service pattern
 */
public interface SAFilm {
	public TFilm create(TFilm tFilm);
	
	public TFilm update(TFilm tFilm);
	
	public Long delete(Long uuid);
	
	public TFilm read(Long uuid);
	
	public List<TFilm> readAll();
	
	public List<TFilm> readAllByPremiere();

	public List<TFilm> searchLikeByName(String name);

	public TFilm findByUuid(String uuid);

	public List<TFilm> getRecentFilms();
}
