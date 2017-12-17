package com.gameoflife;

public class InvalidGridIndexException extends RuntimeException {
    public InvalidGridIndexException(String message) {
        super(message);
    }
}
