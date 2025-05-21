package com.inditex.core.config.exception;

import com.inditex.core.domain.exception.PriceNotFoundException;
import com.inditex.core.model.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    void handlePriceNotFound_givenValidException_shouldReturn404WithMessage() {
        String message = "Price not found for product 35455";
        PriceNotFoundException ex = new PriceNotFoundException(message);

        ResponseEntity<ErrorResponse> response = exceptionHandler.handlePriceNotFound(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getError()).isEqualTo(HttpStatus.NOT_FOUND.getReasonPhrase());
        assertThat(response.getBody().getMessage()).isEqualTo(message);
        assertThat(response.getBody().getTimestamp()).isNotNull();
    }

    @Test
    void handleGeneric_givenUnexpectedException_shouldReturn500WithGenericMessage() {
        Exception ex = new RuntimeException("DB connection failed");

        ResponseEntity<ErrorResponse> response = exceptionHandler.handleGeneric(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getError()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        assertThat(response.getBody().getMessage()).isEqualTo("An unexpected error occurred, view logs in server");
        assertThat(response.getBody().getTimestamp()).isNotNull();
    }
}
