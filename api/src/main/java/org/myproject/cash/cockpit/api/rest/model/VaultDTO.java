package org.myproject.cash.cockpit.api.rest.model;

import java.time.LocalDate;
import java.util.UUID;

public record VaultDTO(UUID id, LocalDate date, double sum) {
}
