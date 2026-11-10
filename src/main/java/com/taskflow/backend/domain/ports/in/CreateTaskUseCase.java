package com.taskflow.backend.domain.ports.in;

import com.taskflow.backend.domain.model.Priority;
import com.taskflow.backend.domain.model.Task;

/**
 * Puerto de Entrada (Driving Port).
 * Define la operación de crear una nueva tarea.
 */
public interface CreateTaskUseCase {

    /**
     * Ejecuta el caso de uso de creación de tarea.
     * @param title Título de la tarea
     * @param description Descripción detallada
     * @param priority Prioridad asignada
     * @return La tarea creada con su ID asignado
     */
    Task execute(String title, String description, Priority priority);
}