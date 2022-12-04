package com.kidmain.todolist.repositories;

import com.kidmain.todolist.entities.TodoTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoTaskRepository extends CrudRepository<TodoTask, Long> {
    List<TodoTask> findAllByOrderById();
}
