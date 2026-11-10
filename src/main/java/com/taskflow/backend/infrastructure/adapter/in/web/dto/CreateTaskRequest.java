package com.taskflow.backend.infrastructure.adapter.in.web.dto;

import com.taskflow.backend.domain.model.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para la petición de crear una tarea.
 * Usa validaciones de Jakarta para asegurar datos limpios antes de llegar al dominio.
 */
public record CreateTaskRequest(

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 100, message = "El título debe tener entre 3 y 100 caracteres")
    String title,

    String description,

    Priority priority
) {}