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

import es.ucm.fdi.tfg.app.entity.UserApp;
import es.ucm.fdi.tfg.app.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    @ResponseBody
    public Iterable<UserApp> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/save")
    @ResponseBody
    public UserApp save(@RequestBody UserApp user) {
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<UserApp> getUserById(@PathVariable String id) {
        return userRepository.findById(id);
    }

}