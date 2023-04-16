package com.fiipractic.twitterproject.exceptions;

public class UsernameExistsException extends RuntimeException{
    public UsernameExistsException(String username) {
        super("There already exists an account with the username: " + username + ".");
    }
}
