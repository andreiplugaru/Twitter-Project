package com.fiipractic.twitterproject.exceptions;

import java.util.UUID;

public class LikeFoundException extends RuntimeException{
    public LikeFoundException(UUID postId) {
        super("Like already exists for post with id: " + postId + ".");
    }

}
