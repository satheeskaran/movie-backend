package com.sivasathees.movies.service;

import com.sivasathees.movies.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User saveUser(User user);
    public User findUserByUserAndPass(String username, String password);
    public User findUserByUser(String username);
}
