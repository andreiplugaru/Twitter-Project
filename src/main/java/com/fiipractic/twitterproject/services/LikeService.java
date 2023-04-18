package com.fiipractic.twitterproject.services;

import com.fiipractic.twitterproject.entities.Like;
import com.fiipractic.twitterproject.entities.Post;
import com.fiipractic.twitterproject.entities.User;
import com.fiipractic.twitterproject.exceptions.LikeFoundException;
import com.fiipractic.twitterproject.exceptions.LikeNotFoundException;
import com.fiipractic.twitterproject.repositories.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostService postService;

    public void add(UUID postId) {
        Post post = postService.checkPostExists(postId);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Like like = new Like();
        like.setPost(post);
        like.setUser(user);
        checkLikeNotExistForPostAndUser(post, user);
        likeRepository.save(like);
    }

    public Like checkLikeForPostAndUser(Post post, User user) {
        Optional<Like> likeOptional = likeRepository.findByPostAndUser(post, user);
        if (!likeOptional.isPresent()) {
            throw new LikeNotFoundException(post.getId());
        }
        return likeOptional.get();
    }
    public void checkLikeNotExistForPostAndUser(Post post, User user) {
        Optional<Like> likeOptional = likeRepository.findByPostAndUser(post, user);
        if (likeOptional.isPresent()) {
            throw new LikeFoundException(post.getId());
        }
    }
    public void remove(UUID postId) {
        Post post = postService.checkPostExists(postId);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Like like = checkLikeForPostAndUser(post, user);
        likeRepository.delete(like);
    }
}
