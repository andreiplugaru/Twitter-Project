package com.fiipractic.twitterproject.services;

import com.fiipractic.twitterproject.dtos.PostCreationDto;
import com.fiipractic.twitterproject.dtos.PostReturnDto;
import com.fiipractic.twitterproject.entities.Follow;
import com.fiipractic.twitterproject.entities.Mention;
import com.fiipractic.twitterproject.entities.Post;
import com.fiipractic.twitterproject.entities.User;
import com.fiipractic.twitterproject.exceptions.EntityNotFoundException;
import com.fiipractic.twitterproject.mappers.PostMapper;
import com.fiipractic.twitterproject.repositories.PostRepository;
import com.fiipractic.twitterproject.utils.PostUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final PostMapper postMapper;
    private final FollowService followService;
    private final MentionService mentionService;

    public void create(PostCreationDto postCreationDto) {
        Post post = postMapper.mapToPost(postCreationDto);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        postRepository.save(post);
        mentionService.save(getMentions(post));
    }
    public List<Mention> getMentions(Post post){
        List<User> users = userService.getAll();
        List<User> mentionedUsers = PostUtil.getPostMentions(post, users);

        return  mentionedUsers
                .stream()
                .map(user -> new Mention(user, post))
                .toList();
    }
    public Post checkPostExists(UUID postId){
        Optional<Post> optionalPost = postRepository.findById(postId);
        if(!optionalPost.isPresent())
            throw new EntityNotFoundException("Post", postId.toString());
        return optionalPost.get();
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

    public void repost(UUID postId){
        Post post = checkPostExists(postId);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        postRepository.save(post);
    }

    public void delete(UUID postId){
        Post post = checkPostExists(postId);
        postRepository.delete(post);
    }

    public List<PostReturnDto> getPostWithUserMentioned(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Mention> mentions = mentionService.getByUser(user);

        return mentions
                .stream()
                .map(mention -> postMapper.mapToPostDto(mention.getPost()))
                .toList();
    }
}
