package org.myproject.cash.cockpit.api.exception;

public class RuleNotFoundException extends ServiceRuntimeException {
    public RuleNotFoundException() {
        super("Rule not found");
    }
}
