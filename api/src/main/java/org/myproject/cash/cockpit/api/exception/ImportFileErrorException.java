package org.myproject.cash.cockpit.api.exception;

public class ImportFileErrorException extends ServiceRuntimeException {
    public ImportFileErrorException(final Exception e) {
        super(e.getMessage());
    }
}
