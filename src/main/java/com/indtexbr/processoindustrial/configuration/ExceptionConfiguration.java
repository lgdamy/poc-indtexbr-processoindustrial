package com.indtexbr.processoindustrial.configuration;

import com.indtexbr.processoindustrial.domain.dto.Result;
import com.indtexbr.processoindustrial.exception.FuntimeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author lgdamy@raiadrogasil.com on 08/08/2020
 */
@ControllerAdvice
public class ExceptionConfiguration {

    @ExceptionHandler(FuntimeException.class)
    public ResponseEntity<Result> funtimeException(FuntimeException fe) {
        return ResponseEntity
                .ok()
                .body(new Result(fe.getMessage(), true));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> exception(Exception e) {
        return ResponseEntity
                .ok()
                .body(new Result(e.getClass().getSimpleName() + ": " + e.getMessage() != null ? e.getMessage(): "N/A", true ));
    }
}
