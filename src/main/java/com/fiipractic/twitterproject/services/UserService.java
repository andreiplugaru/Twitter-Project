package com.fiipractic.twitterproject.services;

import com.fiipractic.twitterproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public final boolean checkUsernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
