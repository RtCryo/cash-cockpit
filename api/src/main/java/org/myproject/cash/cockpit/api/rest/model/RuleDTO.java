package org.myproject.cash.cockpit.api.rest.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class RuleDTO {

    @NotNull(message = "tag can't be null")
    private final UUID tagId;
    @NotEmpty(message = "keywords can't be empty")
    private final List<String> has;
    @Pattern(regexp = "INFO|CONSUMER|TYPE", message = "area can be INFO, CONSUMER or TYPE")
    private final String area;

}
