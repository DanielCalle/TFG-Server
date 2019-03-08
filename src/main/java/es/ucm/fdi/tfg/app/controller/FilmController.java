package es.ucm.fdi.tfg.app.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String save(@RequestPart("film") @Valid String filmString,
    @RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file) {
        JSONObject obj = new JSONObject(filmString);
        String res = (String)obj.get("name");
        return res;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Film> getFilmById(@PathVariable String id) {
        return filmRepository.findById(id);
    }


}
