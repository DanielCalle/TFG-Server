package es.ucm.fdi.tfg.app.sa;

import java.util.List;

import es.ucm.fdi.tfg.app.transfer.TFilm;

public interface SAFilm {
	public TFilm create(TFilm tFilm);
	
	public TFilm update(TFilm tFilm);
	
	public String delete(String uuid);
	
	public TFilm read(String uuid);
	
	public List<TFilm> readAll();
}
