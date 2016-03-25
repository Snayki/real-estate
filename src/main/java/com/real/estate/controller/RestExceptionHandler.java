package com.real.estate.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.io.IOException;

/**
 * Created by Snayki on 23.03.2016.
 */
@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    public String validationExceptionHandler(Throwable throwable, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return throwable.getMessage();
    }
}
