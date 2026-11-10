package com.taskflow.backend.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio Spring Data JPA.
 * Spring genera automáticamente los métodos CRUD.
 * Nota: No requiere @Repository porque extiende de JpaRepository.
 */
public interface SpringDataTaskRepository extends JpaRepository<TaskEntity, Long> {
    // Spring genera automáticamente: save(), findById(), findAll(), deleteById(), etc.
}