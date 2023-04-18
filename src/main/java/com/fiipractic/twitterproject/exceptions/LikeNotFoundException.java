package com.fiipractic.twitterproject.exceptions;

public class LikeNotFoundException extends RuntimeException {
    public LikeNotFoundException(String postId) {
        super("Like not found for post with id: " + postId + ".");
    }
}
