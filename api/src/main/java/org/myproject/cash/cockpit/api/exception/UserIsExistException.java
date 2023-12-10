package org.myproject.cash.cockpit.api.exception;

public class UserIsExistException extends ServiceRuntimeException {
    public UserIsExistException(final String message) {
        super(message);
    }
}
