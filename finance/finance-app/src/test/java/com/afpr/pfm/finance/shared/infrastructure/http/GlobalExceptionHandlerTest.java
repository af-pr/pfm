package com.afpr.pfm.finance.shared.infrastructure.http;

import com.afpr.pfm.finance.shared.exception.ConflictException;
import com.afpr.pfm.finance.shared.exception.NotValidException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler underTest;

    @Test
    void handleConflict_returns409WithNoBody() {
        ResponseEntity<Void> result = underTest.handleConflict(new ConflictException("already exists"));

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(result.getBody()).isNull();
    }

    @Test
    void handleNotValid_returns404WithNoBody() {
        ResponseEntity<Void> result = underTest.handleNotValid(new NotValidException("not valid"));

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody()).isNull();
    }
}
