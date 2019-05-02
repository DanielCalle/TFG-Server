package es.ucm.fdi.tfg.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PutMapping;

import es.ucm.fdi.tfg.app.sa.SAFactory;
import es.ucm.fdi.tfg.app.sa.SAUser;
import es.ucm.fdi.tfg.app.transfer.TFriendship;
import es.ucm.fdi.tfg.app.transfer.TPlan;
import es.ucm.fdi.tfg.app.transfer.TUser;
import es.ucm.fdi.tfg.app.transfer.TFilm;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	SAUser saUserApp = SAFactory.getInstance().generateSAUser();

	@GetMapping({ "", "/" })
	@ResponseBody
	public Iterable<TUser> getAllUsers() {
		return saUserApp.readAll();
	}

	@PostMapping({ "", "/" })
	@ResponseBody
	public ResponseEntity<TUser> save(@RequestBody TUser tUser) {

		if (tUser != null) {

			TUser response = saUserApp.create(tUser);

			if (response != null)
				return ResponseEntity.status(HttpStatus.CREATED).body(response);

			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

	}

	@PutMapping({ "", "/" })
	@ResponseBody
	public ResponseEntity<TUser> update(@RequestBody TUser tUser) {
		if (tUser.getId() != null && tUser.getName() != null) {

			TUser response = saUserApp.update(tUser);

			if (response != null)
				return ResponseEntity.status(HttpStatus.OK).body(tUser);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<TUser> getUserById(@PathVariable Long id) {
		TUser tUser = saUserApp.read(id);

		if (tUser != null)
			return ResponseEntity.status(HttpStatus.OK).body(tUser);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<TUser> delete(@PathVariable Long id) {
		Long response = saUserApp.delete(id);

		if (response != null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping("/{id}/friendships")
	@ResponseBody
	public ResponseEntity<List<TFriendship>> getFriendships(@PathVariable Long id) {
		List<TFriendship> listFriendships = saUserApp.getFriends(id);

		if (listFriendships != null)
			return ResponseEntity.status(HttpStatus.OK).body(listFriendships);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping("/{id}/friends")
	@ResponseBody
	public ResponseEntity<List<TUser>> getFriends(@PathVariable Long id) {
		List<TFriendship> listFriendships = saUserApp.getFriends(id);

		List<Long> ids = listFriendships.stream().map(friendship -> {
			return friendship.getFriendId() == id ? friendship.getRequesterId() : friendship.getFriendId();
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(saUserApp.getUsers(ids));
	}

	@GetMapping("/{id}/plans")
	@ResponseBody
	public ResponseEntity<List<TPlan>> getPlans(@PathVariable Long id) {
		List<TPlan> listPlans = saUserApp.getPlans(id);

		if (listPlans != null)
			return ResponseEntity.status(HttpStatus.OK).body(listPlans);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping("/{id}/films")
	@ResponseBody
	public ResponseEntity<List<TFilm>> getFilms(@PathVariable Long id) {
		List<TFilm> listFilms = saUserApp.getFilms(id);

		if (listFilms != null)
			return ResponseEntity.status(HttpStatus.OK).body(listFilms);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<TUser> login(@RequestBody TUser user) {
		TUser result = saUserApp.login(user.getEmail(), user.getPassword());

		if (result != null) {
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}

	@GetMapping("/search/{name}")
	@ResponseBody
	public ResponseEntity<List<TUser>> searchLikeByName(@PathVariable String name) {
		return ResponseEntity.status(HttpStatus.OK).body(saUserApp.searchLikeByName(name));
	}

	@GetMapping("/{id}/friendships/plans")
	@ResponseBody
	public ResponseEntity<List<TPlan>> getFriendsPlans(@PathVariable Long id) {
		List<TFriendship> listFriendships = saUserApp.getFriends(id);
		if (listFriendships != null) {
			List<TPlan> result = new ArrayList<TPlan>();
			for (int i = 0; i < listFriendships.size(); i++) {
				if (listFriendships.get(i).isActive()) {
					List<TPlan> listPlans = saUserApp.getPlans(listFriendships.get(i).getFriendId());
					if (listPlans != null) {
						for (int j = 0; j < listPlans.size(); j++) {
							result.add(listPlans.get(j));
						}
					}
				}
			}
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

}