package es.ucm.fdi.tfg.app.sa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import es.ucm.fdi.tfg.app.entity.Film;
import es.ucm.fdi.tfg.app.entity.Plan;
import es.ucm.fdi.tfg.app.entity.User;
import es.ucm.fdi.tfg.app.repository.FilmRepository;
import es.ucm.fdi.tfg.app.repository.PlanRepository;
import es.ucm.fdi.tfg.app.repository.UserRepository;
import es.ucm.fdi.tfg.app.transfer.TPlan;
import es.ucm.fdi.tfg.app.transfer.TUser;

/**
 * Application Service pattern
 */
@Service
public class SAPlanImp implements SAPlan {

	private final static int MAX_RESULTS = 10;

	@Autowired
	private PlanRepository planRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FilmRepository filmRepository;

	// Mapping from entity to transfer and viceverse
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public TPlan create(TPlan tPlan) {
		Optional<User> creator = userRepository.findById(tPlan.getCreatorId());
		Optional<Film> film = filmRepository.findById(tPlan.getFilmId());
		// Check if user and film exist
		if (creator.isPresent() && film.isPresent()) {
				Plan plan = new Plan();
				plan.setTitle(tPlan.getTitle());
				plan.setCreator(creator.get());
				plan.setFilm(film.get());
				plan.setDate(tPlan.getDate());
				plan.setLocation(tPlan.getLocation());
				plan.setDescription(tPlan.getDescription());
				plan = planRepository.save(plan);

				return modelMapper.map(plan, TPlan.class);
		}

		return null;
	}

	@Override
	public TPlan join(Long id, Long userId) {
		Optional<Plan> optPlan = planRepository.findById(id);
		Optional<User> user = userRepository.findById(userId);

		if (optPlan.isPresent() && user.isPresent()) {
			if (optPlan.get().getCreator().getId() != user.get().getId()) {
				List<User> joinedUsers = optPlan.get().getJoinedUsers();
				joinedUsers.add(user.get());
				optPlan.get().setJoinedUsers(joinedUsers);
				Plan plan = planRepository.save(optPlan.get());
				return modelMapper.map(plan, TPlan.class);
			}
		}

		return null;
	}

	@Override
	public TPlan quit(Long id, Long userId) {
		Optional<Plan> optPlan = planRepository.findById(id);
		Optional<User> user = userRepository.findById(userId);

		if (optPlan.isPresent() && user.isPresent()) {
			if (optPlan.get().getCreator().getId() != user.get().getId()) {
				List<User> joinedUsers = optPlan.get().getJoinedUsers();
				joinedUsers.remove(user.get());
				optPlan.get().setJoinedUsers(joinedUsers);
				Plan plan = planRepository.save(optPlan.get());
				return modelMapper.map(plan, TPlan.class);
			}
		}

		return null;
	}

	@Override
	public Long delete(Long id) {
		Optional<Plan> plan = planRepository.findById(id);

		if (plan.isPresent()) {
			planRepository.delete(plan.get());
			return id;
		}

		return null;
	}

	@Override
	public TPlan read(Long id) {
		Optional<Plan> optPlan = planRepository.findById(id);

		if (optPlan.isPresent()) {
			return modelMapper.map(optPlan.get(), TPlan.class);
		}

		return null;
	}

	@Override
	public List<TPlan> readAll() {
		Iterable<Plan> listPlan = planRepository.findAll();

		// see lambda stream in java 8
		return StreamSupport.stream(listPlan.spliterator(), false).map(plan -> modelMapper.map(plan, TPlan.class))
		.collect(Collectors.toList());
	}

	@Override
	public List<TUser> getJoinedUsers(Long id) {
		Optional<Plan> optPlan = planRepository.findById(id);

		if (optPlan.isPresent()) {
			return optPlan.get().getJoinedUsers().stream().map(user -> modelMapper.map(user, TUser.class))
					.collect(Collectors.toList());
		}

		return null;
	}

	@Override
	public List<TPlan> getPlansByUserId(Long id) {
		// Enabling paging option for large data
		Iterable<Plan> listPlans = planRepository.getPlansByUserId(id, PageRequest.of(0, MAX_RESULTS));

		// see lambda stream in java 8
		return StreamSupport.stream(listPlans.spliterator(), false)
			.map(plan -> modelMapper.map(plan, TPlan.class))
			.collect(Collectors.toList());
	}

	@Override
	public List<TPlan> searchLikeByTitle(String title) {
		Iterable<Plan> listPlans = planRepository.findByTitleContainingIgnoreCase(title);
		
		// see lambda stream in java 8
		return StreamSupport.stream(listPlans.spliterator(), false)
			.map(plan -> modelMapper.map(plan, TPlan.class))
			.collect(Collectors.toList());
	}

}
