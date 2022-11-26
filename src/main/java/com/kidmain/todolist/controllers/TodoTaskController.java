package com.kidmain.todolist.controllers;

import com.kidmain.todolist.entities.TodoTask;
import com.kidmain.todolist.services.TodoTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class TodoTaskController {
    private final TodoTaskService service;

    public TodoTaskController(TodoTaskService service) {
        this.service = service;
    }

    @GetMapping("tasks")
    public List<TodoTask> getAllTasks() {
        log.info("GET request: .../tasks");
        return service.getAllTodoTasks();
    }

    @GetMapping("task/{id}")
    public TodoTask getTask(@PathVariable("id") Long id) {
        log.info("GET request: .../task/ " + id);
        return service.getTodoTask(id);
    }

    @GetMapping("tasks/{userId}")
    public List<TodoTask> getAllTasksByUser(@PathVariable("userId") Long id) {
        log.info("GET request: .../tasks/ " + id);
        return service.getAllTodoTasksByUser(id);
    }

    @Validated
    @PostMapping("task/add")
    public void addTask(@Valid @RequestBody TodoTask TodoTask) {
        log.info("POST request: .../add | {}", TodoTask);
        service.addTodoTask(TodoTask);
    }

    @PutMapping("task/edit/{id}")
    public ResponseEntity<TodoTask> updateTask(@PathVariable("id") Long id, @RequestBody TodoTask TodoTask) {
        log.info("PUT request: .../edit/" + id + " | {}", TodoTask);

        TodoTask newTodoTask = service.getTodoTask(id);
        if (TodoTask.getTask() != null) newTodoTask.setTask(TodoTask.getTask());
        newTodoTask.setDone(TodoTask.isDone());

        log.info("PUT request: .../edit/" + id + " | {}", newTodoTask);
        service.updateTodoTask(newTodoTask);
        return ResponseEntity.ok(newTodoTask);
    }

    @DeleteMapping("task/delete/{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        log.info("DELETE request: .../delete/" + id);
        service.deleteTodoTask(id);
    }

    @DeleteMapping("tasks/delete/all")
    public void deleteAllTasks() {
        log.info("DELETE request: .../delete/all");
        service.deleteAllTodoTasks();
    }
}
