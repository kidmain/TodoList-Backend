package com.kidmain.todolist.repositories;

import com.kidmain.todolist.entities.TodoTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoTaskRepository extends JpaRepository<TodoTask, Long> {
    List<TodoTask> findAllByUserId(Long id);
}
