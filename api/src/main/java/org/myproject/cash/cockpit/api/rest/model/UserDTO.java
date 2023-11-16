package org.myproject.cash.cockpit.api.rest.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserDTO (@NotBlank String username, @NotBlank String password){ }
