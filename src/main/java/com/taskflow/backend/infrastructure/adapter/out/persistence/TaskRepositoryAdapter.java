package com.taskflow.backend.infrastructure.adapter.out.persistence;

import com.taskflow.backend.domain.model.Task;
import com.taskflow.backend.domain.ports.out.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador de Salida: Implementa el puerto TaskRepository del dominio
 * usando Spring Data JPA.
 * 
 * Principio SOLID: Dependency Inversion - El dominio no depende de Spring,
 * pero Spring depende del dominio (a través de esta implementación).
 */
@Component
public class TaskRepositoryAdapter implements TaskRepository {

    private final SpringDataTaskRepository jpaRepository;

    // Inyección de dependencias por constructor
    public TaskRepositoryAdapter(SpringDataTaskRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Task save(Task task) {
        TaskEntity entity = TaskMapper.toEntity(task);
        TaskEntity savedEntity = jpaRepository.save(entity);
        return TaskMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return jpaRepository.findById(id)
                .map(TaskMapper::toDomain);
    }

    @Override
    public List<Task> findAll() {
        return jpaRepository.findAll().stream()
                .map(TaskMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
}