package org.myproject.cash.cockpit.api.rest.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ConsumerDTO {

    @NotEmpty
    private final String name;

}
