package org.myproject.cash.cockpit.api.exception;

import java.io.IOException;

public class CreateFileDAOException extends ServiceRuntimeException {
    public CreateFileDAOException(final IOException e) {
        super(e.getMessage());
    }
}
