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

import es.ucm.fdi.tfg.app.entity.FriendshipId;
import es.ucm.fdi.tfg.app.sa.SAFactory;
import es.ucm.fdi.tfg.app.sa.SAFriendship;
import es.ucm.fdi.tfg.app.transfer.TFriendship;

@Controller
@RequestMapping("/friendships")
public class FriendshipController {

	@Autowired
	SAFriendship saFriendship = SAFactory.getInstance().generateSAFriendship();
	
	@GetMapping({ "", "/" })
	@ResponseBody
	public List<TFriendship> getAllUsers() {

		return saFriendship.readAll();
	}

	@PostMapping({ "", "/" })
	@ResponseBody
	public ResponseEntity<TFriendship> save(@RequestBody TFriendship tFriendship) {

		if (tFriendship.getRequesterUuid() != null && tFriendship.getFriendUuid() != null) {

			TFriendship response = saFriendship.create(tFriendship);

			if (response != null)
				return ResponseEntity.status(HttpStatus.CREATED).body(response);

			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

	}

	@PutMapping("/{requesterUuid}/{friendUuid}/accept")
	@ResponseBody
	public ResponseEntity<TFriendship> accept(@PathVariable String requesterUuid, @PathVariable String friendUuid) {

		TFriendship response = saFriendship.accept(requesterUuid, friendUuid);

		if (response != null)
			return ResponseEntity.status(HttpStatus.OK).body(response);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@DeleteMapping("/{requesterUuid}/{friendUuid}")
	@ResponseBody
	public ResponseEntity<TFriendship> delete(@PathVariable String requesterUuid, @PathVariable String friendUuid) {

		FriendshipId response = saFriendship.delete(requesterUuid, friendUuid);

		if (response != null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@GetMapping("/{requesterUuid}/{friendUuid}")
	@ResponseBody
	public ResponseEntity<TFriendship> getUserById(@PathVariable String requesterUuid,
			@PathVariable String friendUuid) {

		TFriendship response = saFriendship.read(requesterUuid, friendUuid);

		if (response != null)
			return ResponseEntity.status(HttpStatus.OK).body(response);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

	}
}
