package com.fiipractic.twitterproject.services;

import com.fiipractic.twitterproject.entities.BasePost;
import com.fiipractic.twitterproject.entities.Post;
import com.fiipractic.twitterproject.entities.Reply;
import com.fiipractic.twitterproject.exceptions.EntityNotFoundException;
import com.fiipractic.twitterproject.repositories.PostRepository;
import com.fiipractic.twitterproject.repositories.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BasePostService {
    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;

    public BasePost checkPostOrReplyExists(UUID id) {
        BasePost basePost = null;
        if (getPostById(id).isPresent())
            basePost = getPostById(id).get();

        if (getReplyById(id).isPresent())
            basePost = getReplyById(id).get();


        if (basePost == null)
            throw new EntityNotFoundException("Post or Reply", id.toString());
        return basePost;
    }

    public Optional<Post> getPostById(UUID postId) {
        return postRepository.findById(postId);
    }

    public Optional<Reply> getReplyById(UUID postId) {
        return replyRepository.findById(postId);
    }
}
