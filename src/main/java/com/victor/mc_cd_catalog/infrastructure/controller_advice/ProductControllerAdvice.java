package com.victor.mc_cd_catalog.infrastructure.controller_advice;

import com.victor.mc_cd_catalog.infrastructure.model.RestError;
import com.victor.mc_cd_catalog.product.domain.use_cases.exceptions.ErrorType;
import com.victor.mc_cd_catalog.product.domain.use_cases.exceptions.NonExistingProductException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ProductControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NonExistingProductException.class)
    public RestError nonExistingProductException() {
        return new RestError(ErrorType.NON_EXISTING_PRODUCT, HttpStatus.NOT_FOUND.value(),
                ErrorType.NON_EXISTING_PRODUCT.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
