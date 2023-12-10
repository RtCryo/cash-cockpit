package org.myproject.cash.cockpit.handler.exception;

public class RuleNotFoundException extends RuntimeException {
    public RuleNotFoundException() {
        super("Rule not found");
    }
}
