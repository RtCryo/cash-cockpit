package org.myproject.cash.cockpit.api.exception;

public class UserNotFoundException extends ServiceRuntimeException {
    public UserNotFoundException(final String s) {
        super(s);
    }
}
