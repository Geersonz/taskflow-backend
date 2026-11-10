package com.taskflow.backend.infrastructure.adapter.in.web.dto;

import java.time.LocalDateTime;

/**
 * DTO para respuestas de error estandarizadas.
 * 
 * Principio SOLID: Single Responsibility - solo se encarga de representar errores.
 */
public record ErrorResponse(
    LocalDateTime timestamp,
    int status,
    String error,
    String message,
    String path
) {
    /** Factory method para crear respuestas de error fácilmente */
    public static ErrorResponse of(int status, String error, String message, String path) {
        return new ErrorResponse(LocalDateTime.now(), status, error, message, path);
    }
}