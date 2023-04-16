package com.fiipractic.twitterproject.services;

import com.fiipractic.twitterproject.entities.Follow;
import com.fiipractic.twitterproject.entities.User;
import com.fiipractic.twitterproject.exceptions.EntityNotFoundException;
import com.fiipractic.twitterproject.repositories.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final UserService userService;
    private final FollowRepository followRepository;

    public void followUser(String username) {
        Optional<User> userOptional = userService.find(username);
        if (!userOptional.isPresent())
            throw new EntityNotFoundException("User", username);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Follow follow = new Follow();
        follow.setUser(user);
        follow.setFollowing(userOptional.get());
        follow.setTimestamp(new Date().getTime());
        followRepository.save(follow);
    }

    public List<Follow> getFollowingByUser(User user){
       return followRepository.findAllByUser(user);
    }

    public void unfollowUser(String username) {
        Optional<User> userOptional = userService.find(username);
        if (!userOptional.isPresent())
            throw new EntityNotFoundException("User", username);

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Follow follow = followRepository.findByUserAndFollowing(currentUser, userOptional.get());
        followRepository.delete(follow);
    }

//    public void deleteFollowByUsers(User user){
//        List<Follow> follows = followRepository.findAllByUserOrFollowing(user);
//        followRepository.deleteAll(follows);
//    }
}
