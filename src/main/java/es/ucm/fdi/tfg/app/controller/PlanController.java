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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.ucm.fdi.tfg.app.sa.SAFactory;
import es.ucm.fdi.tfg.app.sa.SAPlan;
import es.ucm.fdi.tfg.app.sa.SAUser;
import es.ucm.fdi.tfg.app.transfer.TPlan;
import es.ucm.fdi.tfg.app.transfer.TUser;

@Controller
@RequestMapping("/plans")
public class PlanController {
	
	@Autowired
	SAPlan saPlan = SAFactory.getInstance().generateSAPlan();
	@Autowired
	SAUser saUser = SAFactory.getInstance().generateSAUser();

	@GetMapping({ "", "/" })
	@ResponseBody
	public Iterable<TPlan> getAllPlans() {
		return saPlan.readAll();
	}

	@PostMapping({ "", "/" })
	@ResponseBody
	public ResponseEntity<TPlan> save(@RequestBody TPlan tPlan) {

		if (tPlan.getCreatorId() != null && tPlan.getFilmId() != null) {

			TPlan response = saPlan.create(tPlan);

			if (response != null)
				return ResponseEntity.status(HttpStatus.CREATED).body(response);

			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

	}

	@PutMapping("{id}/join/{userId}")
	@ResponseBody
	public ResponseEntity<TPlan> join(@PathVariable long id, @PathVariable Long userId) {

		TPlan response = saPlan.join(id, userId);

		if (response != null)
			return ResponseEntity.status(HttpStatus.OK).body(response);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@PutMapping("{id}/quit/{userId}")
	@ResponseBody
	public ResponseEntity<TPlan> quit(@PathVariable long id, @PathVariable Long userId) {

		TPlan response = saPlan.quit(id, userId);

		if (response != null)
			return ResponseEntity.status(HttpStatus.OK).body(response);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<TPlan> delete(@PathVariable long id) {

		Long response = saPlan.delete(id);

		if (response != null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<TPlan> getPlansById(@PathVariable long id) {

		TPlan response = saPlan.read(id);

		if (response != null)
			return ResponseEntity.status(HttpStatus.OK).body(response);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@GetMapping("/{id}/joined-users")
	@ResponseBody
	public ResponseEntity<List<TUser>> getJoinedUsers(@PathVariable long id) {
		List<TUser> response = saPlan.getJoinedUsers(id);

		if (response != null)
			return ResponseEntity.status(HttpStatus.OK).body(response);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping("/{id}/users")
	@ResponseBody
	public ResponseEntity<List<TUser>> getUsers(@PathVariable long id) {
		List<TUser> response = new ArrayList<>();
		TPlan plan = saPlan.read(id);
		if (plan != null) {
			TUser creator = saUser.read(plan.getCreatorId());
			List<TUser> joinedUsers = saPlan.getJoinedUsers(id);
			if (joinedUsers != null && creator != null) {
				response.add(creator);
				response.addAll(joinedUsers);
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping("/search/{title}")
	@ResponseBody
	public ResponseEntity<List<TPlan>> searchLikeByName(@PathVariable String title) {
		return ResponseEntity.status(HttpStatus.OK).body(saPlan.searchLikeByTitle(title));
	}
}
