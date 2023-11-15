package org.myproject.cash.cockpit.api.exception;

public class FileInfoNotFoundException extends ServiceRuntimeException{
    public FileInfoNotFoundException(final String message) {
        super(message);
    }
}
