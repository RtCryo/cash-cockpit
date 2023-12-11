package org.myproject.cash.cockpit.handler.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileInfoNotFoundException extends RuntimeException {

    public FileInfoNotFoundException(String e) {
        log.error(e);
    }
}
