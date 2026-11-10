package com.taskflow.backend.domain.model;

import com.taskflow.backend.domain.exceptions.InvalidTaskException;
import com.taskflow.backend.domain.exceptions.TaskAlreadyCompletedException;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad de dominio Task.
 * Contiene las reglas de negocio y protege sus propias invariantes.
 * NO tiene anotaciones de JPA ni de Spring (Arquitectura Hexagonal).
 */
public class Task {

    private static final int MIN_TITLE_LENGTH = 3;
    private static final int MAX_TITLE_LENGTH = 100;

    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Priority priority;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;

    /** Constructor para tareas nuevas (aún sin ID). */
    public Task(String title, String description, Priority priority) {
        validateTitle(title);
        this.title = title.trim();
        this.description = description;
        this.priority = priority != null ? priority : Priority.MEDIUM;
        this.status = TaskStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    /** Constructor para reconstruir una tarea existente desde persistencia. */
    public Task(Long id, String title, String description, TaskStatus status,
                Priority priority, LocalDateTime createdAt, LocalDateTime completedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
    }

    // ==================== REGLAS DE NEGOCIO ====================

    public void markAsInProgress() {
        if (this.status == TaskStatus.COMPLETED) {
            throw new TaskAlreadyCompletedException(this.id);
        }
        this.status = TaskStatus.IN_PROGRESS;
    }

    public void complete() {
        if (this.status == TaskStatus.COMPLETED) {
            throw new TaskAlreadyCompletedException(this.id);
        }
        this.status = TaskStatus.COMPLETED;
        this.completedAt = LocalDateTime.now();
    }

    public void cancel() {
        if (this.status == TaskStatus.COMPLETED) {
            throw new IllegalStateException("No se puede cancelar una tarea ya completada");
        }
        this.status = TaskStatus.CANCELLED;
    }

    public void changePriority(Priority newPriority) {
        this.priority = Objects.requireNonNull(newPriority, "La prioridad no puede ser nula");
    }

    public void updateDetails(String newTitle, String newDescription) {
        validateTitle(newTitle);
        this.title = newTitle.trim();
        this.description = newDescription;
    }

    public boolean isOverdue() {
        return this.status != TaskStatus.COMPLETED
                && this.status != TaskStatus.CANCELLED;
    }

    public void assignId(Long id) {
        if (this.id != null) {
            throw new IllegalStateException("La tarea ya tiene un identificador asignado");
        }
        this.id = id;
    }

    // ==================== VALIDACIONES PRIVADAS ====================

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new InvalidTaskException("El título de la tarea es obligatorio");
        }
        String trimmed = title.trim();
        if (trimmed.length() < MIN_TITLE_LENGTH) {
            throw new InvalidTaskException(
                "El título debe tener al menos " + MIN_TITLE_LENGTH + " caracteres");
        }
        if (trimmed.length() > MAX_TITLE_LENGTH) {
            throw new InvalidTaskException(
                "El título no puede exceder " + MAX_TITLE_LENGTH + " caracteres");
        }
    }

    // ==================== GETTERS (sin setters públicos) ====================

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
    public Priority getPriority() { return priority; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getCompletedAt() { return completedAt; }
}