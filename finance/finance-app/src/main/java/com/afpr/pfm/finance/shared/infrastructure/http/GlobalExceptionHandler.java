package com.afpr.pfm.finance.shared.infrastructure.http;

import com.afpr.pfm.finance.shared.exception.ConflictException;
import com.afpr.pfm.finance.shared.exception.NotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Void> handleConflict(ConflictException ex) {
        log.error("Conflict error occurred", ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(NotValidException.class)
    public ResponseEntity<Void> handleNotValid(NotValidException ex) {
        log.error("Not valid error occurred", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
