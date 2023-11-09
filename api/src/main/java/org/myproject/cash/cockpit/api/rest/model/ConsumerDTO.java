package org.myproject.cash.cockpit.api.rest.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class ConsumerDTO {

    @NotEmpty
    private final String name;

}
