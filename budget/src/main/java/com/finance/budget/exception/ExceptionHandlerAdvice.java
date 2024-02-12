package com.finance.budget.exception;

import com.finance.budget.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(writeBudgetException.class)
    public ErrorDto writeBudgetException(Exception e) {
        return new ErrorDto(e.getClass().getName(), e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(writeAccountBookException.class)
    public ErrorDto writeAccountBookException(Exception e) {
        return new ErrorDto(e.getClass().getName(), e.getMessage());
    }
}