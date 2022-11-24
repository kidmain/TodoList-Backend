package com.kidmain.todolist.repositories;

import com.kidmain.todolist.entities.TodoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoUserRepository extends JpaRepository<TodoUser, Long> {
}
