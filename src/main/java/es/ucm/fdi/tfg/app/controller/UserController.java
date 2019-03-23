package es.ucm.fdi.tfg.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.ucm.fdi.tfg.app.entity.Friendship;
import es.ucm.fdi.tfg.app.entity.Plan;
import es.ucm.fdi.tfg.app.entity.UserApp;
import es.ucm.fdi.tfg.app.entity.UserFilm;
import es.ucm.fdi.tfg.app.repository.UserRepository;
import es.ucm.fdi.tfg.app.transfer.TUser;

@Controller
@RequestMapping("/users")
public class UserController{

    @Autowired
    private UserRepository userRepository;

    @GetMapping({"", "/"})
    @ResponseBody
    public Iterable<UserApp> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping({"", "/"})
    @ResponseBody
    public ResponseEntity<UserApp> save(@RequestBody TUser tUser) {
    	Optional<UserApp> optUser = userRepository.findById(tUser.getUuid());
    	if (!optUser.isPresent()) {
    		UserApp user = new UserApp();
    		user.setUuid(tUser.getUuid());
    		user.setEmail(tUser.getEmail());
    		user.setName(tUser.getName());
    		user.setPassword(tUser.getPassword());
    		
    		return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    	}
    	
    	return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        
    }

    @GetMapping("/{uuid}")
    @ResponseBody
    public Optional<UserApp> getUserById(@PathVariable String uuid) {
        return userRepository.findById(uuid);
    }
    
    @GetMapping("/{uuid}/friendships")
    @ResponseBody
    public ResponseEntity<List<Friendship>> getFriends(@PathVariable String uuid) {
    	Optional<UserApp> user = userRepository.findById(uuid);
    	if (user.isPresent()) {
    		List<Friendship> friendships = user.get().getFriends();
    		friendships.addAll(user.get().getFriendRequests());
    		return ResponseEntity.status(HttpStatus.OK).body(friendships);
    	}
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/{uuid}/plans")
    @ResponseBody
    public ResponseEntity<List<Plan>> getPlans(@PathVariable String uuid) {
    	Optional<UserApp> user = userRepository.findById(uuid);
	   	if (user.isPresent()) {
	   		List<Plan> plans = user.get().getCreatedPlans();
	   		plans.addAll(user.get().getJoinedPlans());
		    return ResponseEntity.status(HttpStatus.OK).body(plans);
	   	}
	   	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    @GetMapping("/{uuid}/films")
    @ResponseBody
    public ResponseEntity<List<UserFilm>> getFilms(@PathVariable String uuid) {
    	Optional<UserApp> user = userRepository.findById(uuid);
	   	if (user.isPresent()) {
	   		List<UserFilm> userFilms = user.get().getUserFilms();
		    return ResponseEntity.status(HttpStatus.OK).body(userFilms);
	   	}
	   	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


}