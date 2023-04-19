package com.fiipractic.twitterproject.controllers;

import com.fiipractic.twitterproject.services.FollowService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/followers")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class FollowController {
    private final FollowService followService;
    @PostMapping("/{username}")
    @ResponseStatus(HttpStatus.CREATED)
    public void followUser(@PathVariable String username){
        followService.followUser(username);
    }

    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void unfollowUser(@PathVariable String username){
        followService.unfollowUser(username);
    }
}
