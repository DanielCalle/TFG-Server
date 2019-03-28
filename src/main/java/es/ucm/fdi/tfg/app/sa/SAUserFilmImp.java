package es.ucm.fdi.tfg.app.sa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

				userFilm = userFilmRepository.save(userFilm);

				return tUserFilm;
			}

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
	public TUserFilm read(String userUuid, String filmUuid) {
		UserFilmId id = new UserFilmId();
		id.setUser(userUuid);
		id.setFilm(filmUuid);
		Optional<UserFilm> optUserFilm = userFilmRepository.findById(id);
		if (optUserFilm.isPresent()) {
			TUserFilm tUserFilm = new TUserFilm();
			tUserFilm.setUserUuid(userUuid);
			tUserFilm.setUserUuid(userUuid);
			tUserFilm.setDate(optUserFilm.get().getDate().toString());

			return tUserFilm;
		}

		return null;
	}

	@Override
	public List<TUserFilm> readAll() {
		List<TUserFilm> listTUserFilm = new ArrayList<>();
		Iterable<UserFilm> listUserFilm = userFilmRepository.findAll();

		for (UserFilm userFilm : listUserFilm) {
			TUserFilm tUserFilm = new TUserFilm();
			tUserFilm.setUserUuid(userFilm.getUserApp().getUuid());
			tUserFilm.setFilmUuid(userFilm.getFilm().getUuid());
			tUserFilm.setDate(userFilm.getDate().toString());
			listTUserFilm.add(tUserFilm);
		}

		return listTUserFilm;
	}

}
