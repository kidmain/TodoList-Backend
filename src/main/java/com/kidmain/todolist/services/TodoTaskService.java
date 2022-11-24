package com.kidmain.todolist.services;

import com.kidmain.todolist.entities.TodoTask;
import com.kidmain.todolist.exceptions.TodoTaskNotFoundException;
import com.kidmain.todolist.repositories.TodoTaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TodoTaskService {
    private final TodoTaskRepository repository;

    @Autowired
    public TodoTaskService(TodoTaskRepository repository) {
        this.repository = repository;
    }

    public TodoTask getTodoTask(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.error("Task with id: " + id + " not found");
            throw new TodoTaskNotFoundException("Task with " + id + " not found");
        });
    }

    public List<TodoTask> getAllTodoTasks() {
        return repository.findAll();
    }

    public void deleteTodoTask(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllTodoTasks() {
        repository.deleteAll();
    }

    public void addTodoTask(TodoTask todoTask) {
        repository.save(todoTask);
    }

    public void updateTodoTask(TodoTask todoTask) {
        repository.save(todoTask);
    }

    public List<TodoTask> getAllTodoTasksByUser(Long id) {
        return repository.findAllByUserId(id);
    }
}
