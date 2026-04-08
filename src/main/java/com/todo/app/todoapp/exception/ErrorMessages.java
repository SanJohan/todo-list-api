package com.todo.app.todoapp.exception;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    TASK_NOT_FOUND("No se encuentra esta tarea");

    private final String message;

    private ErrorMessages(String message){
        this.message = message;
    }

}
