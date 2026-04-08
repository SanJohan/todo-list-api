package com.todo.app.todoapp.mapper;

import com.todo.app.todoapp.dto.TaskRequestDTO;
import com.todo.app.todoapp.dto.TaskResponseDTO;
import com.todo.app.todoapp.model.Task;

public class Mapper {

    public static TaskResponseDTO taskResponseToDto(Task task){

        if(task == null) return null;

        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setId(task.getId());
        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setStatus(task.isStatus());

        return taskResponseDTO;
    }

    public static TaskRequestDTO taskRequestToDto(Task task){

        if(task == null) return null;

        TaskRequestDTO taskRequestDTO = new TaskRequestDTO();
        taskRequestDTO.setTitle(task.getTitle());
        taskRequestDTO.setDescription(task.getDescription());
        taskRequestDTO.setStatus(task.isStatus());

        return taskRequestDTO;
    }



}
