package com.taskflow.backend.application.usecase;

import com.taskflow.backend.domain.model.Task;
import com.taskflow.backend.domain.ports.in.GetAllTasksUseCase;
import com.taskflow.backend.domain.ports.out.TaskRepository;

import java.util.List;

/**
 * Implementación del caso de uso para listar tareas.
 */
public class GetAllTasksServiceImpl implements GetAllTasksUseCase {

    private final TaskRepository taskRepository;

    public GetAllTasksServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> execute() {
        // Simplemente delegamos al repositorio
        return taskRepository.findAll();
    }
}