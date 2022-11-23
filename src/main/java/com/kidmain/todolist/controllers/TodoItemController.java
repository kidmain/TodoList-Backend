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
import java.time.Duration;
import java.util.List;

@RestController
@Slf4j
public class TodoItemController {
    private final TodoItemService todoItemService;

    public TodoItemController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @GetMapping
    public String get() {
        log.info("GET request: .../ | SUCCESS");
        return "Hello from GET request";
    }

    @GetMapping("/item/{id}")
    public TodoItem getItem(@PathVariable("id") Long id) {
        log.info("GET request: .../item/" + id + " | SUCCESS");
        return todoItemService.getTodoItem(id);
    }

    @GetMapping("/items")
    public List<TodoItem> getAllItems() {
        log.info("GET request: .../items | SUCCESS");
        return todoItemService.getAllTodoItems();
    }

    @PostMapping()
    public String post() {
        log.info("POST request: .../ | SUCCESS");
        return "Hello from POST request";
    }

    @Validated
    @PostMapping("/add")
    public void addItem(@Valid @RequestBody TodoItem todoItem) {
        todoItemService.addTodoItem(todoItem);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TodoItem> updateItem(@PathVariable("id") Long id, @RequestBody TodoItem todoItem) {
        TodoItem newTodoItem = todoItemService.getTodoItem(id);
        if (todoItem.getAction() != null) newTodoItem.setAction(todoItem.getAction());
        newTodoItem.setDone(todoItem.isDone());

        todoItemService.updateTodoItem(newTodoItem);

        return ResponseEntity.ok(newTodoItem);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteItem(@PathVariable("id") Long id) {
        todoItemService.deleteTodoItem(id);
    }
}
