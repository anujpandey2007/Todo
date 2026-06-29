package com.springboot.todolist.Service;

import com.springboot.todolist.Dto.TaskDto;

import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface TaskService {
    TaskDto createTodo(TaskDto taskDto,Long userId);


    List<TaskDto> getAllTask(Long userId);

    TaskDto getTaskById(Long id,Long userId );

    TaskDto updateTask(Long id,TaskDto taskDto,Long userId);

    void deleteTask(Long id,Long userId);

}
