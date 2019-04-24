package es.ucm.fdi.tfg.app.controller;

import java.util.ArrayList;
import java.util.List;

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
import es.ucm.fdi.tfg.app.sa.SARatingFilm;
import es.ucm.fdi.tfg.app.sa.SAUser;
import es.ucm.fdi.tfg.app.transfer.TFriendship;
import es.ucm.fdi.tfg.app.transfer.TPlan;
import es.ucm.fdi.tfg.app.transfer.TRatingFilm;
import es.ucm.fdi.tfg.app.transfer.TUser;
import es.ucm.fdi.tfg.app.transfer.TUserFilm;
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
		if (tUser.getUuid() != null && tUser.getName() != null) {

			TUser response = saUserApp.update(tUser);

			if (response != null)
				return ResponseEntity.status(HttpStatus.OK).body(tUser);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@GetMapping("/{uuid}")
	@ResponseBody
	public ResponseEntity<TUser> getUserById(@PathVariable String uuid) {
		TUser tUser = saUserApp.read(uuid);

		if (tUser != null)
			return ResponseEntity.status(HttpStatus.OK).body(tUser);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@DeleteMapping("/{uuid}")
	@ResponseBody
	public ResponseEntity<TUser> delete(@PathVariable String uuid) {
		String response = saUserApp.delete(uuid);

		if (response != null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping("/{uuid}/friendships")
	@ResponseBody
	public ResponseEntity<List<TFriendship>> getFriends(@PathVariable String uuid) {
		List<TFriendship> listFriendships = saUserApp.getFriends(uuid);

		if (listFriendships != null)
			return ResponseEntity.status(HttpStatus.OK).body(listFriendships);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping("/{uuid}/plans")
	@ResponseBody
	public ResponseEntity<List<TPlan>> getPlans(@PathVariable String uuid) {
		List<TPlan> listPlans = saUserApp.getPlans(uuid);

		if (listPlans != null)
			return ResponseEntity.status(HttpStatus.OK).body(listPlans);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping("/{uuid}/films")
	@ResponseBody
	public ResponseEntity<List<TFilm>> getFilms(@PathVariable String uuid) {
		List<TFilm> listFilms = saUserApp.getFilms(uuid);

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

	@GetMapping("/{uuid}/friendships/plans")
	@ResponseBody
	public ResponseEntity<List<TPlan>> getFriendsPlans(@PathVariable String uuid) {
		List<TFriendship> listFriendships = saUserApp.getFriends(uuid);
		if (listFriendships != null) {
			List<TPlan> result = new ArrayList<TPlan>();
			for (int i = 0; i < listFriendships.size(); i++) {
				List<TPlan> listPlans = saUserApp.getPlans(listFriendships.get(i).getFriendUuid());
				if (listPlans != null) {
					for (int j = 0; j < listPlans.size(); j++) {
						result.add(listPlans.get(j));
					}
				}
			}
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

}