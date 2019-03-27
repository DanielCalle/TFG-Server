package es.ucm.fdi.tfg.app.controller;

import java.util.List;

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

import es.ucm.fdi.tfg.app.entity.UserApp;
import es.ucm.fdi.tfg.app.sa.SAFactory;
import es.ucm.fdi.tfg.app.sa.SAUser;
import es.ucm.fdi.tfg.app.transfer.TFriendship;
import es.ucm.fdi.tfg.app.transfer.TPlan;
import es.ucm.fdi.tfg.app.transfer.TUser;
import es.ucm.fdi.tfg.app.transfer.TUserFilm;

@Controller
@RequestMapping("/users")
public class UserController {

	@GetMapping({ "", "/" })
	@ResponseBody
	public Iterable<TUser> getAllUsers() {
		SAFactory saFactory = SAFactory.getInstance();
		SAUser saUserApp = saFactory.generateSAUser();

		return saUserApp.readAll();
	}

	@PostMapping({ "", "/" })
	@ResponseBody
	public ResponseEntity<TUser> save(@RequestBody TUser tUser) {

		if (tUser.getUuid() != null && tUser.getName() != null && tUser.getEmail() != null
				&& tUser.getPassword() != null && tUser.getUuid() != "" && tUser.getName() != ""
				&& tUser.getEmail() != "" && tUser.getPassword() != "") {

			SAFactory saFactory = SAFactory.getInstance();
			SAUser saUserApp = saFactory.generateSAUser();
			TUser response = saUserApp.create(tUser);

			if (response != null)
				return ResponseEntity.status(HttpStatus.CREATED).body(tUser);

			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

	}

	@GetMapping("/{uuid}")
	@ResponseBody
	public ResponseEntity<TUser> getUserById(@PathVariable String uuid) {
		SAFactory saFactory = SAFactory.getInstance();
		SAUser saUserApp = saFactory.generateSAUser();
		TUser tUser = saUserApp.read(uuid);

		if (tUser != null)
			return ResponseEntity.status(HttpStatus.OK).body(tUser);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@DeleteMapping("/{uuid}")
	@ResponseBody
	public ResponseEntity<UserApp> delete(@PathVariable String uuid) {
		SAFactory saFactory = SAFactory.getInstance();
		SAUser saUserApp = saFactory.generateSAUser();
		String response = saUserApp.delete(uuid);

		if (response != null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping("/{uuid}/friendships")
	@ResponseBody
	public ResponseEntity<List<TFriendship>> getFriends(@PathVariable String uuid) {
		SAFactory saFactory = SAFactory.getInstance();
		SAUser saUserApp = saFactory.generateSAUser();
		List<TFriendship> listFriendships = saUserApp.getFriends(uuid);

		if (listFriendships != null)
			return ResponseEntity.status(HttpStatus.OK).body(listFriendships);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping("/{uuid}/plans")
	@ResponseBody
	public ResponseEntity<List<TPlan>> getPlans(@PathVariable String uuid) {
		SAFactory saFactory = SAFactory.getInstance();
		SAUser saUserApp = saFactory.generateSAUser();
		List<TPlan> listPlans = saUserApp.getPlans(uuid);

		if (listPlans != null)
			return ResponseEntity.status(HttpStatus.OK).body(listPlans);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping("/{uuid}/films")
	@ResponseBody
	public ResponseEntity<List<TUserFilm>> getFilms(@PathVariable String uuid) {
		SAFactory saFactory = SAFactory.getInstance();
		SAUser saUserApp = saFactory.generateSAUser();
		List<TUserFilm> listFilms = saUserApp.getFilms(uuid);

		if (listFilms != null)
			return ResponseEntity.status(HttpStatus.OK).body(listFilms);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

}