package es.ucm.fdi.tfg.app.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.ucm.fdi.tfg.app.entity.Film;
import es.ucm.fdi.tfg.app.entity.Plan;
import es.ucm.fdi.tfg.app.entity.UserApp;
import es.ucm.fdi.tfg.app.repository.FilmRepository;
import es.ucm.fdi.tfg.app.transfer.TFilm;

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
    public ResponseEntity<Film> save(@RequestBody TFilm tFilm) {
		Optional<Film> optFilm = filmRepository.findById(tFilm.getUuid());
		
		//Check if film exist
		if (!optFilm.isPresent()) {
			Film film = new Film();
			film.setUuid(tFilm.getUuid());
			film.setName(tFilm.getName());
			film.setImageURL(tFilm.getImageURL());
			film.setInfoURL(tFilm.getInfoURL());
			film.setTrailerURL(tFilm.getTrailerURL());
			film.setRating(tFilm.getRating());
			film.setCountry(tFilm.getCountry());
			film.setDirector(tFilm.getDirector());
			film.setDuration(tFilm.getDuration());
			film.setGenre(tFilm.getGenre());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(filmRepository.save(film));
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Film> getFilmById(@PathVariable String id) {
        return filmRepository.findById(id);
    }


}
