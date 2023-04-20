package com.fiipractic.twitterproject.repositories;

import com.fiipractic.twitterproject.entities.Post;
import com.fiipractic.twitterproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findAllByUser(User user);

    List<Post> findAllByUserAndTimestampGreaterThanEqual(User user, Long timestamp);
}
