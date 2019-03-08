package es.ucm.fdi.tfg.app.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestPart;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import es.ucm.fdi.tfg.app.entity.Film;
import es.ucm.fdi.tfg.app.repository.FilmRepository;

@Controller
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/all")
    @ResponseBody
    public Iterable<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @PostMapping("/save")
    @RequestMapping(value = "/save", method = RequestMethod.POST,
    consumes = {"multipart/form-data"})
    @ResponseBody
    public Film save(@RequestPart("film") @Valid String filmString,
    	    @RequestPart("image") @Valid @NotNull @NotBlank MultipartFile image) {
        JSONObject obj = new JSONObject(filmString);
        Film film = new Film();
        byte[] imageBytes = null;
        try {
        	imageBytes = image.getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        film.setUuid((String)obj.get("uuid"));
        film.setName((String)obj.get("name"));
        film.setDirector((String)obj.get("director"));
        film.setTrailerURL((String)obj.get("trailerURL"));
        film.setInfoURL((String)obj.get("infoURL"));
        film.setImage(imageBytes);
        film.setGenre((String)obj.get("genre"));
        film.setDuration((Integer)obj.get("duration"));
        film.setRating((Double)obj.get("rating"));
        film.setCountry((String)obj.get("country"));

        return filmRepository.save(film);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Film> getFilmById(@PathVariable String id) {
        return filmRepository.findById(id);
    }


}
