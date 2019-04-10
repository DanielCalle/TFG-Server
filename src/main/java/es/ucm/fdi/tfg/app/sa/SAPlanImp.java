package es.ucm.fdi.tfg.app.sa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
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
import es.ucm.fdi.tfg.app.entity.Plan;
import es.ucm.fdi.tfg.app.entity.UserApp;
import es.ucm.fdi.tfg.app.repository.FilmRepository;
import es.ucm.fdi.tfg.app.repository.PlanRepository;
import es.ucm.fdi.tfg.app.repository.UserRepository;
import es.ucm.fdi.tfg.app.transfer.TPlan;
import es.ucm.fdi.tfg.app.transfer.TUser;

@Service
public class SAPlanImp implements SAPlan {

	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FilmRepository filmRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public TPlan create(String creatorUuid, String filmUuid) {
		Optional<UserApp> creator = userRepository.findById(creatorUuid);
		Optional<Film> film = filmRepository.findById(filmUuid);
		// Check if user and film exist
		if (creator.isPresent() && film.isPresent()) {
			// Create plan and save
			Plan plan = new Plan();
			plan.setCreator(creator.get());
			plan.setFilm(film.get());
			plan.setDate(new Date());
			plan = planRepository.save(plan);

			return modelMapper.map(plan, TPlan.class);

		}

		return null;
	}

	@Override
	public TPlan join(Long id, String userUuid) {
		Optional<Plan> optPlan = planRepository.findById(id);
		Optional<UserApp> user = userRepository.findById(userUuid);

		if (optPlan.isPresent() && user.isPresent()) {
			if (optPlan.get().getCreator().getUuid() != user.get().getUuid()) {
				List<UserApp> joinedUsers = optPlan.get().getJoinedUsers();
				joinedUsers.add(user.get());
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

		return StreamSupport.stream(listPlan.spliterator(), false)
				.map(plan -> modelMapper.map(plan, TPlan.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<TUser> getJoinedUsers(Long id) {
		Optional<Plan> optPlan = planRepository.findById(id);
		
		if (optPlan.isPresent()) {
			return optPlan.get().getJoinedUsers().stream()
				.map(user -> modelMapper.map(user, TUser.class))
				.collect(Collectors.toList());
		}

		return null;
	}

}
