package com.taskflow.backend.domain.ports.in;

import com.taskflow.backend.domain.model.Task;

public interface UpdateTaskUseCase {
    Task execute(Long id, String title, String description);
}