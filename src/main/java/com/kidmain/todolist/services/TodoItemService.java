package com.kidmain.todolist.services;

import com.kidmain.todolist.entities.TodoItem;
import com.kidmain.todolist.exceptions.TodoItemNotFoundException;
import com.kidmain.todolist.repositories.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoItemService {
    private TodoItemRepository repository;

    @Autowired
    public TodoItemService(TodoItemRepository repository) {
        this.repository = repository;
    }

    public TodoItem getTodoItem(Long id) {
        return repository.findById(id).
                orElseThrow(() -> new TodoItemNotFoundException("Item with " + id + " not found"));
    }

    public List<TodoItem> getAllTodoItems() {
        return repository.findAll();
    }

    public void deleteTodoItem(Long id) {
        repository.deleteById(id);
    }

    public void addTodoItem(TodoItem todoItem) {
        repository.save(todoItem);
    }

    public void updateTodoItem(TodoItem todoItem) {
        repository.save(todoItem);
    }
}
