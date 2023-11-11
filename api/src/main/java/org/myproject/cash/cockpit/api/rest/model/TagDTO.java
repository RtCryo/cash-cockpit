package org.myproject.cash.cockpit.api.rest.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TagDTO(UUID id, @NotNull @NotBlank String tagName) {
}
