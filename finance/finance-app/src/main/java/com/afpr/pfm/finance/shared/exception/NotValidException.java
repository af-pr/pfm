package com.afpr.pfm.finance.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotValidException extends RuntimeException {
    public NotValidException(String message) {
        super(message);
    }
}
