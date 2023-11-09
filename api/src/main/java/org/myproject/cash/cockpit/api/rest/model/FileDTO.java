package org.myproject.cash.cockpit.api.rest.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder(toBuilder = true)
public class FileDTO {

    private final UUID id;
    private final byte[] fileByte;

}
