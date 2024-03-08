package com.example.demo.core.exception;

// Exception class for handling cases when no MyListEntry is found by ID
public class NoMyListEntryByIdFoundException extends Exception {

    // Constructor with a message parameter
    public NoMyListEntryByIdFoundException(String message) {
        super(message);
    }
}
