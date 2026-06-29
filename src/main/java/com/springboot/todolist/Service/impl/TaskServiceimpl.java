package com.springboot.todolist.Service.impl;

import com.springboot.todolist.Dto.TaskDto;
import com.springboot.todolist.Entity.Task;
import com.springboot.todolist.Entity.Users;
import com.springboot.todolist.Reposiytory.TaskRepository;
import com.springboot.todolist.Reposiytory.UserRepository;
import com.springboot.todolist.Service.TaskService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service

public class TaskServiceimpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskServiceimpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TaskDto createTodo(TaskDto taskDto,Long userId) {
        Task todo = mapToEntity(taskDto);
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        todo.setUser(user);
        Task savedTodo = taskRepository.save(todo);
        return mapToDto(savedTodo);
    }

    @Override
    public List<TaskDto> getAllTask(Long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        return tasks.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public TaskDto getTaskById(Long id,Long userId) {
        Task todo = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("task not found with id: " + id));
        if (!todo.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized! You cannot delete this task.");
        }
        return mapToDto(todo);
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto,Long userId) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() ->new RuntimeException("task not found with id " + id ));
        task.setTitle(taskDto.getTitle());
        if (!task.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized! You cannot update this task.");
        }
        task.setDescription(taskDto.getDescription());
        task.setCompleted(taskDto.isCompleted());
        Task updatedTask = taskRepository.save(task);

        return mapToDto(updatedTask);
    }

    @Override
    public void deleteTask(Long id,Long userId) {
        Task todo = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo task not found with id: " + id));
        if (!todo.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized! You cannot delete this task.");
        }
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
