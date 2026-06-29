package com.springboot.todolist.Controller;

import com.springboot.todolist.Dto.TaskDto;
import com.springboot.todolist.Entity.UserPrincipal;
import com.springboot.todolist.Service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/todo_list")
public class TaskController {


    private final  TaskService taskService;



    @PostMapping
    public ResponseEntity<TaskDto> createTodo(@RequestBody TaskDto taskDto,@AuthenticationPrincipal UserPrincipal principal){
        TaskDto createdtodo = taskService.createTodo(taskDto,principal.getId());
        return new ResponseEntity<>(createdtodo , HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTask(@AuthenticationPrincipal UserPrincipal principal){
        Long userId = principal.getId();
        List<TaskDto> tasks = taskService.getAllTask(userId);
        return new ResponseEntity<>(tasks , HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id,@AuthenticationPrincipal UserPrincipal principal){
        Long userId = principal.getId();
        TaskDto taskDto = taskService.getTaskById(id,userId);
        return new ResponseEntity<>(taskDto,HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTodo(@PathVariable Long id, @RequestBody TaskDto taskDto,@AuthenticationPrincipal UserPrincipal principal) {
        Long userId = principal.getId();
        TaskDto updatedTodo = taskService.updateTask(id, taskDto,userId);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id,@AuthenticationPrincipal UserPrincipal principal) {
        Long userId = principal.getId();
        taskService.deleteTask(id,userId);
        return new ResponseEntity<>("Todo item deleted successfully.", HttpStatus.OK);
    }





}













