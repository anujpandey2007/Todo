package com.springboot.todolist.Service;

import com.springboot.todolist.Dto.TaskDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface TaskService {
    TaskDto createTodo(TaskDto taskDto);


    List<TaskDto> getAllTask();

    TaskDto getTaskById(Long id );

    TaskDto updateTask(Long id,TaskDto taskDto);

    void deleteTask(Long id );

}
