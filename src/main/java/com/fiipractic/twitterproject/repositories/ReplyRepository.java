package com.fiipractic.twitterproject.repositories;

import com.fiipractic.twitterproject.entities.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReplyRepository extends JpaRepository<Reply, UUID> {
}
