package org.myproject.cash.cockpit.handler.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
@EqualsAndHashCode
@RequiredArgsConstructor
public class FileInfo {

    @Builder.Default
    private final UUID id = UUID.randomUUID();
    private final String name;
    private final String type;
    private final LocalDate start;
    private final LocalDate end;
    private final UUID bankStatementId;
    private final ProgressStatus status;
}
