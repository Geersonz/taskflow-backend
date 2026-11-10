package com.taskflow.backend.application.usecase;

import com.taskflow.backend.domain.exceptions.TaskNotFoundException;
import com.taskflow.backend.domain.model.Task;
import com.taskflow.backend.domain.ports.in.CompleteTaskUseCase;
import com.taskflow.backend.domain.ports.out.TaskRepository;

public class CompleteTaskServiceImpl implements CompleteTaskUseCase {
    private final TaskRepository taskRepository;

    public CompleteTaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task execute(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.complete();
        return taskRepository.save(task);
    }
}