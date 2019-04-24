package es.ucm.fdi.tfg.app.controller;

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
	public TUserFilm save(@RequestBody TUserFilm tUserFilm) {
		if (tUserFilm.getUserUuid() != null && tUserFilm.getFilmUuid() != null && tUserFilm.getDate() != null
				&& tUserFilm.getUserUuid() != "" && tUserFilm.getFilmUuid() != "" && tUserFilm.getDate() != "") {

			TUserFilm response = saUserFilm.create(tUserFilm);

			if (response != null)
				return response;

		}

		return null;

	}

	@DeleteMapping("/{userUuid}/{filmUuid}")
	@ResponseBody
	public ResponseEntity<TUserFilm> delete(@PathVariable String userUuid, @PathVariable String filmUuid) {

		UserFilmId response = saUserFilm.delete(userUuid, filmUuid);

		if (response != null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@GetMapping("/{userUuid}/{filmUuid}")
	@ResponseBody
	public ResponseEntity<TUserFilm> getUserFilmsById(@PathVariable String userUuid, @PathVariable String filmUuid) {

		TUserFilm response = saUserFilm.read(userUuid, filmUuid);

		if (response != null)
			return ResponseEntity.status(HttpStatus.OK).body(response);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

	}

	@GetMapping("/{userUuid}/{filmUuid}/rating")
	@ResponseBody
	public ResponseEntity<TUserFilm> getRating(@PathVariable String userUuid, @PathVariable String filmUuid) {
		TUserFilm userFilm = saUserFilm.read(userUuid, filmUuid);

		if (userFilm != null) {
			return ResponseEntity.status(HttpStatus.OK).body(userFilm);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@PostMapping("/rate")
	@ResponseBody
	public ResponseEntity<TUserFilm> rate(@RequestBody TUserFilm tUserFilm){
		TUserFilm userFilm = saUserFilm.save(tUserFilm);

		if (userFilm != null) {
			return ResponseEntity.status(HttpStatus.OK).body(userFilm);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
}