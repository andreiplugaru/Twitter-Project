package com.fiipractic.twitterproject.mappers;

import com.fiipractic.twitterproject.dtos.UserDto;
import com.fiipractic.twitterproject.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getUsername(), user.getFirstname(), user.getLastname()
        );
    }
}
