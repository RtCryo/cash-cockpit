package org.myproject.cash.cockpit.handler.producer;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ReportDto(UUID userId, UUID fileInfoId, int found, int saved, boolean successful, String errorMsg) {
}
