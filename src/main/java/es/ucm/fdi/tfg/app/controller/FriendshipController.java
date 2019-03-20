package es.ucm.fdi.tfg.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

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

import es.ucm.fdi.tfg.app.entity.Friendship;
import es.ucm.fdi.tfg.app.entity.FriendshipId;
import es.ucm.fdi.tfg.app.entity.UserApp;
import es.ucm.fdi.tfg.app.repository.FriendshipRepository;
import es.ucm.fdi.tfg.app.repository.UserRepository;
import es.ucm.fdi.tfg.app.transfer.TFriendship;

@Controller
@RequestMapping("/friendships")
public class FriendshipController {
	
	@Autowired
    private FriendshipRepository friendshipRepository;
	@Autowired
    private UserRepository userRepository;
	
    @GetMapping({"", "/"})
    @ResponseBody
    public Iterable<Friendship> getAllUsers() {
        return friendshipRepository.findAll();
    }

    @PostMapping({"", "/"})
    @ResponseBody
    public ResponseEntity<Friendship> save(
    		@RequestBody TFriendship tFriendship
    		) {
    	
    	//Find users for to see exist
        Optional<UserApp> optRequester = userRepository.findById(tFriendship.getRequesterUuid());
        Optional<UserApp> optFriend = userRepository.findById(tFriendship.getFriendUuid());
        
        if (optRequester.isPresent() && optFriend.isPresent()) {
        	try {
        		//Parse string date, if it's not posible then return bad request
	        	SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
	        	df.setTimeZone(TimeZone.getTimeZone("UTC"));
	        	Date date = df.parse(tFriendship.getDate());
	        	
	        	//Find friendship of this users, if it's exist then return bad request
	        	FriendshipId friendshipId1 = new FriendshipId();
	        	FriendshipId friendshipId2 = new FriendshipId();
	        	friendshipId1.setFriend(tFriendship.getRequesterUuid());
	        	friendshipId1.setRequester(tFriendship.getFriendUuid());
	        	friendshipId2.setFriend(tFriendship.getFriendUuid());
	        	friendshipId2.setRequester(tFriendship.getRequesterUuid());
	        	Optional<Friendship> optFriendship1 = friendshipRepository.findById(friendshipId1);
	        	Optional<Friendship> optFriendship2 = friendshipRepository.findById(friendshipId2);
	        	
	        	if (!optFriendship1.isPresent() && !optFriendship2.isPresent()) {
	        		//Create Friendship and save
		            Friendship friendship = new Friendship();
		            friendship.setRequester(optRequester.get());
		            friendship.setFriend(optFriend.get());
		            friendship.setDate(date);
		            friendship.setActive(false);
		
		        	return ResponseEntity.status(HttpStatus.CREATED).body(friendshipRepository.save(friendship));
	        	}
	        	
	        	return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	        	
        	} catch (ParseException ex) {
        		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);	
        	}
        	
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }
    
    @PutMapping("/{requesterUuid}/{friendUuid}/accept")
    @ResponseBody
    public ResponseEntity<Friendship> accept(
    		@PathVariable String requesterUuid,
    		@PathVariable String friendUuid
    		) {
    	
    	//Find Friendship and if not exist return not found
    	FriendshipId friendshipId = new FriendshipId();
    	friendshipId.setFriend(friendUuid);
    	friendshipId.setRequester(requesterUuid);
    	Optional<Friendship> friendship = friendshipRepository.findById(friendshipId);
    	
    	if (friendship.isPresent()) {
    		//Change active to true and save the friendship
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
    	//Find the friendship
    	FriendshipId friendshipId = new FriendshipId();
    	friendshipId.setFriend(friendUuid);
    	friendshipId.setRequester(requesterUuid);
    	Optional<Friendship> friendship = friendshipRepository.findById(friendshipId);
    	
    	//if the friendship exist then remove it else return not found
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
