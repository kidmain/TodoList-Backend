package com.kidmain.todolist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TodoTaskNotFoundException extends RuntimeException {
    public TodoTaskNotFoundException(String message) {
        super(message);
    }
}
