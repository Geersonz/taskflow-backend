package com.taskflow.backend.infrastructure.config;

import com.taskflow.backend.application.usecase.CreateTaskServiceImpl;
import com.taskflow.backend.application.usecase.UpdateTaskServiceImpl;
import com.taskflow.backend.application.usecase.DeleteTaskServiceImpl;
import com.taskflow.backend.application.usecase.CompleteTaskServiceImpl;
import com.taskflow.backend.application.usecase.GetAllTasksServiceImpl;
import com.taskflow.backend.domain.ports.in.CreateTaskUseCase;
import com.taskflow.backend.domain.ports.in.UpdateTaskUseCase;
import com.taskflow.backend.domain.ports.in.DeleteTaskUseCase;
import com.taskflow.backend.domain.ports.in.CompleteTaskUseCase;
import com.taskflow.backend.domain.ports.in.GetAllTasksUseCase;
import com.taskflow.backend.domain.ports.out.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración que registra los casos de uso como beans de Spring.
 * 
 * Esta clase vive en la capa de infraestructura porque es el "pegamento"
 * que conecta el dominio con Spring. Mantiene el dominio limpio de anotaciones.
 * 
 * Principio SOLID: Dependency Inversion - Spring depende del dominio, no al revés.
 */
@Configuration
public class UseCaseConfig {

    @Bean
    public CreateTaskUseCase createTaskUseCase(TaskRepository taskRepository) {
        return new CreateTaskServiceImpl(taskRepository);
    }

    @Bean
    public GetAllTasksUseCase getAllTasksUseCase(TaskRepository taskRepository) {
        return new GetAllTasksServiceImpl(taskRepository);
    }

    @Bean
    public UpdateTaskUseCase updateTaskUseCase(TaskRepository taskRepository) {
        return new UpdateTaskServiceImpl(taskRepository);
    }

    @Bean
    public DeleteTaskUseCase deleteTaskUseCase(TaskRepository taskRepository) {
        return new DeleteTaskServiceImpl(taskRepository);
    }

    @Bean
    public CompleteTaskUseCase completeTaskUseCase(TaskRepository taskRepository) {
        return new CompleteTaskServiceImpl(taskRepository);
    }
}