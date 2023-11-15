package org.myproject.cash.cockpit.api.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

public class ConvertStringToModelObjectMapperException extends ServiceRuntimeException {
    public ConvertStringToModelObjectMapperException(final JsonProcessingException e) {
        super(e.getMessage());
    }
}
