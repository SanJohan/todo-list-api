package com.todo.app.todoapp.service;

import com.todo.app.todoapp.dto.TaskRequestDTO;
import com.todo.app.todoapp.dto.TaskResponseDTO;
import com.todo.app.todoapp.exception.TaskNotFoundException;
import com.todo.app.todoapp.mapper.Mapper;
import com.todo.app.todoapp.model.Task;
import com.todo.app.todoapp.repository.TaskRepository;
import lombok.Builder;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public ResponseEntity<TaskResponseDTO> createTask(TaskRequestDTO taskDto){
        Task task = Task.builder().
                title(taskDto.getTitle()).
                description(taskDto.getDescription())
                .status(taskDto.isStatus()).
                build();
        TaskResponseDTO taskResponseDTO = Mapper.taskResponseToDto(taskRepository.save(task));

        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponseDTO);
    }

    public ResponseEntity<TaskResponseDTO> updateTask(Long id, TaskRequestDTO taskDto){
        Task task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.isStatus());

        TaskResponseDTO taskResponseDTO = Mapper.taskResponseToDto(taskRepository.save(task));

        return ResponseEntity.status(HttpStatus.OK).body(taskResponseDTO);
    }

    public ResponseEntity<List<TaskResponseDTO>> getTasks(){
        List<TaskResponseDTO> tasks = taskRepository.findAll().stream().map(Mapper::taskResponseToDto).toList();// t -> Mapper.taskResponseToDto(t)
        System.out.println("get tareas: " + tasks.stream().map(TaskResponseDTO::getTitle));// t-> t.getTitle()
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    public ResponseEntity<Void> deleteTask(Long id){
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            taskRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new TaskNotFoundException();
    }


}
