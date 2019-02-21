package es.ucm.fdi.tfg.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.ucm.fdi.tfg.app.entity.UserFilm;
import es.ucm.fdi.tfg.app.repository.UserFilmRepository;

@Controller
@RequestMapping("/userFilm")
public class UserFilmController{

    @Autowired
    private UserFilmRepository userFilmRepository;

    @PostMapping("/save")
    @ResponseBody
    public UserFilm save(@RequestBody UserFilm userFilm) {
        return userFilmRepository.save(userFilm);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<UserFilm> getUserFilmsById(@PathVariable String id){
        return userFilmRepository.findById(id);
    }
}