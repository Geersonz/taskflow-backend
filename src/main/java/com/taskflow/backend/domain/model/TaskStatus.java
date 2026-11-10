package com.taskflow.backend.domain.model;

/**
 * Estados posibles del ciclo de vida de una tarea.
 * Pertenece al dominio: no depende de ningún framework.
 */
public enum TaskStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}