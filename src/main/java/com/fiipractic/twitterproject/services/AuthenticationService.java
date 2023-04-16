package com.fiipractic.twitterproject.services;

import com.fiipractic.twitterproject.config.JwtService;
import com.fiipractic.twitterproject.dtos.AuthenticationRequest;
import com.fiipractic.twitterproject.dtos.AuthenticationResponse;
import com.fiipractic.twitterproject.dtos.RegisterRequest;
import com.fiipractic.twitterproject.entities.User;
import com.fiipractic.twitterproject.exceptions.UsernameExistsException;
import com.fiipractic.twitterproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        if(userService.checkUsernameExists(registerRequest.getUsername()))
            throw new UsernameExistsException(registerRequest.getUsername());

        User user = new User();
        user.setFirstname(registerRequest.getFirstname());
        user.setLastname(registerRequest.getLastname());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setUsername(registerRequest.getUsername());
        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        User user = userRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow();

        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}
