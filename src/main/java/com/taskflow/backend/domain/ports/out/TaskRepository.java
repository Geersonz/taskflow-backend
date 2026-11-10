package com.taskflow.backend.domain.ports.out;

import com.taskflow.backend.domain.model.Task;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de Salida (Driven Port).
 * Define el contrato que debe cumplir cualquier base de datos 
 * que quiera interactuar con el dominio de Tareas.
 */
public interface TaskRepository {

    /** Guarda una nueva tarea o actualiza una existente. */
    Task save(Task task);

    /** Busca una tarea por su ID. */
    Optional<Task> findById(Long id);

    /** Retorna todas las tareas. */
    List<Task> findAll();

    /** Elimina una tarea por su ID. */
    void deleteById(Long id);

    boolean existsById(Long id);
}