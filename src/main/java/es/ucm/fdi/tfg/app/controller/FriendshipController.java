package es.ucm.fdi.tfg.app.controller;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.ucm.fdi.tfg.app.entity.Film;
import es.ucm.fdi.tfg.app.entity.Friendship;
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
	
    @GetMapping("/all")
    @ResponseBody
    public Iterable<Friendship> getAllUsers() {
        return friendshipRepository.findAll();
    }

    @PostMapping("/save")
    @ResponseBody
    public Friendship save(
    		@RequestParam String requesterId,
    		@RequestParam String friendId,
    		@RequestParam int year,
    		@RequestParam int month,
    		@RequestParam int day,
    		@RequestParam int hrs,
    		@RequestParam int min,
    		@RequestParam boolean active
    		) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hrs+1);
        cal.set(Calendar.MINUTE, min);
        Date date = cal.getTime();
        Optional<UserApp> requester = userRepository.findById(requesterId);
        Optional<UserApp> friend = userRepository.findById(friendId);
        Friendship friendship = new Friendship();
        friendship.setRequester(requester.get());
        friendship.setFriend(friend.get());
        friendship.setDate(date);
        friendship.setActive(active);
    	return friendshipRepository.save(friendship);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Friendship> getUserById(@PathVariable String id) {
        return friendshipRepository.findById(id);
    }
}
