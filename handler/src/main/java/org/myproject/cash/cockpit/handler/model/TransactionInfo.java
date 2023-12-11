package org.myproject.cash.cockpit.handler.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@EqualsAndHashCode
@RequiredArgsConstructor
public class TransactionInfo {

    @EqualsAndHashCode.Exclude
    private final UUID id;
    private final String info;

}
