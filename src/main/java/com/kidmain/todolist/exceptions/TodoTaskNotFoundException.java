package com.kidmain.todolist.exceptions;

public class TodoTaskNotFoundException extends RuntimeException {
    public TodoTaskNotFoundException(String message) {
        super(message);
    }
}
