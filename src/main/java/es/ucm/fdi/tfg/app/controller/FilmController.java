package es.ucm.fdi.tfg.app.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.ucm.fdi.tfg.app.entity.Film;
import es.ucm.fdi.tfg.app.repository.FilmRepository;

@Controller
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping({"", "/"})
    @ResponseBody
    public Iterable<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @PostMapping({"", "/"})
    @ResponseBody
    public ResponseEntity<Film> save(@RequestBody Film film) {
		Optional<Film> optFilm = filmRepository.findById(film.getUuid());
		
		//Check if film exist
		if (!optFilm.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(filmRepository.save(film));
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
    
    @PutMapping({"", "/"})
    @ResponseBody
    public ResponseEntity<Film> update(@RequestBody Film film) {
		Optional<Film> optFilm = filmRepository.findById(film.getUuid());
		
		//Check if film exist
		if (optFilm.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(filmRepository.save(film));
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/{uuid}")
    @ResponseBody
    public Optional<Film> getFilmById(@PathVariable String uuid) {
        return filmRepository.findById(uuid);
    }
    
    @DeleteMapping("/{uuid}")
    @ResponseBody
    public ResponseEntity<Film> delete(@PathVariable String uuid) {
    	Optional<Film> optFilm = filmRepository.findById(uuid);
    	if (optFilm.isPresent()) {
    		filmRepository.delete(optFilm.get());
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    	}
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


}
