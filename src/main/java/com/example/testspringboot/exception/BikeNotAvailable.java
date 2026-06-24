package com.example.testspringboot.exception;

public class BikeNotAvailable extends RuntimeException {
    public BikeNotAvailable(String message) {
        super(message);
    }
}
