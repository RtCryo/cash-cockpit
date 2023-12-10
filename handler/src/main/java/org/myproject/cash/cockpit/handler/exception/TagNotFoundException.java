package org.myproject.cash.cockpit.handler.exception;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException() {
        super("Tag not found");
    }
}
