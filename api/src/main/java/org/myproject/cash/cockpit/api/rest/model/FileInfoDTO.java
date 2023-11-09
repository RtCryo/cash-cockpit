package org.myproject.cash.cockpit.api.rest.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
public class FileInfoDTO {

    @Builder.Default
    private final UUID id = UUID.randomUUID();
    private final String name;
    private final String type;
    private final LocalDate start;
    private final LocalDate end;
    private final UUID bankStatementId;

}
