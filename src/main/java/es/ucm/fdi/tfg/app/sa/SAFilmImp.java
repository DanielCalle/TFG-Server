package es.ucm.fdi.tfg.app.sa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ucm.fdi.tfg.app.entity.Film;
import es.ucm.fdi.tfg.app.repository.FilmRepository;
import es.ucm.fdi.tfg.app.transfer.TFilm;

@Service
public class SAFilmImp implements SAFilm {
	
    @Autowired
	private FilmRepository filmRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

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
    		
			return modelMapper.map(film, TFilm.class);
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
    		
			return modelMapper.map(film, TFilm.class);
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
			return modelMapper.map(optFilm.get(), TFilm.class);
		}
		
		return null;
	}

	@Override
	public List<TFilm> readAll() {
		Iterable<Film> listFilms = filmRepository.findAll();
		
		return StreamSupport.stream(listFilms.spliterator(), false)
			.map(film -> modelMapper.map(film, TFilm.class))
			.collect(Collectors.toList());
	}

}
