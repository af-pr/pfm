package com.afpr.pfm.finance.shared.infrastructure.http;

import com.afpr.pfm.finance.shared.exception.ConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Void> handleConflict(ConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
