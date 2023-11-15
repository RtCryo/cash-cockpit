package org.myproject.cash.cockpit.api.rest.model;

import java.time.LocalDate;
import java.util.UUID;

public record FileInfoDTO(UUID id, String name, String type, LocalDate start, LocalDate end, UUID bankStatement,
                          Boolean isHandled) {
}
