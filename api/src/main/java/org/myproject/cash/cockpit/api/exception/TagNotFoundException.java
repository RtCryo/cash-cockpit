package org.myproject.cash.cockpit.api.exception;

public class TagNotFoundException extends ServiceRuntimeException {
    public TagNotFoundException() {
        super("Tag not found");
    }
}
