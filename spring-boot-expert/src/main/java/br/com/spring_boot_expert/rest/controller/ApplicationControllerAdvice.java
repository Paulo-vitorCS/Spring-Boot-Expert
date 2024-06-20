package br.com.spring_boot_expert.rest.controller;

import br.com.spring_boot_expert.exceptions.BusinessRulesException;
import br.com.spring_boot_expert.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessRulesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBusinessRulesException(BusinessRulesException ex) {
        String errorMessage = ex.getMessage();
        return new ApiErrors(errorMessage);
    }

}
