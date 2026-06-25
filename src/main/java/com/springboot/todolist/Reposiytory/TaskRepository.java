package com.springboot.todolist.Reposiytory;

import com.springboot.todolist.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
