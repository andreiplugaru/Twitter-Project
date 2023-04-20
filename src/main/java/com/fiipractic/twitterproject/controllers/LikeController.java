package com.fiipractic.twitterproject.controllers;

import com.fiipractic.twitterproject.services.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class LikeController {
    private final LikeService likeService;
    @PostMapping("/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(tags="Like")
    public void like(@PathVariable UUID postId){
        likeService.add(postId);
    }

    @DeleteMapping("/{postId}")
    @Operation(tags="Like")
    public void remove(@PathVariable UUID postId){
        likeService.remove(postId);
    }
}
