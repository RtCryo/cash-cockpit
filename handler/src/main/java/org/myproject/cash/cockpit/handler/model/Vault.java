package org.myproject.cash.cockpit.handler.model;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode
@RequiredArgsConstructor
public class Vault {

    private final UUID id;
    private final LocalDate date;
    private final double sum;

}
