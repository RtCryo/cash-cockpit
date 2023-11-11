package org.myproject.cash.cockpit.api.exception;

public class ServiceRuntimeException extends RuntimeException {
    public ServiceRuntimeException(final String message) {
        super(message);
    }
}
