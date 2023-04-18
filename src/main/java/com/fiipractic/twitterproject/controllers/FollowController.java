package com.fiipractic.twitterproject.controllers;

import com.fiipractic.twitterproject.services.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/followers")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;
    @PostMapping("/{username}")
    public void followUser(@PathVariable String username){
        followService.followUser(username);
    }

    @DeleteMapping("/{username}")
    public void unfollowUser(@PathVariable String username){
        followService.unfollowUser(username);
    }
}
