package com.todo.app.todoapp.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequestDTO {
    private String title;
    private String description;
    private boolean status;
}
