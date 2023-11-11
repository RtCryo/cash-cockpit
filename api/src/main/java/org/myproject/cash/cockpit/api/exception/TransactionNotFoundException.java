package org.myproject.cash.cockpit.api.exception;

public class TransactionNotFoundException extends ServiceRuntimeException {
    public TransactionNotFoundException() {
        super("transaction not found");
    }
}
