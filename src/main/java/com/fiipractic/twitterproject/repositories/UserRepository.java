package com.fiipractic.twitterproject.repositories;

import com.fiipractic.twitterproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>{
    Optional<User> findByUsername(String username);

    List<User> findByUsernameOrFirstnameOrLastname(String username, String firstname, String lastname);

}
