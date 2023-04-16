package com.fiipractic.twitterproject.controllers;

import com.fiipractic.twitterproject.dtos.UserDto;
import com.fiipractic.twitterproject.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserDto>> searchUser(@RequestParam(required = false) Optional<String> username,
                                                    @RequestParam (required = false) Optional<String> firstName,
                                                    @RequestParam (required = false) Optional<String> lastName){

        return ResponseEntity.ok(
                userService.searchByUsernameOrFistNameOrLastName(username, firstName, lastName));
    }
}
