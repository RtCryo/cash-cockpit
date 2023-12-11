package org.myproject.cash.cockpit.handler.exception;

public class TransactionNotFound extends RuntimeException {
    public TransactionNotFound() {
        super("Transaction not found");
    }
}
