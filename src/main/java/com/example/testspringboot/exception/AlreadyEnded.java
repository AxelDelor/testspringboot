package com.example.testspringboot.exception;

public class AlreadyEnded extends RuntimeException {
    public AlreadyEnded(String message) {
        super(message);
    }
}
