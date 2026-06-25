package com.springboot.todolist.Controller;

import com.springboot.todolist.Dto.TaskDto;
import com.springboot.todolist.Service.TaskService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/todo_list")
public class TaskController {


    private final  TaskService taskService;



    @PostMapping
    public ResponseEntity<TaskDto> createTodo(@RequestBody TaskDto taskDto){
        TaskDto createdtodo = taskService.createTodo(taskDto);
        return new ResponseEntity<>(createdtodo , HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTask(){
        List<TaskDto> tasks = taskService.getAllTask();
        return new ResponseEntity<>(tasks , HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id){
        TaskDto taskDto = taskService.getTaskById(id);
        return new ResponseEntity<>(taskDto,HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTodo(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        TaskDto updatedTodo = taskService.updateTask(id, taskDto);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>("Todo item deleted successfully.", HttpStatus.OK);
    }





}













