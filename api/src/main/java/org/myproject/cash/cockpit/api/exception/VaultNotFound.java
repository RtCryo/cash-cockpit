package org.myproject.cash.cockpit.api.exception;

public class VaultNotFound extends ServiceRuntimeException {
    public VaultNotFound() {
        super("Vault not found");
    }
}
