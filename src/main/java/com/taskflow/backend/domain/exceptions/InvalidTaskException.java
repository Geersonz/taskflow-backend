package com.taskflow.backend.domain.exceptions;

public class InvalidTaskException extends RuntimeException {

    public InvalidTaskException(String message) {
        super(message);
    }
}