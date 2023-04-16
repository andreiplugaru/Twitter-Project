package com.fiipractic.twitterproject.services;

import com.fiipractic.twitterproject.dtos.UserDto;
import com.fiipractic.twitterproject.mappers.UserMapper;
import com.fiipractic.twitterproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public final boolean checkUsernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public final List<UserDto> searchByUsernameOrFistNameOrLastName(Optional<String> username, Optional<String> firstName, Optional<String> lastName) {
        String currentUsername = username.orElse("");
        String currentFirstName = firstName.orElse("");
        String currentLastName = lastName.orElse("");

        List<UserDto> userDtos = userRepository.findByUsernameOrFirstnameOrLastname(currentUsername, currentFirstName, currentLastName).stream()
                .map(userMapper::mapToUserDto)
                .toList();
        return userDtos;
    }
}
