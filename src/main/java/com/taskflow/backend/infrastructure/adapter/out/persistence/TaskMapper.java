package com.taskflow.backend.infrastructure.adapter.out.persistence;

import com.taskflow.backend.domain.model.Task;

/**
 * Mapper que convierte entre Task (dominio) y TaskEntity (JPA).
 * 
 * Principio SOLID: Single Responsibility - solo se encarga de mapear.
 */
public class TaskMapper {

    /** Convierte TaskEntity (JPA) a Task (dominio) */
    public static Task toDomain(TaskEntity entity) {
        return new Task(
            entity.getId(),
            entity.getTitle(),
            entity.getDescription(),
            entity.getStatus(),
            entity.getPriority(),
            entity.getCreatedAt(),
            entity.getCompletedAt()
        );
    }

    /** Convierte Task (dominio) a TaskEntity (JPA) */
    public static TaskEntity toEntity(Task task) {
        TaskEntity entity = new TaskEntity();
        entity.setId(task.getId());
        entity.setTitle(task.getTitle());
        entity.setDescription(task.getDescription());
        entity.setStatus(task.getStatus());
        entity.setPriority(task.getPriority());
        entity.setCreatedAt(task.getCreatedAt());
        entity.setCompletedAt(task.getCompletedAt());
        return entity;
    }
}