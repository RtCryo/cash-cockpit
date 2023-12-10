package org.myproject.cash.cockpit.handler.exception;

public class DatepickerNullException extends RuntimeException {
    public DatepickerNullException() {
        super("Date range is invalid");
    }
}
