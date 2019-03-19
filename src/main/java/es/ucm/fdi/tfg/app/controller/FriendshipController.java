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

import es.ucm.fdi.tfg.app.entity.Friendship;
import es.ucm.fdi.tfg.app.entity.FriendshipId;
import es.ucm.fdi.tfg.app.entity.UserApp;
import es.ucm.fdi.tfg.app.repository.FriendshipRepository;
import es.ucm.fdi.tfg.app.repository.UserRepository;

@Controller
@RequestMapping("/friendship")
public class FriendshipController {
	
	@Autowired
    private FriendshipRepository friendshipRepository;
	@Autowired
    private UserRepository userRepository;
	
    @GetMapping("/")
    @ResponseBody
    public Iterable<Friendship> getAllUsers() {
        return friendshipRepository.findAll();
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<Friendship> save(
    		@RequestParam String requesterUuid,
    		@RequestParam String friendUuid,
    		@RequestParam int year,
    		@RequestParam int month,
    		@RequestParam int day,
    		@RequestParam int hrs,
    		@RequestParam int min
    		) {

        Optional<UserApp> requester = userRepository.findById(requesterUuid);
        Optional<UserApp> friend = userRepository.findById(friendUuid);
        
        if (requester.isPresent() && friend.isPresent()) {
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
                Friendship friendship = new Friendship();
                friendship.setRequester(requester.get());
                friendship.setFriend(friend.get());
                friendship.setDate(date);
                friendship.setActive(false);

            	return ResponseEntity.status(HttpStatus.CREATED).body(friendshipRepository.save(friendship));
        	}
        	
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }
    
    @PutMapping("/accept/{requesterUuid}/{friendUuid}")
    @ResponseBody
    public ResponseEntity<Friendship> accept(
    		@PathVariable String requesterUuid,
    		@PathVariable String friendUuid
    		) {
    	FriendshipId friendshipId = new FriendshipId();
    	friendshipId.setFriend(friendUuid);
    	friendshipId.setRequester(requesterUuid);
    	Optional<Friendship> friendship = friendshipRepository.findById(friendshipId);
    	if (friendship.isPresent()) {
    		friendship.get().setActive(true);
    		friendshipRepository.save(friendship.get());
    		
    		return ResponseEntity.status(HttpStatus.OK).body(friendship.get());
    	}
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    @DeleteMapping("/{requesterUuid}/{friendUuid}")
    @ResponseBody
    public ResponseEntity<Friendship> delete(
    		@PathVariable String requesterUuid,
    		@PathVariable String friendUuid
    		) {
    	FriendshipId friendshipId = new FriendshipId();
    	friendshipId.setFriend(friendUuid);
    	friendshipId.setRequester(requesterUuid);
    	Optional<Friendship> friendship = friendshipRepository.findById(friendshipId);
    	if (friendship.isPresent()) {
    		friendshipRepository.delete(friendship.get());
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    	}
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/{requesterUuid}/{friendUuid}")
    @ResponseBody
    public ResponseEntity<Friendship> getUserById(
    		@PathVariable String requesterUuid,
    		@PathVariable String friendUuid) {
    	FriendshipId friendshipId = new FriendshipId();
    	friendshipId.setFriend(friendUuid);
    	friendshipId.setRequester(requesterUuid);
    	Optional<Friendship> friendship = friendshipRepository.findById(friendshipId);
    	if (friendship.isPresent()) {
	    	return ResponseEntity.status(HttpStatus.OK).body(friendship.get());
    	}
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    	
    }
}
