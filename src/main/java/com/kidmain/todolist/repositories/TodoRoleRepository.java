package com.kidmain.todolist.repositories;

import com.kidmain.todolist.entities.TodoRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRoleRepository extends JpaRepository<TodoRole, Long> {
    Optional<TodoRole> findByName(String name);
}