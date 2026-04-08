package com.todo.app.todoapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFoundException  extends  RuntimeException {

    public TaskNotFoundException() {
        super(ErrorMessages.TASK_NOT_FOUND.getMessage());
    }
}
