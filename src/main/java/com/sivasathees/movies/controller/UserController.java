package com.sivasathees.movies.controller;

import com.sivasathees.movies.model.Movie;
import com.sivasathees.movies.model.User;
import com.sivasathees.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static jdk.nashorn.internal.objects.Global.instance;
import static jdk.nashorn.internal.objects.Global.undefined;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String add(@RequestBody User user){
        User use = userService.findUserByUser(user.getUsername());
        if(use == null){
            userService.saveUser(user);
            return "{\"success\":1}";
        }
        else{
            return "{\"success\":0}";
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        User use = userService.findUserByUserAndPass(user.getUsername(), user.getPassword());
        if(use == null){
            return "{\"success\":1}";
        }
        else{
            return "{\"success\":0}";
        }
    }

    @PostMapping("/checkUser")
    public User checkUser(@RequestBody User user){
        return userService.findUserByUser(user.getUsername());
    }
}
