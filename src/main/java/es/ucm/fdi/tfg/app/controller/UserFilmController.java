package es.ucm.fdi.tfg.app.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.ucm.fdi.tfg.app.entity.Film;
import es.ucm.fdi.tfg.app.entity.UserApp;
import es.ucm.fdi.tfg.app.entity.UserFilm;
import es.ucm.fdi.tfg.app.entity.UserFilmId;
import es.ucm.fdi.tfg.app.repository.FilmRepository;
import es.ucm.fdi.tfg.app.repository.UserFilmRepository;
import es.ucm.fdi.tfg.app.repository.UserRepository;
import es.ucm.fdi.tfg.app.transfer.TUserFilm;

@Controller
@RequestMapping("/user-films")
public class UserFilmController{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FilmRepository filmRepository;
    @Autowired
    private UserFilmRepository userFilmRepository;
    
    @GetMapping({"", "/"})
    @ResponseBody
    public Iterable<UserFilm> getAll() {
        return userFilmRepository.findAll();
    }

    @PostMapping({"", "/"})
    @ResponseBody
    public ResponseEntity<UserFilm> save(@RequestBody TUserFilm tUserFilm) {
		Optional<UserApp> optUser = userRepository.findById(tUserFilm.getUserUuid());
		Optional<Film> optFilm = filmRepository.findById(tUserFilm.getFilmUuid());
		//Check if user and film exist
		if (optUser.isPresent() && optFilm.isPresent()) {
			UserFilmId id = new UserFilmId();
			id.setFilm(tUserFilm.getFilmUuid());
			id.setUser(tUserFilm.getUserUuid());
			Optional<UserFilm> optUserFilm = userFilmRepository.findById(id);
			if (!optUserFilm.isPresent()) {
				UserFilm userFilm = new UserFilm();
				userFilm.setFilm(optFilm.get());
				userFilm.setUserApp(optUser.get());
				userFilm.setDate(new Date());
				
				userFilm = userFilmRepository.save(userFilm);
				
				return ResponseEntity.status(HttpStatus.CREATED).body(userFilm);
			}

			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
			
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }
    
    @DeleteMapping("/{userUuid}/{filmUuid}")
    @ResponseBody
    public ResponseEntity<UserFilm> delete(
    		@PathVariable String userUuid,
    		@PathVariable String filmUuid) {
    	UserFilmId id = new UserFilmId();
		id.setUser(userUuid);
		id.setFilm(filmUuid);
		Optional<UserFilm> optUserFilm = userFilmRepository.findById(id);
    	if (optUserFilm.isPresent()) {
    		userFilmRepository.delete(optUserFilm.get());
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    	}
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/{userUuid}/{filmUuid}")
    @ResponseBody
    public ResponseEntity<UserFilm> getUserFilmsById(
    		@PathVariable String userUuid,
    		@PathVariable String filmUuid) {
    	UserFilmId id = new UserFilmId();
		id.setUser(userUuid);
		id.setFilm(filmUuid);
		Optional<UserFilm> optUserFilm = userFilmRepository.findById(id);
    	if (optUserFilm.isPresent()) {
	    	return ResponseEntity.status(HttpStatus.OK).body(optUserFilm.get());
    	}
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}