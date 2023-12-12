package org.myproject.cash.cockpit.api.service.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
@RequiredArgsConstructor
public final class FileInfoFacade {
    private final UUID userId;
    private final UUID fileInfoId;
    private final byte[] file;
}
