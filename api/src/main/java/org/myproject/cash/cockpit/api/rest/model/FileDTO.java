package org.myproject.cash.cockpit.api.rest.model;

import java.util.UUID;

public record FileDTO(UUID id, byte[] fileByte) {
}
