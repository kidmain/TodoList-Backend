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
    private final int TASK_NAME_LENGTH = 3;
    private final TodoTaskRepository repository;

    @Autowired
    public TodoTaskService(TodoTaskRepository repository) {
        this.repository = repository;
    }

    public TodoTask getTodoTaskById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.error("Task with id: " + id + " not found");
            throw new TodoTaskNotFoundException("Task with " + id + " not found");
        });
    }

    public List<TodoTask> getAllTodoTasks() {
        return repository.findAllByOrderById();
    }

    public void deleteTodoTaskById(Long id) {
        repository.delete(getTodoTaskById(id));
    }

    public void deleteAllTodoTasks() {
        repository.deleteAll();
    }

    public boolean addTodoTask(TodoTask todoTask) {
        if (todoTask.getTask() == null || todoTask.getTask().isEmpty() || todoTask.getTask().isBlank()) return false;
        if (todoTask.getTask().length() < TASK_NAME_LENGTH) return false;
        else {
            repository.save(todoTask);
            return true;
        }
    }

    public boolean updateTodoTask(TodoTask todoTask) {
        if (todoTask.getTask().isEmpty() || todoTask.getTask().isBlank()) return false;
        if (todoTask.getTask().length() < TASK_NAME_LENGTH) return false;
        else {
            repository.save(todoTask);
            return true;
        }
    }
}
