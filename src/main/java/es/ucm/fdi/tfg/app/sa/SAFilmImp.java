package es.ucm.fdi.tfg.app.sa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import es.ucm.fdi.tfg.app.entity.Film;
import es.ucm.fdi.tfg.app.repository.FilmRepository;
import es.ucm.fdi.tfg.app.transfer.TFilm;

public class SAFilmImp implements SAFilm {
	
    @Autowired
    private FilmRepository filmRepository;

	@Override
	public TFilm create(TFilm tFilm) {
    	Optional<Film> optUser = filmRepository.findById(tFilm.getUuid());
    	if (!optUser.isPresent()) {
			Film film = new Film();
			film.setUuid(tFilm.getUuid());
			film.setName(tFilm.getName());
			film.setImageURL(tFilm.getImageURL());
			film.setInfoURL(tFilm.getInfoURL());
			film.setTrailerURL(tFilm.getTrailerURL());
			film.setRating(tFilm.getRating());
			film.setCountry(tFilm.getCountry());
			film.setDirector(tFilm.getDirector());
			film.setDuration(tFilm.getDuration());
			film.setGenre(tFilm.getGenre());
    		
			film = filmRepository.save(film);
    		
    		return tFilm;
    	}
    	
    	return null;
	}

	@Override
	public TFilm update(TFilm tFilm) {
    	Optional<Film> optUser = filmRepository.findById(tFilm.getUuid());
    	if (optUser.isPresent()) {
			Film film = optUser.get();
			film.setUuid(tFilm.getUuid());
			film.setName(tFilm.getName());
			film.setImageURL(tFilm.getImageURL());
			film.setInfoURL(tFilm.getInfoURL());
			film.setTrailerURL(tFilm.getTrailerURL());
			film.setRating(tFilm.getRating());
			film.setCountry(tFilm.getCountry());
			film.setDirector(tFilm.getDirector());
			film.setDuration(tFilm.getDuration());
			film.setGenre(tFilm.getGenre());
    		
			film = filmRepository.save(film);
    		
    		return tFilm;
    	}
    	
    	return null;
	}

	@Override
	public String delete(String uuid) {
		Optional<Film> optFilm = filmRepository.findById(uuid);
		
		if (optFilm.isPresent()) {
			filmRepository.delete(optFilm.get());
			
			return uuid;
		}
		
		return null;
	}

	@Override
	public TFilm read(String uuid) {
		Optional<Film> optFilm = filmRepository.findById(uuid);
		
		if (optFilm.isPresent()) {
			filmRepository.delete(optFilm.get());
			Film film = optFilm.get();
			TFilm tFilm = new TFilm();
			tFilm.setUuid(film.getUuid());
			tFilm.setName(film.getName());
			tFilm.setImageURL(film.getImageURL());
			tFilm.setInfoURL(film.getInfoURL());
			tFilm.setTrailerURL(film.getTrailerURL());
			tFilm.setRating(film.getRating());
			tFilm.setCountry(film.getCountry());
			tFilm.setDirector(film.getDirector());
			tFilm.setDuration(film.getDuration());
			tFilm.setGenre(film.getGenre());
			
			return tFilm;
		}
		
		return null;
	}

	@Override
	public List<TFilm> readAll() {
		List<TFilm> listTFilms = new ArrayList<>();
		Iterable<Film> listFilms = filmRepository.findAll();
		
		for (Film film : listFilms) {
			TFilm tFilm = new TFilm();
			tFilm.setUuid(film.getUuid());
			tFilm.setName(film.getName());
			tFilm.setImageURL(film.getImageURL());
			tFilm.setInfoURL(film.getInfoURL());
			tFilm.setTrailerURL(film.getTrailerURL());
			tFilm.setRating(film.getRating());
			tFilm.setCountry(film.getCountry());
			tFilm.setDirector(film.getDirector());
			tFilm.setDuration(film.getDuration());
			tFilm.setGenre(film.getGenre());
			listTFilms.add(tFilm);
		}
		
		return listTFilms;
	}

}
