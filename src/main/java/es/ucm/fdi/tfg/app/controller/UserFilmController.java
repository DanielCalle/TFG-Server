package es.ucm.fdi.tfg.app.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.ucm.fdi.tfg.app.entity.UserFilmId;
import es.ucm.fdi.tfg.app.sa.SAFactory;
import es.ucm.fdi.tfg.app.sa.SAUserFilm;
import es.ucm.fdi.tfg.app.transfer.TUserFilm;

@Controller
@RequestMapping("/user-films")
public class UserFilmController {

	@Autowired
	SAUserFilm saUserFilm = SAFactory.getInstance().generateSAUserFilm();

	@GetMapping({ "", "/" })
	@ResponseBody
	public List<TUserFilm> getAll() {
		return saUserFilm.readAll();
	}

	@PostMapping({ "", "/" })
	@ResponseBody
	public ResponseEntity<TUserFilm> save(@RequestBody TUserFilm tUserFilm) {
		if (tUserFilm.getUserId() != null && tUserFilm.getFilmId() != null) {

			tUserFilm.setDate(new Date());
			TUserFilm response = saUserFilm.create(tUserFilm);

			if (response != null)
				return ResponseEntity.status(HttpStatus.CREATED).body(response);

		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@PutMapping("/{userId}/{filmId}/rate/{rating}")
	@ResponseBody
	public ResponseEntity<TUserFilm> rate(@PathVariable Long userId, @PathVariable Long filmId,
			@PathVariable float rating) {
		TUserFilm userFilm = saUserFilm.rate(userId, filmId, rating);

		if (userFilm != null)
			return ResponseEntity.status(HttpStatus.OK).body(userFilm);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@DeleteMapping("/{userId}/{filmId}")
	@ResponseBody
	public ResponseEntity<TUserFilm> delete(@PathVariable Long userId, @PathVariable Long filmId) {

		UserFilmId response = saUserFilm.delete(userId, filmId);

		if (response != null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@GetMapping("/{userId}/{filmId}")
	@ResponseBody
	public ResponseEntity<TUserFilm> getUserFilmsById(@PathVariable Long userId, @PathVariable Long filmId) {

		TUserFilm response = saUserFilm.read(userId, filmId);

		if (response != null)
			return ResponseEntity.status(HttpStatus.OK).body(response);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

	}
}