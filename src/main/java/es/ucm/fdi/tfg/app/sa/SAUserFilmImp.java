package es.ucm.fdi.tfg.app.sa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ucm.fdi.tfg.app.entity.Film;
import es.ucm.fdi.tfg.app.entity.UserApp;
import es.ucm.fdi.tfg.app.entity.UserFilm;
import es.ucm.fdi.tfg.app.entity.UserFilmId;
import es.ucm.fdi.tfg.app.repository.FilmRepository;
import es.ucm.fdi.tfg.app.repository.UserFilmRepository;
import es.ucm.fdi.tfg.app.repository.UserRepository;
import es.ucm.fdi.tfg.app.transfer.TUserFilm;

@Service
public class SAUserFilmImp implements SAUserFilm {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private UserFilmRepository userFilmRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public TUserFilm create(TUserFilm tUserFilm) {
		Optional<UserApp> optUser = userRepository.findById(tUserFilm.getUserUuid());
		Optional<Film> optFilm = filmRepository.findById(tUserFilm.getFilmUuid());

		// Check if user and film exist
		if (optUser.isPresent() && optFilm.isPresent()) {
			UserFilmId id = new UserFilmId();
			id.setFilm(tUserFilm.getFilmUuid());
			id.setUser(tUserFilm.getUserUuid());
			Optional<UserFilm> optUserFilm = userFilmRepository.findById(id);
			if (!optUserFilm.isPresent()) {
				UserFilm userFilm = new UserFilm();
				userFilm.setFilm(optFilm.get());
				userFilm.setUserApp(optUser.get());
				userFilm.setDate(new Date());
				userFilm.setRating(tUserFilm.getRating());

				userFilm = userFilmRepository.save(userFilm);
				return modelMapper.map(userFilm, TUserFilm.class);
			}

		}

		return null;
	}

	@Override
	public TUserFilm save(TUserFilm tUserFilm) {
		Optional<UserApp> user = userRepository.findById(tUserFilm.getUserUuid());
		Optional<Film> film = filmRepository.findById(tUserFilm.getFilmUuid());

		if (user.isPresent() && film.isPresent()) {
			UserFilm userFilm = new UserFilm();
			userFilm.setUserApp(user.get());
			userFilm.setFilm(film.get());
			userFilm.setRating(tUserFilm.getRating());
			
			userFilm = userFilmRepository.save(userFilm);
			return modelMapper.map(userFilm, TUserFilm.class);
		}
		return null;
	}

	@Override
	public UserFilmId delete(String userUuid, String filmUuid) {
		UserFilmId id = new UserFilmId();
		id.setUser(userUuid);
		id.setFilm(filmUuid);

		Optional<UserFilm> optUserFilm = userFilmRepository.findById(id);

		if (optUserFilm.isPresent()) {
			userFilmRepository.delete(optUserFilm.get());
			return id;
		}

		return null;
	}

	@Override
	public TUserFilm rate(String userUuid, String filmUuid, float rating) {
		UserFilmId id = new UserFilmId();
		id.setUser(userUuid);
		id.setFilm(filmUuid);

		Optional<UserFilm> optUserFilm = userFilmRepository.findById(id);

		if (optUserFilm.isPresent()) {
			UserFilm userFilm = optUserFilm.get();
			userFilm.setRating(rating);
			userFilm = userFilmRepository.save(userFilm);
			return modelMapper.map(userFilm, TUserFilm.class);
		}

		return null;
	}

	@Override
	public TUserFilm read(String userUuid, String filmUuid) {
		UserFilmId id = new UserFilmId();
		id.setUser(userUuid);
		id.setFilm(filmUuid);

		Optional<UserFilm> optUserFilm = userFilmRepository.findById(id);

		if (optUserFilm.isPresent()) {
			return modelMapper.map(optUserFilm.get(), TUserFilm.class);
		}

		return null;
	}

	@Override
	public List<TUserFilm> readAll() {
		Iterable<UserFilm> listUserFilm = userFilmRepository.findAll();

		return StreamSupport.stream(listUserFilm.spliterator(), false)
				.map(userFilm -> modelMapper.map(userFilm, TUserFilm.class))
				.collect(Collectors.toList());
	}

}
