package com.kidmain.todolist.repositories;

import com.kidmain.todolist.entities.TodoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoUserRepository extends JpaRepository<TodoUser, Long> {
    Optional<TodoUser> findByUsername(String username);
    Boolean existsByUsername(String username);
}
