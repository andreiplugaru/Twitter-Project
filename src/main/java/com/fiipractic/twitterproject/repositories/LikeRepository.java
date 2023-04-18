package com.fiipractic.twitterproject.repositories;

import com.fiipractic.twitterproject.entities.Like;
import com.fiipractic.twitterproject.entities.Post;
import com.fiipractic.twitterproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, UUID> {
    Optional<Like> findByPostAndUser(Post post, User user);
}
