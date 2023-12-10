package org.myproject.cash.cockpit.handler.exception;

public class FileTypeNotSupported extends RuntimeException {
    public FileTypeNotSupported() {
        super("File type not supported");
    }
}
