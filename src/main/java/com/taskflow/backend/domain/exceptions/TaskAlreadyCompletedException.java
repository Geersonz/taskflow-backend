package com.taskflow.backend.domain.exceptions;

public class TaskAlreadyCompletedException extends RuntimeException {

    public TaskAlreadyCompletedException(Long id) {
        super("La tarea con id " + id + " ya fue completada");
    }
}