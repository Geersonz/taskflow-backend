package com.taskflow.backend.application.usecase;

import com.taskflow.backend.domain.model.Priority;
import com.taskflow.backend.domain.model.Task;
import com.taskflow.backend.domain.ports.in.CreateTaskUseCase;
import com.taskflow.backend.domain.ports.out.TaskRepository;

/**
 * Implementación del caso de uso para crear tareas.
 * 
 * Principios SOLID aplicados:
 * - Dependency Inversion (D): Dependemos de la interfaz TaskRepository, no de una clase concreta.
 * - Single Responsibility (S): Esta clase solo se encarga de orquestar la creación.
 */
public class CreateTaskServiceImpl implements CreateTaskUseCase {

    private final TaskRepository taskRepository;

    // Inyección de dependencias por constructor (Buena práctica de Clean Code)
    public CreateTaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task execute(String title, String description, Priority priority) {
        // 1. Creamos la entidad de dominio (aquí se validan las reglas de negocio)
        Task newTask = new Task(title, description, priority);
        
        // 2. Usamos el puerto de salida para guardarla
        // (No sabemos si se guarda en Postgres, MySQL o un archivo, ¡y no nos importa!)
        return taskRepository.save(newTask);
    }
}