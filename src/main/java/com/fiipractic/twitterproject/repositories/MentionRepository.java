package com.fiipractic.twitterproject.repositories;

import com.fiipractic.twitterproject.entities.Mention;
import com.fiipractic.twitterproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MentionRepository extends JpaRepository<Mention, UUID> {
    List<Mention> getAllByUser(User user);
}
