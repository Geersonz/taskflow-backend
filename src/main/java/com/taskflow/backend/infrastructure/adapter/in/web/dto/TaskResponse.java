package com.taskflow.backend.infrastructure.adapter.in.web.dto;

import com.taskflow.backend.domain.model.Priority;
import com.taskflow.backend.domain.model.Task;
import com.taskflow.backend.domain.model.TaskStatus;

import java.time.LocalDateTime;

/**
 * DTO para la respuesta de una tarea.
 */
public record TaskResponse(
    Long id,
    String title,
    String description,
    TaskStatus status,
    Priority priority,
    LocalDateTime createdAt
) {
    // Método estático para mapear fácilmente desde el Dominio
    public static TaskResponse fromDomain(Task task) {
        return new TaskResponse(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getStatus(),
            task.getPriority(),
            task.getCreatedAt()
        );
    }
}