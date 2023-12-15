package org.myproject.cash.cockpit.messenger.model;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ReportDto(UUID userId, UUID fileInfoId, int found, int saved, boolean successful, String msg) {
}
