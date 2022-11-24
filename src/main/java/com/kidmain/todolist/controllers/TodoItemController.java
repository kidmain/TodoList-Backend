package com.kidmain.todolist.controllers;

import com.kidmain.todolist.entities.TodoItem;
import com.kidmain.todolist.services.TodoItemService;
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
public class TodoItemController {
    private final TodoItemService service;

    public TodoItemController(TodoItemService service) {
        this.service = service;
    }

    @GetMapping("/items")
    public List<TodoItem> getAllItems() {
        log.info("GET request: .../items");
        return service.getAllTodoItems();
    }

    @GetMapping("/item/{id}")
    public TodoItem getItem(@PathVariable("id") Long id) {
        log.info("GET request: .../item/ " + id);
        return service.getTodoItem(id);
    }

    @GetMapping("/items/{userId}")
    public List<TodoItem> getAllItemsByUser(@PathVariable("userId") Long id) {
        log.info("GET request: .../items/ " + id);
        return service.getAllTodoItemsByUser(id);
    }

    @Validated
    @PostMapping("/add")
    public void addItem(@Valid @RequestBody TodoItem todoItem) {
        log.info("POST request: .../add | {}", todoItem);
        service.addTodoItem(todoItem);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TodoItem> updateItem(@PathVariable("id") Long id, @RequestBody TodoItem todoItem) {
        log.info("PUT request: .../edit/" + id + " | {}", todoItem);

        TodoItem newTodoItem = service.getTodoItem(id);
        if (todoItem.getAction() != null) newTodoItem.setAction(todoItem.getAction());
        newTodoItem.setDone(todoItem.isDone());

        log.info("PUT request: .../edit/" + id + " | {}", newTodoItem);
        service.updateTodoItem(newTodoItem);
        return ResponseEntity.ok(newTodoItem);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteItem(@PathVariable("id") Long id) {
        log.info("DELETE request: .../delete/" + id);
        service.deleteTodoItem(id);
    }

    @DeleteMapping("/delete/all")
    public void deleteAllItems() {
        log.info("DELETE request: .../delete/all");
        service.deleteAllTodoItems();
    }
}
