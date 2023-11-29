package org.myproject.cash.cockpit.api.rest.model;

import lombok.Builder;

@Builder(toBuilder = true)
public record MessageDTO(int code, String message) {

    public static MessageDTO create(final int code, final String message) {
        return MessageDTO.builder()
                .code(code)
                .message(message)
                .build();
    }

}
