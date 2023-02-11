package com.ignacioillanes.CurrencyExchangejava.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ignacioillanes.CurrencyExchangejava.Dto.ResponseDto;

@ControllerAdvice
public class ConvertionExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDto> handleException(Exception e) {
        ResponseDto response = new ResponseDto();
        response.setSuccess(false);
        response.setMessage(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<ResponseDto> handleException(ConvertionException e) {
        ResponseDto response = new ResponseDto();
        response.setSuccess(false);
        response.setMessage(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
