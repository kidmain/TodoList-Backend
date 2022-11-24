package com.kidmain.todolist.services;

import com.kidmain.todolist.entities.TodoItem;
import com.kidmain.todolist.exceptions.TodoItemNotFoundException;
import com.kidmain.todolist.repositories.TodoItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TodoItemService {
    private TodoItemRepository repository;

    @Autowired
    public TodoItemService(TodoItemRepository repository) {
        this.repository = repository;
    }

    public TodoItem getTodoItem(Long id) {
        log.info("Get todoItem with id: " + id);
        return repository.findById(id).orElseThrow(() -> {
            log.error("Item with id: " + id + " not found");
            throw new TodoItemNotFoundException("Item with " + id + " not found");
        });
    }

    public List<TodoItem> getAllTodoItems() {
        return repository.findAll();
    }

    public void deleteTodoItem(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllTodoItems() {
        repository.deleteAll();
    }

    public void addTodoItem(TodoItem todoItem) {
        repository.save(todoItem);
    }

    public void updateTodoItem(TodoItem todoItem) {
        repository.save(todoItem);
    }

    public List<TodoItem> getAllTodoItemsByUser(Long id) {
        return repository.findAllByUserId(id);
    }
}
