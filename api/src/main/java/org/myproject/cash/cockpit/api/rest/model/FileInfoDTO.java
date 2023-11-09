package org.myproject.cash.cockpit.api.rest.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class FileInfoDTO {

    private final UUID id;
    private final String name;
    private final String type;
    private final LocalDate start;
    private final LocalDate end;
    private final UUID bankStatement;

}
