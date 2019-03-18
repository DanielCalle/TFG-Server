package es.ucm.fdi.tfg.app.controller;

import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.ucm.fdi.tfg.app.entity.Film;
import es.ucm.fdi.tfg.app.entity.Plan;
import es.ucm.fdi.tfg.app.entity.UserApp;
import es.ucm.fdi.tfg.app.repository.FilmRepository;
import es.ucm.fdi.tfg.app.repository.PlanRepository;
import es.ucm.fdi.tfg.app.repository.UserRepository;

@Controller
@RequestMapping("/plan")
public class PlanController {
	
	@Autowired
    private PlanRepository planRepository;
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private FilmRepository filmRepository;
	
    @GetMapping("/")
    @ResponseBody
    public Iterable<Plan> getAllUsers() {
        return planRepository.findAll();
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<Plan> save(
    		@RequestParam String uuidCreator,
    		@RequestParam String uuidFilm,
    		@RequestParam int year,
    		@RequestParam int month,
    		@RequestParam int day,
    		@RequestParam int hrs,
    		@RequestParam int min
    		) {

        Optional<UserApp> creator = userRepository.findById(uuidCreator);
        Optional<Film> film = filmRepository.findById(uuidFilm);
        
        if (creator.isPresent() && film.isPresent()) {
        	if (year >= 0 &&
        			month > 0 && month <= 12 &&
        			day > 0 && day <= 31 &&
        			hrs >= 0 && hrs <= 24 &&
        			min >= 0 && min <= 60) {

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month-1);
                cal.set(Calendar.DAY_OF_MONTH, day);
                cal.set(Calendar.HOUR_OF_DAY, hrs+1);
                cal.set(Calendar.MINUTE, min);
                Date date = cal.getTime();
                Plan plan = new Plan();
                plan.setCreator(creator.get());
                plan.setFilm(film.get());
                plan.setDate(date);

            	return ResponseEntity.status(HttpStatus.CREATED).body(planRepository.save(plan));
        	}
        	else
        		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        else
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }
    
    @PutMapping("/join")
    @ResponseBody
    public ResponseEntity<Plan> accept(
    		@RequestParam long planId,
    		@RequestParam String uuidUser
    		) {
    	Optional<Plan> plan = planRepository.findById(planId);
    	Optional<UserApp> user = userRepository.findById(uuidUser);
    	if (plan.isPresent() &&  user.isPresent()) {
    		List<UserApp> joinedUsers = plan.get().getJoinedUsers();
    		joinedUsers.add(user.get());
    		plan.get().setJoinedUsers(joinedUsers);
    		planRepository.save(plan.get());
    		
    		return ResponseEntity.status(HttpStatus.OK).body(plan.get());
    	}
    	else
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable long id) {
    	Optional<Plan> plan = planRepository.findById(id);
    	if (plan.isPresent()) {
    		planRepository.delete(plan.get());
    		
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    	}
    	else
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Plan> getUserById(@PathVariable long id) {
    	Optional<Plan> plan = planRepository.findById(id);
    	if (plan.isPresent()) {
	    	return ResponseEntity.status(HttpStatus.OK).body(plan.get());
    	}
    	else
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    	
    }
}
