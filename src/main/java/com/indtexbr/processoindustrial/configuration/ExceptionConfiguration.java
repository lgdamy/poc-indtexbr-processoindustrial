package com.indtexbr.processoindustrial.configuration;

import com.indtexbr.processoindustrial.domain.dto.ResultError;
import com.indtexbr.processoindustrial.exception.FuntimeException;
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
    public ResponseEntity<ResultError> funtimeException(FuntimeException fe) {
        return ResponseEntity
                .status(fe.getStatus())
                .body(new ResultError(fe.getMessage(), fe.getClass().getSimpleName()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultError> exception(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResultError(e.getMessage() != null ? e.getMessage(): "N/A", e.getClass().getSimpleName() ));
    }
}
