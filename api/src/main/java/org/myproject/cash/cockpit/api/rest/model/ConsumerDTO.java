package org.myproject.cash.cockpit.api.rest.model;

import jakarta.validation.constraints.NotEmpty;

public record ConsumerDTO(@NotEmpty String name) {
}
