package com.taskflow.backend.domain.ports.in;

import com.taskflow.backend.domain.model.Task;

import java.util.List;

/**
 * Puerto de Entrada (Driving Port).
 * Define la operación de obtener todas las tareas.
 */
public interface GetAllTasksUseCase {

    /**
     * Ejecuta el caso de uso de listado.
     * @return Lista de todas las tareas existentes
     */
    List<Task> execute();
}