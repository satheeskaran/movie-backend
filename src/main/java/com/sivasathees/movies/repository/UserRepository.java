package com.sivasathees.movies.repository;

import com.sivasathees.movies.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsernameAndPassword(String username, String password);
    User findUserByUsername(String username);
}
