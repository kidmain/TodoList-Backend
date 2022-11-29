package com.kidmain.todolist.services;

import com.kidmain.todolist.entities.TodoRole;
import com.kidmain.todolist.entities.TodoUser;
import com.kidmain.todolist.exceptions.TodoUserNotFoundException;
import com.kidmain.todolist.repositories.TodoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoUserDetailsService implements UserDetailsService {
    private final TodoUserRepository repository;

    @Autowired
    public TodoUserDetailsService(TodoUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TodoUser user = repository.findByUsername(username)
                .orElseThrow( () -> {
                    throw new TodoUserNotFoundException("User with {" + username + "} not found");
                } );
        return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<TodoRole> roles) {
        return roles.stream()
                .map( role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}