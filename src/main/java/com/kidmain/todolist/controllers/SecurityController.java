package com.kidmain.todolist.controllers;

import com.kidmain.todolist.entities.TodoTask;
import com.kidmain.todolist.entities.TodoUser;
import com.kidmain.todolist.services.TodoTaskService;
import com.kidmain.todolist.services.TodoUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/s")
@Slf4j
public class SecurityController {
    private final TodoTaskService taskService;
    private final TodoUserService userService;

    public SecurityController(TodoTaskService taskService, TodoUserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("tasks")
    public List<TodoTask> getAllTasks() {
        log.info("GET request: .../tasks");
        return taskService.getAllTodoTasks();
    }

    @GetMapping("task/{id}")
    public TodoTask getTask(@PathVariable("id") Long id) {
        log.info("GET request: .../task/ " + id);
        return taskService.getTodoTask(id);
    }

    @GetMapping("tasks/{userId}")
    public List<TodoTask> getAllTasksByUser(@PathVariable("userId") Long id) {
        log.info("GET request: .../tasks/ " + id);
        return taskService.getAllTodoTasksByUser(id);
    }

    @Validated
    @PostMapping("task/add")
    public void addTask(@Valid @RequestBody TodoTask TodoTask) {
        log.info("POST request: .../add | {}", TodoTask);
        taskService.addTodoTask(TodoTask);
    }

    @PutMapping("task/edit/{id}")
    public ResponseEntity<TodoTask> updateTask(@PathVariable("id") Long id, @RequestBody TodoTask TodoTask) {
        log.info("PUT request: .../edit/" + id + " | {}", TodoTask);

        TodoTask newTodoTask = taskService.getTodoTask(id);
        if (TodoTask.getTask() != null) newTodoTask.setTask(TodoTask.getTask());
        newTodoTask.setDone(TodoTask.isDone());

        log.info("PUT request: .../edit/" + id + " | {}", newTodoTask);
        taskService.updateTodoTask(newTodoTask);
        return ResponseEntity.ok(newTodoTask);
    }

    @DeleteMapping("task/delete/{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        log.info("DELETE request: .../delete/" + id);
        taskService.deleteTodoTask(id);
    }

    @DeleteMapping("tasks/delete/all")
    public void deleteAllTasks() {
        log.info("DELETE request: .../delete/all");
        taskService.deleteAllTodoTasks();
    }

    @GetMapping("/test")
    public String securityTest() {
        return "Hello from prohibited page";
    }

    @GetMapping("users")
    public List<TodoUser> getAllUsers() {
        log.info("GET request: .../users");
        return userService.getAllTodoUsers();
    }

    @GetMapping("user/{id}")
    public TodoUser getUser(@PathVariable("id") Long id) {
        log.info("GET request: .../users/" + id);
        return userService.getTodoUser(id);
    }

    @Validated
    @PostMapping("user/add")
    public void addUser(@Valid @RequestBody TodoUser todoUser) {
        log.info("POST request: .../user/add | {}", todoUser);
        userService.addTodoUser(todoUser);
    }

    @PutMapping("user/edit/{id}")
    public ResponseEntity<TodoUser> updateUser(@PathVariable("id") Long id, @RequestBody TodoUser todoUser) {
        log.info("PUT request: .../user/edit/" + id + " | {}", todoUser);
        TodoUser newTodoUser = userService.getTodoUser(id);
        if (todoUser.getTotalScore() != null) newTodoUser.setTotalScore(todoUser.getTotalScore());

        userService.updateTodoUser(newTodoUser);
        return ResponseEntity.ok(newTodoUser);
    }

    @DeleteMapping("user/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        log.info("DELETE request: .../user/delete/" + id);
        userService.deleteTodoUser(id);
    }

    @DeleteMapping("users/delete/all")
    public void deleteAllUsers() {
        log.info("DELETE request: .../users/delete/all");
        userService.deleteAllTodoUsers();
    }
}
