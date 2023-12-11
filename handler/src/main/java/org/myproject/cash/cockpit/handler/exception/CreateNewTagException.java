package org.myproject.cash.cockpit.handler.exception;

public class CreateNewTagException extends RuntimeException {
    public CreateNewTagException(String s) {
        super("Tag not created, is exist or not present: " + s);
    }
}
