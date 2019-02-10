package es.ucm.fdi.tfg.app.controller;

import java.util.Optional;

import com.github.lambdaexpression.annotation.EnableRequestBodyParam;
import com.github.lambdaexpression.annotation.RequestBodyParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import es.ucm.fdi.tfg.app.entity.Film;
import es.ucm.fdi.tfg.app.repository.FilmRepository;

@Controller
@RequestMapping("/film")
@EnableRequestBodyParam
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @CrossOrigin
    @GetMapping("/all")
    @ResponseBody
    public Iterable<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @CrossOrigin
    @PostMapping("/save")
    @ResponseBody
    public Film save(@RequestBody Film film) {
        return filmRepository.save(film);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Film> getFilmById(@PathVariable String id) {
        return filmRepository.findById(id);
    }


}
