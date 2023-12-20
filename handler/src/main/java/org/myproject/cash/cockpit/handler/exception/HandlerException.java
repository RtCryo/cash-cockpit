package org.myproject.cash.cockpit.handler.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class HandlerException extends RuntimeException {

    private final String message;

    public HandlerException(final String message) {
        this.message = message;
        log.error(message);
    }
}
