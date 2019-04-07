package es.ucm.fdi.tfg.app.sa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

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

	@Override
	public TPlan create(TPlan tPlan) {
		Optional<UserApp> creator = userRepository.findById(tPlan.getCreatorUuid());
		Optional<Film> film = filmRepository.findById(tPlan.getFilmUuid());
		// Check if user and film exist
		if (creator.isPresent() && film.isPresent()) {
			try {
				// Parse string date, if it's not posible then return bad request
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
				df.setTimeZone(TimeZone.getTimeZone("UTC"));
				Date date = df.parse(tPlan.getDate());

				// Create plan and save
				Plan plan = new Plan();
				plan.setCreator(creator.get());
				plan.setFilm(film.get());
				plan.setDate(date);
				planRepository.save(plan);

				return tPlan;

			} catch (ParseException ex) {
				return null;
			}

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
				planRepository.save(optPlan.get());

				TPlan tPlan = new TPlan();
				tPlan.setCreatorUuid(optPlan.get().getCreator().getUuid());
				tPlan.setFilmUuid(optPlan.get().getFilm().getUuid());
				tPlan.setDate(optPlan.get().getDate().toString());

				return tPlan;
			}
			;
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

			TPlan tPlan = new TPlan();
			tPlan.setCreatorUuid(optPlan.get().getCreator().getUuid());
			tPlan.setFilmUuid(optPlan.get().getFilm().getUuid());
			tPlan.setDate(optPlan.get().getDate().toString());
			return tPlan;
		}

		return null;
	}

	@Override
	public List<TPlan> readAll() {
		List<TPlan> listTPlan = new ArrayList<>();
		Iterable<Plan> listPlan = planRepository.findAll();

		for (Plan plan : listPlan) {
			TPlan tPlan = new TPlan();
			tPlan.setId(plan.getId());
			tPlan.setCreatorUuid(plan.getCreator().getUuid());
			tPlan.setFilmUuid(plan.getFilm().getUuid());
			tPlan.setDate(plan.getDate().toString());
			listTPlan.add(tPlan);
		}

		return listTPlan;
	}

	@Override
	public List<TUser> getJoinedUsers(Long id) {
		Optional<Plan> optPlan = planRepository.findById(id);
		List<TUser> listTUser = new ArrayList<>();
		if (optPlan.isPresent()) {

			for (UserApp userApp : optPlan.get().getJoinedUsers()) {
				TUser tUser = new TUser();
				tUser.setUuid(userApp.getUuid());
				tUser.setName(userApp.getName());
				tUser.setEmail(userApp.getEmail());
				tUser.setPassword(userApp.getPassword());
				tUser.setImageURL(userApp.getImageURL());
				listTUser.add(tUser);
			}

			return listTUser;
		}

		return null;
	}

}
