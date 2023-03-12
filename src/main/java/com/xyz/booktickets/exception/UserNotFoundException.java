package com.xyz.booktickets.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("No user found");
    }
}
