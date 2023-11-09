package org.myproject.cash.cockpit.api.rest.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder(toBuilder = true)
public record RuleDTO(@NotNull(message = "tag can't be null") UUID tagId,
                      @NotEmpty(message = "keywords can't be empty") List<String> has,
                      @Pattern(regexp = "INFO|CONSUMER|TYPE", message = "area can be INFO, CONSUMER or TYPE") String area) {
}
