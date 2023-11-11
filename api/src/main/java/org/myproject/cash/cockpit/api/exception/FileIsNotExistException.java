package org.myproject.cash.cockpit.api.exception;

public class FileIsNotExistException extends ServiceRuntimeException {
    public FileIsNotExistException(final String message) {
        super(message);
    }
}
