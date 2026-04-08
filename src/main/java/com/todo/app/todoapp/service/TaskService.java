package com.todo.app.todoapp.service;

import com.todo.app.todoapp.dto.TaskRequestDTO;
import com.todo.app.todoapp.dto.TaskResponseDTO;
import com.todo.app.todoapp.model.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {
    public ResponseEntity<TaskResponseDTO> createTask(TaskRequestDTO task);
    public ResponseEntity<TaskResponseDTO> updateTask(Long id, TaskRequestDTO task);
    public ResponseEntity<List<TaskResponseDTO>> getTasks();
    public ResponseEntity<Void> deleteTask(Long id);
}
