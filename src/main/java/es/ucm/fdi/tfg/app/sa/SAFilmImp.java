package es.ucm.fdi.tfg.app.sa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import es.ucm.fdi.tfg.app.entity.Film;
import es.ucm.fdi.tfg.app.repository.FilmRepository;
import es.ucm.fdi.tfg.app.transfer.TFilm;

/**
 * Application Service pattern
 */
@Service
public class SAFilmImp implements SAFilm {

	private static final int MAX_RESULTS = 10;

	@Autowired
	private FilmRepository filmRepository;

	// Mapping from entity to transfer and viceverse
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public TFilm create(TFilm tFilm) {
		Film film = filmRepository.save(modelMapper.map(tFilm, Film.class));

		if (film != null) {
			return modelMapper.map(film, TFilm.class);
		}

		return null;
	}

	@Override
	public TFilm update(TFilm tFilm) {
		Optional<Film> optUser = filmRepository.findById(tFilm.getId());

		if (optUser.isPresent()) {
			Film film = filmRepository.save(modelMapper.map(tFilm, Film.class));

			return modelMapper.map(film, TFilm.class);
		}

		return null;
	}

	@Override
	public Long delete(Long id) {
		Optional<Film> optFilm = filmRepository.findById(id);

		if (optFilm.isPresent()) {
			filmRepository.delete(optFilm.get());
			return id;
		}

		return null;
	}

	@Override
	public TFilm read(Long id) {
		Optional<Film> optFilm = filmRepository.findById(id);

		if (optFilm.isPresent()) {
			return modelMapper.map(optFilm.get(), TFilm.class);
		}

		return null;
	}

	@Override
	public TFilm findByUuid(String uuid) {
		Optional<Film> optFilm = filmRepository.findByUuid(uuid);

		if (optFilm.isPresent()) {
			return modelMapper.map(optFilm.get(), TFilm.class);
		}

		return null;
	}

	@Override
	public List<TFilm> readAll() {
		// Enabling paging option for large data
		Iterable<Film> listFilms = filmRepository.findAll(PageRequest.of(0, MAX_RESULTS));

		// see lambda stream in java 8
		return StreamSupport.stream(listFilms.spliterator(), false).map(film -> modelMapper.map(film, TFilm.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<TFilm> readAllByPremiere() {
		// Enabling paging option for large data
		Iterable<Film> listFilms = filmRepository
				.findAll(PageRequest.of(0, MAX_RESULTS, Sort.by("premiere").descending()));

		// see lambda stream in java 8
		return StreamSupport.stream(listFilms.spliterator(), false).map(film -> modelMapper.map(film, TFilm.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<TFilm> searchLikeByName(String name) {
		// Enabling paging option for large data
		Iterable<Film> listFilms = filmRepository.findByNameContainingIgnoreCase(name, PageRequest.of(0, MAX_RESULTS));

		// see lambda stream in java 8
		return StreamSupport.stream(listFilms.spliterator(), false).map(film -> modelMapper.map(film, TFilm.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<TFilm> getRecentFilms() {
		// Enabling paging option for large data
		Iterable<Film> listFilms = filmRepository.getRecentFilms(PageRequest.of(0, MAX_RESULTS, Sort.unsorted()));

		// see lambda stream in java 8
		return StreamSupport.stream(listFilms.spliterator(), false)
				.map(film -> modelMapper.map(film, TFilm.class)).collect(Collectors.toList());
	}

}
