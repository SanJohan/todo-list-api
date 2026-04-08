package com.todo.app.todoapp.controller;

import com.todo.app.todoapp.dto.TaskRequestDTO;
import com.todo.app.todoapp.dto.TaskResponseDTO;
import com.todo.app.todoapp.exception.TaskNotFoundException;
import com.todo.app.todoapp.model.Task;
import com.todo.app.todoapp.service.TaskServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

    private final TaskServiceImpl taskServiceImpl;

    public TaskController(TaskServiceImpl taskServiceImpl){
        this.taskServiceImpl = taskServiceImpl;
    }

    @PostMapping
    ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskRequestDTO taskDto){
        return taskServiceImpl.createTask(taskDto);
    }

    @GetMapping
    ResponseEntity<List<TaskResponseDTO>> getTask(){
        return taskServiceImpl.getTasks();
    }

    @PutMapping("/{id}")
    ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO taskDto){
        return taskServiceImpl.updateTask(id,taskDto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTask(@PathVariable Long id){
        return taskServiceImpl.deleteTask(id);
    }

    @GetMapping("/pruebita")
    boolean getDos(){
        throw new TaskNotFoundException();
    }
}
