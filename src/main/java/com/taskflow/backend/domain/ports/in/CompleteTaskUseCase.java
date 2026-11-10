package com.taskflow.backend.domain.ports.in;

import com.taskflow.backend.domain.model.Task;

public interface CompleteTaskUseCase {
    Task execute(Long id);
}