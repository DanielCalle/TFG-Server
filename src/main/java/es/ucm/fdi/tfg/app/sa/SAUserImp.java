package es.ucm.fdi.tfg.app.sa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ucm.fdi.tfg.app.entity.Friendship;
import es.ucm.fdi.tfg.app.entity.Plan;
import es.ucm.fdi.tfg.app.entity.UserApp;
import es.ucm.fdi.tfg.app.entity.UserFilm;
import es.ucm.fdi.tfg.app.entity.Film;
import es.ucm.fdi.tfg.app.repository.UserRepository;
import es.ucm.fdi.tfg.app.transfer.TFilm;
import es.ucm.fdi.tfg.app.transfer.TFriendship;
import es.ucm.fdi.tfg.app.transfer.TPlan;
import es.ucm.fdi.tfg.app.transfer.TUser;
import es.ucm.fdi.tfg.app.transfer.TUserFilm;
import es.ucm.fdi.tfg.app.transfer.TFilm;


@Service
public class SAUserImp implements SAUser{

	@Autowired
	private UserRepository userRepository;

	@Override
	public TUser create(TUser tUser) {
		Optional<UserApp> optUser = userRepository.findById(tUser.getUuid());
		if (!optUser.isPresent()) {
			UserApp user = new UserApp();
			user.setUuid(tUser.getUuid());
			user.setEmail(tUser.getEmail());
			user.setName(tUser.getName());
			user.setPassword(tUser.getPassword());

			user = userRepository.save(user);

			return tUser;
		}

		return null;
	}

	@Override
	public TUser update(TUser tUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(String uuid) {
		Optional<UserApp> optUser = userRepository.findById(uuid);

		if (optUser.isPresent()) {
			userRepository.delete(optUser.get());
			
			return uuid;
		}
		
		return null;
	}

	@Override
	public TUser read(String uuid) {
		Optional<UserApp> optUser = userRepository.findById(uuid);
		
		if (optUser.isPresent()) {
			TUser tUser = new TUser();
			tUser.setUuid(optUser.get().getUuid());
			tUser.setName(optUser.get().getName());
			tUser.setEmail(optUser.get().getEmail());
			tUser.setPassword(optUser.get().getPassword());
			
			return tUser;
		}
		
		return null;
	}

	@Override
	public List<TUser> readAll() {
		List<TUser> listTUser = new ArrayList<>();
		Iterable<UserApp> listUserApp = userRepository.findAll();
		
		for (UserApp userApp : listUserApp) {
			TUser tUser = new TUser();
			tUser.setUuid(userApp.getUuid());
			tUser.setName(userApp.getName());
			tUser.setEmail(userApp.getEmail());
			tUser.setPassword(userApp.getPassword());
			listTUser.add(tUser);
		}
		
		return listTUser;
	}

	@Override
	public List<TFriendship> getFriends(String uuid) {
		Optional<UserApp> optUser = userRepository.findById(uuid);
		
		if (optUser.isPresent()) {
			List<TFriendship> listFriends = new ArrayList<>();
			for (Friendship friendship : optUser.get().getFriendRequests()) {
				TFriendship tFriendship = new TFriendship();
				tFriendship.setRequesterUuid(friendship.getRequester().getUuid());
				tFriendship.setFriendUuid(friendship.getFriend().getUuid());
				tFriendship.setDate(friendship.toString());
				listFriends.add(tFriendship);
			}
			for (Friendship friendship : optUser.get().getFriends()) {
				TFriendship tFriendship = new TFriendship();
				tFriendship.setRequesterUuid(friendship.getRequester().getUuid());
				tFriendship.setFriendUuid(friendship.getFriend().getUuid());
				tFriendship.setDate(friendship.toString());
				listFriends.add(tFriendship);
			}
			
			return listFriends;
		}
		
		return null;
	}

	@Override
	public List<TPlan> getPlans(String uuid) {
		Optional<UserApp> optUser = userRepository.findById(uuid);
		
		if (optUser.isPresent()) {
			List<TPlan> listPlans = new ArrayList<>();
			for (Plan plan : optUser.get().getCreatedPlans()) {
				TPlan tPlan = new TPlan();
				tPlan.setId(plan.getId());
				tPlan.setCreatorUuid(plan.getCreator().getUuid());
				tPlan.setFilmUuid(plan.getFilm().getUuid());
				tPlan.setDate(plan.getDate().toString());
				listPlans.add(tPlan);
			}
			for (Plan plan : optUser.get().getJoinedPlans()) {
				TPlan tPlan = new TPlan();
				tPlan.setId(plan.getId());
				tPlan.setCreatorUuid(plan.getCreator().getUuid());
				tPlan.setFilmUuid(plan.getFilm().getUuid());
				tPlan.setDate(plan.getDate().toString());
				listPlans.add(tPlan);
			}
			
			return listPlans;
		}
		
		return null;
	}

	@Override
	public List<TFilm> getFilms(String uuid) {
		Optional<UserApp> optUser = userRepository.findById(uuid);
		
		if (optUser.isPresent()) {
			List<TFilm> listUserFilms = new ArrayList<>();
			for (UserFilm userFilm : optUser.get().getUserFilms()) {
				TFilm tFilm = new TFilm();
				tFilm.setUuid(userFilm.getFilm().getUuid());
				tFilm.setCountry(userFilm.getFilm().getCountry());
				tFilm.setDirector(userFilm.getFilm().getDirector());
				tFilm.setDuration(userFilm.getFilm().getDuration());
				tFilm.setGenre(userFilm.getFilm().getGenre());
				tFilm.setImageURL(userFilm.getFilm().getImageURL());
				tFilm.setInfoURL(userFilm.getFilm().getInfoURL());
				tFilm.setName(userFilm.getFilm().getName());
				tFilm.setRating(userFilm.getFilm().getRating());
				tFilm.setTrailerURL(userFilm.getFilm().getTrailerURL());
				listUserFilms.add(tFilm);
			}
			
			return listUserFilms;
		}
		
		return null;
	}

}
