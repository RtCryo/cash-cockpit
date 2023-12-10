package org.myproject.cash.cockpit.handler.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder
@EqualsAndHashCode
@RequiredArgsConstructor
public class TransactionType {

    @EqualsAndHashCode.Exclude
    private final UUID id;

    @NotNull
    @NotBlank
    private final String type;

}
