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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.ucm.fdi.tfg.app.sa.SAFactory;
import es.ucm.fdi.tfg.app.sa.SAFilm;
import es.ucm.fdi.tfg.app.sa.SARatingFilm;
import es.ucm.fdi.tfg.app.transfer.TFilm;
import es.ucm.fdi.tfg.app.transfer.TRatingFilm;

@Controller
@RequestMapping("/films")
public class FilmController {
	
	@Autowired
	SAFilm saFilm = SAFactory.getInstance().generateSAFilm();
	@Autowired
	SARatingFilm saRatingFilm = SAFactory.getInstance().generateSaRatingFilm();

	@GetMapping({ "", "/" })
	@ResponseBody
	public List<TFilm> getAllFilms() {

		return saFilm.readAll();
	}

	@PostMapping({ "", "/" })
	@ResponseBody
	public ResponseEntity<TFilm> save(@RequestBody TFilm tFilm) {

		if (tFilm.getUuid() != null && tFilm.getName() != null) {
			
			TFilm response = saFilm.create(tFilm);

			if (response != null)
				return ResponseEntity.status(HttpStatus.CREATED).body(response);

			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

	}

	@PutMapping({ "", "/" })
	@ResponseBody
	public ResponseEntity<TFilm> update(@RequestBody TFilm tFilm) {
		if (tFilm.getUuid() != null && tFilm.getName() != null) {

			TFilm response = saFilm.update(tFilm);

			if (response != null)
				return ResponseEntity.status(HttpStatus.OK).body(tFilm);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@GetMapping("/{uuid}")
	@ResponseBody
	public ResponseEntity<TFilm> getFilmById(@PathVariable String uuid) {

		TFilm tFilm = saFilm.read(uuid);

		if (tFilm != null)
			return ResponseEntity.status(HttpStatus.OK).body(tFilm);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@DeleteMapping("/{uuid}")
	@ResponseBody
	public ResponseEntity<TFilm> delete(@PathVariable String uuid) {

		String response = saFilm.delete(uuid);

		if (response != null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping("/{uuid}/user/{userUuid}/rating")
	@ResponseBody
	public ResponseEntity<TRatingFilm> getRating(@PathVariable String uuid, @PathVariable String userUuid) {
		TRatingFilm ratingFilm = saRatingFilm.read(userUuid, uuid);

		if (ratingFilm != null) {
			return ResponseEntity.status(HttpStatus.OK).body(ratingFilm);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@PostMapping("/rate")
	@ResponseBody
	public ResponseEntity<TRatingFilm> rate(@RequestBody TRatingFilm tRatingFilm){
		TRatingFilm ratingFilm = saRatingFilm.save(tRatingFilm);

		if (ratingFilm != null) {
			return ResponseEntity.status(HttpStatus.OK).body(ratingFilm);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

}
