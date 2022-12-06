package com.kidmain.todolist.controllers;

import com.kidmain.todolist.entities.TodoTask;
import com.kidmain.todolist.services.TodoTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@Slf4j
public class TodoTaskController {
    private final TodoTaskService taskService;

    public TodoTaskController(TodoTaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TodoTask> getAllTasks() {
        log.info("GET request: .../tasks");
        return taskService.getAllTodoTasks();
    }

    @GetMapping("/{id}")
    public TodoTask getTask(@PathVariable("id") Long id) {
        log.info("GET request: .../task/ " + id);
        return taskService.getTodoTaskById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<TodoTask> addTask(@RequestBody TodoTask todoTask) {
        log.warn("POST request: .../add | {}", todoTask);
        if (taskService.addTodoTask(todoTask)) {
            log.info("POST request: .../add | {}", todoTask);
            return new ResponseEntity<>(todoTask, HttpStatus.OK);
        } else {
            log.error("POST request: .../add | {}", todoTask);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<TodoTask> updateTask(
            @PathVariable("id") Long id,
            @RequestBody TodoTask todoTask
    ) {
        log.warn("PUT request: .../add | {}", todoTask);

        TodoTask task = taskService.getTodoTaskById(id);
        if (todoTask.getTask() != null) {
            task.setTask(todoTask.getTask());
        }
        if (todoTask.isDone() != null) {
            task.setDone(todoTask.isDone());
        }

        if (taskService.updateTodoTask(task)) {
            log.info("PUT request: .../add | {}", task);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            log.error("PUT request: .../add | {}", task);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}/delete")
    public void deleteTask(@PathVariable("id") Long id) {
        log.warn("DELETE request: .../delete/" + id);
        taskService.deleteTodoTaskById(id);
    }

    @DeleteMapping("/delete/all")
    public void deleteAllTasks() {
        log.info("DELETE request: .../delete/all");
        taskService.deleteAllTodoTasks();
    }
}
