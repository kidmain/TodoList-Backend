package com.kidmain.todolist.controllers;

import com.kidmain.todolist.entities.TodoUser;
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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class TodoUserController {
    private final TodoUserService service;

    public TodoUserController(TodoUserService service) {
        this.service = service;
    }

    @GetMapping("users")
    public List<TodoUser> getAllUsers() {
        return service.getAllTodoUsers();
    }

    @GetMapping("user/{id}")
    public TodoUser getUser(@PathVariable("id") Long id) {
        return service.getTodoUser(id);
    }

    @Validated
    @PostMapping("user/add")
    public void addUser(@Valid @RequestBody TodoUser todoUser) {
        service.addTodoUser(todoUser);
    }

    @PutMapping("user/edit/{id}")
    public ResponseEntity<TodoUser> updateUser(@PathVariable("id") Long id, @RequestBody TodoUser todoUser) {
        TodoUser newTodoUser = service.getTodoUser(id);
        if (todoUser.getTotalScore() != null) newTodoUser.setTotalScore(todoUser.getTotalScore());

        service.updateTodoUser(newTodoUser);
        return ResponseEntity.ok(newTodoUser);
    }

    @DeleteMapping("user/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        service.deleteTodoUser(id);
    }

    @DeleteMapping("users/delete/all")
    public void deleteAllUsers() {
        service.deleteAllTodoUsers();
    }
}
