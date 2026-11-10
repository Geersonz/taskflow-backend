package com.taskflow.backend.application.usecase;

import com.taskflow.backend.domain.exceptions.TaskNotFoundException;
import com.taskflow.backend.domain.model.Task;
import com.taskflow.backend.domain.ports.in.UpdateTaskUseCase;
import com.taskflow.backend.domain.ports.out.TaskRepository;

public class UpdateTaskServiceImpl implements UpdateTaskUseCase {
    private final TaskRepository taskRepository;

    public UpdateTaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task execute(Long id, String title, String description) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.updateDetails(title, description);
        return taskRepository.save(task);
    }
}