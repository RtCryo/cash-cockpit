package org.myproject.cash.cockpit.api.rest.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record FilterDTO(LocalDate localDateStart, LocalDate localDateEnd, List<UUID> tags) {
}
