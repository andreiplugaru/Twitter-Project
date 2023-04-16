package com.fiipractic.twitterproject.repositories;

import com.fiipractic.twitterproject.entities.Follow;
import com.fiipractic.twitterproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByUser(User user);

    Follow findByUserAndFollowing(User user, User following);
//    List<Follow> findAllByUserOrFollowing(User user);

}
