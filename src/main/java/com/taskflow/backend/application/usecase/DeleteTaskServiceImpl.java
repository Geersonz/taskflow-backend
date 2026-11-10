package com.taskflow.backend.application.usecase;

import com.taskflow.backend.domain.exceptions.TaskNotFoundException;
import com.taskflow.backend.domain.ports.in.DeleteTaskUseCase;
import com.taskflow.backend.domain.ports.out.TaskRepository;

public class DeleteTaskServiceImpl implements DeleteTaskUseCase {
    private final TaskRepository taskRepository;

    public DeleteTaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void execute(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }
}