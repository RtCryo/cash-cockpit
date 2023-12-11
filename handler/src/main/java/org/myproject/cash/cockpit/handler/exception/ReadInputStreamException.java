package org.myproject.cash.cockpit.handler.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadInputStreamException extends RuntimeException {
    public ReadInputStreamException(final Exception e) {
        log.error(e.getMessage());
    }
}
