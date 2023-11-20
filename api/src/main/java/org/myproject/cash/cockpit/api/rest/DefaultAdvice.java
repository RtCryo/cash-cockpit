package org.myproject.cash.cockpit.api.rest;

import org.myproject.cash.cockpit.api.exception.ServiceRuntimeException;
import org.myproject.cash.cockpit.api.rest.model.MessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultAdvice extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ServiceRuntimeException.class)
    public MessageDTO handleDefaultError(final ServiceRuntimeException exception){
        return MessageDTO.create(500, exception.getMessage());
    }

}
