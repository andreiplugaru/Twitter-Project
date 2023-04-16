package com.fiipractic.twitterproject.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {
    @NotNull(message = "The first name should not be null")
    private String firstname;

    @NotNull(message = "The last name should not be null")
    private String lastname;

    @NotNull(message = "The username should not be null")
    private String username;

    @NotNull(message = "The password should not be null")
    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;
}
