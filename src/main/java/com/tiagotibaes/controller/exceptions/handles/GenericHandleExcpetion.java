package com.tiagotibaes.controller.exceptions.handles;

import com.tiagotibaes.controller.exceptions.errors.GenericErrors;
import com.tiagotibaes.exception.BusinessRuleException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericHandleExcpetion {
    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericErrors handleBusinessRuleException(BusinessRuleException ex){
       String messageError = ex.getMessage();
       return new GenericErrors(messageError);
    }
}
