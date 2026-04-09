package com.todo.app.todoapp;

import com.todo.app.todoapp.dto.TaskRequestDTO;
import com.todo.app.todoapp.dto.TaskResponseDTO;
import com.todo.app.todoapp.mapper.Mapper;
import com.todo.app.todoapp.model.Task;
import com.todo.app.todoapp.repository.TaskRepository;
import com.todo.app.todoapp.service.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task task;
    private TaskRequestDTO requestDTO;
    private TaskResponseDTO responseDTO;

    @BeforeEach
    void setUp(){
        task = Task.builder().
                id(1L).
                title("Tare de prueba").
                description("Descripcion de prueba").
                status(true).
                build();

        requestDTO = new TaskRequestDTO();
        requestDTO.setTitle("Tarea de prueba");
        requestDTO.setDescription("Descripcion de prueba");
        requestDTO.setStatus(true);

        responseDTO = new TaskResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setTitle("Tarea de prueba");
        responseDTO.setStatus(true);
    }

    @Test
    @DisplayName("createTask debería crear una tarea y retorna 201 created")
    void createTask_ShouldReturnCreatedTask() {
        given(taskRepository.save(any(Task.class))).willReturn(task);

        try (MockedStatic<Mapper> mockedMapper = mockStatic(Mapper.class)){
            mockedMapper.when(() -> Mapper.taskResponseToDto(task))
                    .thenReturn(responseDTO);

            //Act
            ResponseEntity<TaskResponseDTO> response = taskService.createTask(requestDTO);

            //Assert
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            assertThat(response.getBody()).isNotNull();
            assertThat(response.getBody().getId()).isEqualTo(1L);
            assertThat(response.getBody().getTitle()).isEqualTo("Tarea de prueba");
        }

        verify(taskRepository).save(any(Task.class));
    }

}
