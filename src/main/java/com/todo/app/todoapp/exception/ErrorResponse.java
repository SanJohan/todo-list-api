package com.todo.app.todoapp.exception;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private int statusCode;
    private String message;
    private String path;
    private LocalDateTime timestamp;

    public ErrorResponse(int statusCode, String message, String path){
        this.statusCode = statusCode;
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }
}
