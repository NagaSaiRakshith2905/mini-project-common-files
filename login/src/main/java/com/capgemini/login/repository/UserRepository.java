package com.capgemini.login.repository;

import com.capgemini.login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findUserByUserName(String userName);
    Optional<User> findUserByEmail(String email);
}
