package es.ucm.fdi.tfg.app.controller;

import java.util.List;
import java.util.Optional;

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

import es.ucm.fdi.tfg.app.entity.Film;
import es.ucm.fdi.tfg.app.entity.Plan;
import es.ucm.fdi.tfg.app.entity.UserApp;
import es.ucm.fdi.tfg.app.repository.FilmRepository;
import es.ucm.fdi.tfg.app.repository.PlanRepository;
import es.ucm.fdi.tfg.app.repository.UserRepository;

@Controller
@RequestMapping("/plans")
public class PlanController {

	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FilmRepository filmRepository;
	
	@GetMapping({"", "/"})
	@ResponseBody
	public Iterable<Plan> getAllUsers() {
		return planRepository.findAll();
	}

	@PostMapping({"", "/"})
	@ResponseBody
	public ResponseEntity<Plan> save(
			@RequestBody Plan plan
			) {
		String creatorUuid = plan.getCreator().getUuid();
		String filmUuid = plan.getFilm().getUuid();
		
		Optional<UserApp> creator = userRepository.findById(creatorUuid);
		Optional<Film> film = filmRepository.findById(filmUuid);
		//Check if user and film exist
		if (creator.isPresent() && film.isPresent()) {
			plan.setCreator(creator.get());
			plan.setFilm(film.get());
			plan.setDate(plan.getDate());

			return ResponseEntity.status(HttpStatus.CREATED).body(planRepository.save(plan));
			
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

	}
	
	@PutMapping("{id}/join/{userUuid}")
	@ResponseBody
	public ResponseEntity<Plan> accept(
			@PathVariable long id,
			@PathVariable String userUuid
			) {
		Optional<Plan> plan = planRepository.findById(id);
		Optional<UserApp> user = userRepository.findById(userUuid);
		if (plan.isPresent() &&  user.isPresent()) {
			if (plan.get().getCreator().getUuid() != user.get().getUuid()) {
				List<UserApp> joinedUsers = plan.get().getJoinedUsers();
				joinedUsers.add(user.get());
				plan.get().setJoinedUsers(joinedUsers);
				planRepository.save(plan.get());
				return ResponseEntity.status(HttpStatus.OK).body(plan.get());
			}
			
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Plan> delete(@PathVariable long id) {
		Optional<Plan> plan = planRepository.findById(id);
		if (plan.isPresent()) {
			planRepository.delete(plan.get());
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Plan> getUserById(@PathVariable long id) {
		Optional<Plan> plan = planRepository.findById(id);
		if (plan.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(plan.get());
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		
	}
}
