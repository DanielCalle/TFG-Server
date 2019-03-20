package es.ucm.fdi.tfg.app.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

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
    public Film save(@RequestParam String uuid, @RequestParam String name,
    		@RequestParam String director, @RequestParam String trailerURL,
    		@RequestParam String infoURL, @RequestParam MultipartFile image,
    		@RequestParam String genre, @RequestParam int duration,
    		@RequestParam double rating, @RequestParam String country) {

        Film film = new Film();
        byte[] imageBytes = null;
        try {
        	imageBytes = image.getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        film.setUuid(uuid);
        film.setName(name);
        film.setDirector(director);
        film.setTrailerURL(trailerURL);
        film.setInfoURL(infoURL);
        film.setImage(imageBytes);
        film.setGenre(genre);
        film.setDuration(duration);
        film.setRating(rating);
        film.setCountry(country);

        return filmRepository.save(film);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Film> getFilmById(@PathVariable String id) {
        return filmRepository.findById(id);
    }


}
