package org.myproject.cash.cockpit.api.rest.model;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record TransactionInfoDTO(UUID id, @NotEmpty String info) {
}
