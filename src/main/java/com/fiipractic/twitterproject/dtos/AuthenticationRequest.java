package com.fiipractic.twitterproject.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequest {
    @NotNull(message = "The username should not be null")
    private String username;
    @NotNull(message = "The password should not be null")
    private String password;
}
