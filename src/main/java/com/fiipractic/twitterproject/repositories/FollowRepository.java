package com.fiipractic.twitterproject.repositories;

import com.fiipractic.twitterproject.entities.Follow;
import com.fiipractic.twitterproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByUser(User user);

    Optional<Follow> findByUserAndFollowing(User user, User following);
}
