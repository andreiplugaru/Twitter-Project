package com.fiipractic.twitterproject.controllers;

import com.fiipractic.twitterproject.dtos.PostCreationDto;
import com.fiipractic.twitterproject.dtos.PostReturnDto;
import com.fiipractic.twitterproject.services.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PostController {
    private final PostService postService;
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostCreationDto postCreationDto) {
        postService.create(postCreationDto);
    }

    @GetMapping("/own-posts")
    public ResponseEntity<List<PostReturnDto>> getOwnPosts(@RequestParam Optional<Long> timestamp) {
       return ResponseEntity.ok(postService.getOwnPosts(timestamp));
    }
    @GetMapping("/feed")
    public ResponseEntity<List<PostReturnDto>> getFeed() {
        return ResponseEntity.ok(postService.getFeed());
    }

    @PostMapping("/repost/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void repost(@PathVariable UUID postId) {
        postService.repost(postId);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID postId) {
        postService.delete(postId);
    }

    @GetMapping("/mentions")
    public ResponseEntity<List<PostReturnDto>> getMentions() {
        return ResponseEntity.ok(postService.getPostWithUserMentioned());
    }

}