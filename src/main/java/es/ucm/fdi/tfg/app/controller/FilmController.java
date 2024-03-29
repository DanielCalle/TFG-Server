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
import es.ucm.fdi.tfg.app.transfer.TFilm;

@Controller
@RequestMapping("/films")
public class FilmController {

	@Autowired
	SAFilm saFilm = SAFactory.getInstance().generateSAFilm();

	@GetMapping({ "", "/" })
	@ResponseBody
	public ResponseEntity<List<TFilm>> getAllFilms() {
		return ResponseEntity.status(HttpStatus.OK).body(saFilm.readAll());
	}

	@PostMapping({ "", "/" })
	@ResponseBody
	public ResponseEntity<TFilm> save(@RequestBody TFilm tFilm) {
		TFilm response = saFilm.create(tFilm);

		if (response != null)
			return ResponseEntity.status(HttpStatus.CREATED).body(response);

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@PutMapping({ "", "/" })
	@ResponseBody
	public ResponseEntity<TFilm> update(@RequestBody TFilm tFilm) {
		if (tFilm.getId() != null && tFilm.getName() != null) {

			TFilm response = saFilm.update(tFilm);

			if (response != null)
				return ResponseEntity.status(HttpStatus.OK).body(tFilm);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@GetMapping("/search/{name}")
	@ResponseBody
	public ResponseEntity<List<TFilm>> searchLikeByName(@PathVariable String name) {
		return ResponseEntity.status(HttpStatus.OK).body(saFilm.searchLikeByName(name));
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<TFilm> getFilmById(@PathVariable Long id) {

		TFilm tFilm = saFilm.read(id);

		if (tFilm != null)
			return ResponseEntity.status(HttpStatus.OK).body(tFilm);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping("/uuid/{uuid}")
	@ResponseBody
	public ResponseEntity<TFilm> getFilmByUuid(@PathVariable String uuid) {
		TFilm tFilm = saFilm.findByUuid(uuid);

		if (tFilm != null)
			return ResponseEntity.status(HttpStatus.OK).body(tFilm);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<TFilm> delete(@PathVariable Long id) {

		Long response = saFilm.delete(id);

		if (response != null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

}
