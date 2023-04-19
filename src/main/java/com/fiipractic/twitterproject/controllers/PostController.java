package com.fiipractic.twitterproject.controllers;

import com.fiipractic.twitterproject.dtos.PostCreationDto;
import com.fiipractic.twitterproject.dtos.PostReturnDto;
import com.fiipractic.twitterproject.dtos.PostWithLikesDto;
import com.fiipractic.twitterproject.services.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PostController {
    private final PostService postService;
    @PostMapping("")
    public ResponseEntity<UUID> createPost(@RequestBody PostCreationDto postCreationDto) {
       return ResponseEntity.status(HttpStatus.CREATED).body(postService.create(postCreationDto));
    }

    @GetMapping("/own-posts")
    public ResponseEntity<List<PostWithLikesDto>> getOwnPosts(@RequestParam Optional<Long> date) {
       return ResponseEntity.status(HttpStatus.OK).body(postService.getOwnPosts(date));
    }
    @GetMapping("/feed")
    public ResponseEntity<List<PostReturnDto>> getFeed() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getFeed());
    }

    @PostMapping("/repost/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UUID> repost(@PathVariable UUID postId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.repost(postId));
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam UUID postId) {

        postService.delete(postId);
    }

    @GetMapping("/mentions")
    public ResponseEntity<List<PostReturnDto>> getMentions() {
        return ResponseEntity.ok(postService.getPostWithUserMentioned());
    }

}
