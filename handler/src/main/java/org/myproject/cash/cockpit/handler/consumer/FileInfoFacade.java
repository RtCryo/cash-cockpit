package org.myproject.cash.cockpit.handler.consumer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class FileInfoFacade {
    private UUID userId;
    private UUID fileInfoId;
    private byte[] file;
}
