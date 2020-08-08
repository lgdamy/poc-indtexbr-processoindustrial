package com.indtexbr.processoindustrial.configuration;

import com.indtexbr.processoindustrial.exception.FuntimeException;
import org.springframework.core.NestedCheckedException;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author lgdamy@raiadrogasil.com on 08/08/2020
 */
@ControllerAdvice
public class ExceptionConfiguration {

    @ExceptionHandler(FuntimeException.class)
    public ResponseEntity<String> funtimeException(FuntimeException fe) {
        return ResponseEntity
                .status(fe.getStatus())
                .body(fe.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getClass().getSimpleName() + ": " + e.getMessage() != null ? e.getMessage(): "N/A");
    }
}
