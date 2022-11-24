package com.kidmain.todolist.exceptions;

public class TodoUserNotFoundException extends RuntimeException {
    public TodoUserNotFoundException(String message) {
        super(message);
    }
}
