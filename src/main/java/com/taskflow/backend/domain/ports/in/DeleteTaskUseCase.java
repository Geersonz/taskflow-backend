package com.taskflow.backend.domain.ports.in;

public interface DeleteTaskUseCase {
    void execute(Long id);
}