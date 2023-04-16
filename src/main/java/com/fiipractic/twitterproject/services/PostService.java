package com.fiipractic.twitterproject.services;

import com.fiipractic.twitterproject.dtos.PostCreationDto;
import com.fiipractic.twitterproject.dtos.PostReturnDto;
import com.fiipractic.twitterproject.entities.Follow;
import com.fiipractic.twitterproject.entities.Post;
import com.fiipractic.twitterproject.entities.User;
import com.fiipractic.twitterproject.mappers.PostMapper;
import com.fiipractic.twitterproject.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final FollowService followService;

    public void create(PostCreationDto postCreationDto) {
        Post post = postMapper.mapToPost(postCreationDto);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        postRepository.save(post);
    }

    public List<PostReturnDto> getOwnPosts(Optional<Long> timestamp) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Post> posts;
        if (timestamp.isPresent()) {
            posts = postRepository.findAllByUserAndTimestampAfter(user, timestamp.get());
        } else {
            posts = postRepository.findAllByUser(user);
        }
        return posts
                .stream()
                .map(postMapper::mapToPostDto)
                .toList();
    }

    public List<PostReturnDto> getFeed(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<User> following = followService.getFollowingByUser(user).stream().map(Follow::getFollowing).toList();
        List<Post> posts = postRepository.findAllByUserIn(following);

        return posts
                .stream()
                .map(postMapper::mapToPostDto)
                .toList();
    }
}
