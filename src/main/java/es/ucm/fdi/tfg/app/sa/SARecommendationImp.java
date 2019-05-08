package es.ucm.fdi.tfg.app.sa;

import java.util.ArrayList;
import java.util.Date;
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
import es.ucm.fdi.tfg.app.entity.Recommendation;
import es.ucm.fdi.tfg.app.entity.RecommendationId;
import es.ucm.fdi.tfg.app.entity.User;
import es.ucm.fdi.tfg.app.entity.UserFilm;
import es.ucm.fdi.tfg.app.entity.UserFilmId;
import es.ucm.fdi.tfg.app.repository.FilmRepository;
import es.ucm.fdi.tfg.app.repository.PlanRepository;
import es.ucm.fdi.tfg.app.repository.RecommendationRepository;
import es.ucm.fdi.tfg.app.repository.UserFilmRepository;
import es.ucm.fdi.tfg.app.repository.UserRepository;
import es.ucm.fdi.tfg.app.transfer.TFilm;
import es.ucm.fdi.tfg.app.transfer.TRecommendation;
import es.ucm.fdi.tfg.app.transfer.TUserFilm;

@Service
public class SARecommendationImp implements SARecommendation {

	private static final int MAX_RESULTS = 100;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private RecommendationRepository recommendationRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public TRecommendation create(TRecommendation tRecommendation) {
		Optional<User> optUser = userRepository.findById(tRecommendation.getUserId());
		Optional<Film> optFilm = filmRepository.findById(tRecommendation.getFilmId());

		// Check if user and film exist
		if (optUser.isPresent() && optFilm.isPresent()) {
			Recommendation recommendation = new Recommendation();
			recommendation.setFilm(optFilm.get());
			recommendation.setUser(optUser.get());
			recommendation.setRating(tRecommendation.getRating());
			recommendation.setDate(tRecommendation.getDate());

			recommendation = recommendationRepository.save(recommendation);
			return modelMapper.map(recommendation, TRecommendation.class);

		}

		return null;
	}

	@Override
	public TRecommendation read(Long userId, long filmId) {
		RecommendationId id = new RecommendationId();
		id.setUser(userId);
		id.setFilm(filmId);

		Optional<Recommendation> optRecommendation = recommendationRepository.findById(id);

		if (optRecommendation.isPresent()) {
			return modelMapper.map(optRecommendation.get(), TRecommendation.class);
		}

		return null;
	}

	@Override
	public TRecommendation save(TRecommendation tRecommendation) {
		Optional<User> user = userRepository.findById(tRecommendation.getUserId());
		Optional<Film> film = filmRepository.findById(tRecommendation.getFilmId());

		if (user.isPresent() && film.isPresent()) {
			Recommendation recommendation = new Recommendation();
			recommendation.setUser(user.get());
			recommendation.setFilm(film.get());
			recommendation.setRating(tRecommendation.getRating());
			recommendation.setDate(tRecommendation.getDate());
			
			recommendation = recommendationRepository.save(recommendation);
			return modelMapper.map(recommendation, TRecommendation.class);
		}
		return null;
	}

	@Override
	public List<TRecommendation> readAll() {
		Iterable<Recommendation> listRecommendation = recommendationRepository.findAll(PageRequest.of(0, MAX_RESULTS));

		return StreamSupport.stream(listRecommendation.spliterator(), false)
				.map(recommendation -> modelMapper.map(recommendation, TRecommendation.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<TRecommendation> findByUserId(long id) {
		Iterable<Recommendation> listRecommendation = recommendationRepository.findAllByUserId(id);

		return StreamSupport.stream(listRecommendation.spliterator(), false)
				.map(recommendation -> modelMapper.map(recommendation, TRecommendation.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<TFilm> findFilmsByUserId(long id) {
		Iterable<Recommendation> listRecommendation = recommendationRepository.findAllByUserId(id, PageRequest.of(0, MAX_RESULTS, Sort.by("date").descending()));
		
		return StreamSupport.stream(listRecommendation.spliterator(), false)
				.map(recommendation -> modelMapper.map(recommendation.getFilm(), TFilm.class))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteAll() {
		recommendationRepository.deleteAll();
	}

}
