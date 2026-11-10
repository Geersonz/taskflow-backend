package com.taskflow.backend.infrastructure.adapter.in.web;

import com.taskflow.backend.domain.exceptions.InvalidTaskException;
import com.taskflow.backend.domain.exceptions.TaskAlreadyCompletedException;
import com.taskflow.backend.domain.exceptions.TaskNotFoundException;
import com.taskflow.backend.infrastructure.adapter.in.web.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * Manejador global de excepciones.
 * 
 * Captura todas las excepciones lanzadas desde el dominio o la aplicación
 * y las convierte en respuestas HTTP limpias y estandarizadas.
 * 
 * Principios SOLID:
 * - Single Responsibility: Solo se encarga de manejar errores
 * - Open/Closed: Se pueden agregar nuevos manejadores sin modificar los existentes
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja cuando no se encuentra una tarea.
     * Retorna HTTP 404 (Not Found).
     */
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTaskNotFound(
            TaskNotFoundException ex, HttpServletRequest request) {
        
        ErrorResponse error = ErrorResponse.of(
            HttpStatus.NOT_FOUND.value(),
            "Not Found",
            ex.getMessage(),
            request.getRequestURI()
        );
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Maneja cuando se intenta modificar una tarea ya completada.
     * Retorna HTTP 409 (Conflict).
     */
    @ExceptionHandler(TaskAlreadyCompletedException.class)
    public ResponseEntity<ErrorResponse> handleTaskAlreadyCompleted(
            TaskAlreadyCompletedException ex, HttpServletRequest request) {
        
        ErrorResponse error = ErrorResponse.of(
            HttpStatus.CONFLICT.value(),
            "Conflict",
            ex.getMessage(),
            request.getRequestURI()
        );
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    /**
     * Maneja validaciones fallidas del dominio.
     * Retorna HTTP 400 (Bad Request).
     */
    @ExceptionHandler(InvalidTaskException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTask(
            InvalidTaskException ex, HttpServletRequest request) {
        
        ErrorResponse error = ErrorResponse.of(
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            ex.getMessage(),
            request.getRequestURI()
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Maneja errores de validación de Jakarta (@Valid en los DTOs).
     * Retorna HTTP 400 con todos los mensajes de validación.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        
        // Concatenamos todos los mensajes de error de validación
        String messages = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));
        
        ErrorResponse error = ErrorResponse.of(
            HttpStatus.BAD_REQUEST.value(),
            "Validation Error",
            messages,
            request.getRequestURI()
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Maneja cualquier otra excepción no prevista.
     * Retorna HTTP 500 (Internal Server Error).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, HttpServletRequest request) {
        
        ErrorResponse error = ErrorResponse.of(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            "Ocurrió un error inesperado: " + ex.getMessage(),
            request.getRequestURI()
        );
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}