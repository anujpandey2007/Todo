package com.springboot.todolist.Service.impl;

import com.springboot.todolist.Dto.TaskDto;
import com.springboot.todolist.Entity.Task;
import com.springboot.todolist.Reposiytory.TaskRepository;
import com.springboot.todolist.Service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service

public class TaskServiceimpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceimpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDto createTodo(TaskDto taskDto) {
        Task todo = mapToEntity(taskDto);
        Task savedTodo = taskRepository.save(todo);
        return mapToDto(savedTodo);
    }

    @Override
    public List<TaskDto> getAllTask() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public TaskDto getTaskById(Long id) {
        Task todo = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("task not found with id: " + id));
        return mapToDto(todo);
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() ->new RuntimeException("task not found with id " + id ));
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setCompleted(taskDto.isCompleted());
        Task updatedTask = taskRepository.save(task);

        return mapToDto(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        Task todo = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo task not found with id: " + id));
        taskRepository.delete(todo);

    }


    // helper helper helper helper
    private Task mapToEntity(TaskDto dto) {
        Task entity = new Task();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setCompleted(dto.isCompleted());
        return entity;
    }

    // Helper Method: Convert Entity to DTO
    private TaskDto mapToDto(Task entity) {
        TaskDto dto = new TaskDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setCompleted(entity.isCompleted());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}
