package com.indtexbr.processoindustrial.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;

/**
 * @author lgdamy@raiadrogasil.com on 07/08/2020
 */
@Getter
public final class FuntimeException extends RuntimeException {

    private HttpStatus status;

    public FuntimeException(String message, @NotNull HttpStatus status) {
        super(message);
        this.status = status;
    }

    public FuntimeException(String message) {
        super(message);
        this.status = HttpStatus.EXPECTATION_FAILED;
    }
}
