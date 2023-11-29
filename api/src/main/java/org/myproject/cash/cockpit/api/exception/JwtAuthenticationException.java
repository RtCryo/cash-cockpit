package org.myproject.cash.cockpit.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class JwtAuthenticationException extends ServiceRuntimeException {

    private final HttpStatus httpStatus;

    public JwtAuthenticationException(final String msg, final HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }

}
