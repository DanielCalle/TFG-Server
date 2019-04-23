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
import es.ucm.fdi.tfg.app.entity.Friendship;
import es.ucm.fdi.tfg.app.entity.FriendshipId;
import es.ucm.fdi.tfg.app.entity.RatingFilm;
import es.ucm.fdi.tfg.app.entity.RatingFilmId;
import es.ucm.fdi.tfg.app.entity.UserApp;
import es.ucm.fdi.tfg.app.repository.FilmRepository;
import es.ucm.fdi.tfg.app.repository.FriendshipRepository;
import es.ucm.fdi.tfg.app.repository.RatingFilmRepository;
import es.ucm.fdi.tfg.app.repository.UserRepository;
import es.ucm.fdi.tfg.app.transfer.TFriendship;
import es.ucm.fdi.tfg.app.transfer.TRatingFilm;

@Service
public class SARatingFilmImp implements SARatingFilm {

	@Autowired
	private RatingFilmRepository ratingFilmRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FilmRepository filmRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public TRatingFilm read(String user, String film) {
		RatingFilmId ratingFilmId = new RatingFilmId();
		ratingFilmId.setUser(user);
		ratingFilmId.setFilm(film);
		Optional<RatingFilm> ratingFilm = ratingFilmRepository.findById(ratingFilmId);

		// if the friendship exist then remove it else return not found
		if (ratingFilm.isPresent()) {
			return modelMapper.map(ratingFilm.get(), TRatingFilm.class);
		}

		return null;
	}

	@Override
	public TRatingFilm save(TRatingFilm tRatingFilm) {
		Optional<UserApp> user = userRepository.findById(tRatingFilm.getUserUuid());
		Optional<Film> film = filmRepository.findById(tRatingFilm.getFilmUuid());

		if (user.isPresent() && film.isPresent()) {
			RatingFilm ratingFilm = new RatingFilm();
			ratingFilm.setUser(user.get());
			ratingFilm.setFilm(film.get());
			ratingFilm.setRating(tRatingFilm.getRating());
			
			ratingFilm = ratingFilmRepository.save(ratingFilm);
			return modelMapper.map(ratingFilm, TRatingFilm.class);
		}
		return null;
	}

}
