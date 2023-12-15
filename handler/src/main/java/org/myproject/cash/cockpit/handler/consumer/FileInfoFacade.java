package org.myproject.cash.cockpit.handler.consumer;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileInfoFacade {
    private UUID userId;
    private UUID fileInfoId;
    private byte[] file;
}
