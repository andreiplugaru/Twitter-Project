package com.fiipractic.twitterproject.exceptions;

import java.util.UUID;

public class LikeNotFoundException extends RuntimeException {
    public LikeNotFoundException(UUID postId) {
        super("Like not found for post with id: " + postId + ".");
    }
}
