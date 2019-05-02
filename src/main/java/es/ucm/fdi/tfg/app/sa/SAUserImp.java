package es.ucm.fdi.tfg.app.sa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ucm.fdi.tfg.app.entity.Friendship;
import es.ucm.fdi.tfg.app.entity.Plan;
import es.ucm.fdi.tfg.app.entity.UserApp;
import es.ucm.fdi.tfg.app.entity.UserFilm;
import es.ucm.fdi.tfg.app.entity.UserApp;
import es.ucm.fdi.tfg.app.repository.UserRepository;
import es.ucm.fdi.tfg.app.transfer.TFilm;
import es.ucm.fdi.tfg.app.transfer.TFriendship;
import es.ucm.fdi.tfg.app.transfer.TPlan;
import es.ucm.fdi.tfg.app.transfer.TUser;

@Service
public class SAUserImp implements SAUser {

	@Autowired
	private UserRepository userRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public TUser create(TUser tUser) {
		UserApp user = userRepository.save(modelMapper.map(tUser, UserApp.class));
		return modelMapper.map(user, TUser.class);
	}

	@Override
	public TUser update(TUser tUser) {
		Optional<UserApp> optUser = userRepository.findById(tUser.getId());

		if (optUser.isPresent()) {
			UserApp user = userRepository.save(modelMapper.map(tUser, UserApp.class));
			return modelMapper.map(user, TUser.class);
		}

		return null;
	}

	@Override
	public Long delete(Long id) {
		Optional<UserApp> optUser = userRepository.findById(id);

		if (optUser.isPresent()) {
			userRepository.delete(optUser.get());
			return id;
		}

		return null;
	}

	@Override
	public TUser read(Long id) {
		Optional<UserApp> optUser = userRepository.findById(id);

		if (optUser.isPresent()) {
			return modelMapper.map(optUser.get(), TUser.class);
		}

		return null;
	}

	@Override
	public List<TUser> readAll() {
		Iterable<UserApp> listUserApp = userRepository.findAll();

		return StreamSupport.stream(listUserApp.spliterator(), false).map(user -> modelMapper.map(user, TUser.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<TFriendship> getFriends(Long id) {
		Optional<UserApp> optUser = userRepository.findById(id);

		if (optUser.isPresent()) {
			return Stream
					.concat(optUser.get().getFriendRequests().stream()
							.map(friendShip -> modelMapper.map(friendShip, TFriendship.class)),
							optUser.get().getFriends().stream()
									.map(friendShip -> modelMapper.map(friendShip, TFriendship.class)))
					.distinct().collect(Collectors.toList());
		}

		return new ArrayList<TFriendship>();
	}

	@Override
	public List<TUser> getUsers(List<Long> ids) {
		Iterable<UserApp> listUserApp = userRepository.findAllById(ids);

		return StreamSupport.stream(listUserApp.spliterator(), false).map(user -> modelMapper.map(user, TUser.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<TPlan> getPlans(Long id) {
		Optional<UserApp> optUser = userRepository.findById(id);

		if (optUser.isPresent()) {
			return Stream
					.concat(optUser.get().getCreatedPlans().stream().map(plan -> modelMapper.map(plan, TPlan.class)),
							optUser.get().getJoinedPlans().stream().map(plan -> modelMapper.map(plan, TPlan.class)))
					.distinct().collect(Collectors.toList());
		}

		return null;
	}

	@Override
	public List<TFilm> getFilms(Long id) {
		Optional<UserApp> optUser = userRepository.findById(id);

		if (optUser.isPresent()) {
			return optUser.get().getUserFilms().stream()
					.map(userFilm -> modelMapper.map(userFilm.getFilm(), TFilm.class)).collect(Collectors.toList());
		}

		return null;
	}

	@Override
	public TUser login(String email, String password) {
		Optional<UserApp> optUser = userRepository.findByEmail(email);

		if (optUser.isPresent() && optUser.get().getPassword().equalsIgnoreCase(password)) {
			return modelMapper.map(optUser.get(), TUser.class);
		}

		return null;
	}

	@Override
	public List<TUser> searchLikeByName(String name) {
		Iterable<UserApp> listUsers = userRepository.findByNameContainingIgnoreCase(name);

		return StreamSupport.stream(listUsers.spliterator(), false).map(user -> modelMapper.map(user, TUser.class))
				.collect(Collectors.toList());
	}

}
