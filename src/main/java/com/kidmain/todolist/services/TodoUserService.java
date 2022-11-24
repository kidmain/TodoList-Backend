package com.kidmain.todolist.services;

import com.kidmain.todolist.entities.TodoUser;
import com.kidmain.todolist.exceptions.TodoUserNotFoundException;
import com.kidmain.todolist.repositories.TodoUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TodoUserService {
    private final TodoUserRepository repository;

    @Autowired
    public TodoUserService(TodoUserRepository repository) {
        this.repository = repository;
    }

    public TodoUser getTodoUser(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new TodoUserNotFoundException("User with " + id + " not found");
        });
    }

    public List<TodoUser> getAllTodoUsers() {
        return repository.findAll();
    }

    public void deleteTodoUser(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllTodoUsers() {
        repository.deleteAll();
    }

    public void addTodoUser(TodoUser todoUser) {
        repository.save(todoUser);
    }

    public void updateTodoUser(TodoUser todoUser) {
        repository.save(todoUser);
    }
}
