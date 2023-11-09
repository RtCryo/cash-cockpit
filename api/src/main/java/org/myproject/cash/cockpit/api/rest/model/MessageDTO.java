package org.myproject.cash.cockpit.api.rest.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class MessageDTO {

    private final int code;
    private final String message;

    public static MessageDTO create(final int code, final String message) {
        return MessageDTO.builder()
                .code(code)
                .message(message)
                .build();
    }

}
