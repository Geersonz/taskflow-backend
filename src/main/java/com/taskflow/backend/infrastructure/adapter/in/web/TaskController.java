package com.taskflow.backend.infrastructure.adapter.in.web;

import com.taskflow.backend.domain.model.Task;
import com.taskflow.backend.domain.ports.in.*;
import com.taskflow.backend.infrastructure.adapter.in.web.dto.CreateTaskRequest;
import com.taskflow.backend.infrastructure.adapter.in.web.dto.TaskResponse;
import com.taskflow.backend.infrastructure.adapter.in.web.dto.UpdateTaskRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final GetAllTasksUseCase getAllTasksUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final CompleteTaskUseCase completeTaskUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase,
                          GetAllTasksUseCase getAllTasksUseCase,
                          UpdateTaskUseCase updateTaskUseCase,
                          DeleteTaskUseCase deleteTaskUseCase,
                          CompleteTaskUseCase completeTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.getAllTasksUseCase = getAllTasksUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
        this.completeTaskUseCase = completeTaskUseCase;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request) {
        Task created = createTaskUseCase.execute(request.title(), request.description(), request.priority());
        return ResponseEntity.status(HttpStatus.CREATED).body(TaskResponse.fromDomain(created));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> tasks = getAllTasksUseCase.execute().stream()
                .map(TaskResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        Task task = getAllTasksUseCase.execute().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new com.taskflow.backend.domain.exceptions.TaskNotFoundException(id));
        return ResponseEntity.ok(TaskResponse.fromDomain(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskRequest request) {
        Task updated = updateTaskUseCase.execute(id, request.title(), request.description());
        return ResponseEntity.ok(TaskResponse.fromDomain(updated));
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<TaskResponse> completeTask(@PathVariable Long id) {
        Task completed = completeTaskUseCase.execute(id);
        return ResponseEntity.ok(TaskResponse.fromDomain(completed));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        deleteTaskUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}