package es.ucm.fdi.tfg.app.controller;

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
import es.ucm.fdi.tfg.app.sa.SAPlan;
import es.ucm.fdi.tfg.app.transfer.TPlan;

@Controller
@RequestMapping("/plans")
public class PlanController {

	@GetMapping({ "", "/" })
	@ResponseBody
	public Iterable<TPlan> getAllUsers() {
		SAFactory saFactory = SAFactory.getInstance();
		SAPlan saPlan = saFactory.generateSAPlan();

		return saPlan.readAll();
	}

	@PostMapping({ "", "/" })
	@ResponseBody
	public ResponseEntity<TPlan> save(@RequestBody TPlan tPlan) {

		if (tPlan.getCreatorUuid() != null && tPlan.getFilmUuid() != null && tPlan.getDate() != null
				&& tPlan.getCreatorUuid() != "" && tPlan.getFilmUuid() != "" && tPlan.getDate() != "") {

			SAFactory saFactory = SAFactory.getInstance();
			SAPlan saPlan = saFactory.generateSAPlan();
			TPlan response = saPlan.create(tPlan);

			if (response != null)
				return ResponseEntity.status(HttpStatus.CREATED).body(response);

			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

	}

	@PutMapping("{id}/join/{userUuid}")
	@ResponseBody
	public ResponseEntity<TPlan> join(@PathVariable long id, @PathVariable String userUuid) {

		SAFactory saFactory = SAFactory.getInstance();
		SAPlan saPlan = saFactory.generateSAPlan();
		TPlan response = saPlan.join(id, userUuid);

		if (response != null)
			return ResponseEntity.status(HttpStatus.OK).body(response);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<TPlan> delete(@PathVariable long id) {
		SAFactory saFactory = SAFactory.getInstance();
		SAPlan saPlan = saFactory.generateSAPlan();
		Long response = saPlan.delete(id);

		if (response != null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<TPlan> getUserById(@PathVariable long id) {
		SAFactory saFactory = SAFactory.getInstance();
		SAPlan saPlan = saFactory.generateSAPlan();
		TPlan response = saPlan.read(id);

		if (response != null)
			return ResponseEntity.status(HttpStatus.OK).body(response);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
}
