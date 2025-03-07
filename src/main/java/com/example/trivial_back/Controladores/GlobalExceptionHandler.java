package com.example.trivial_back.Controladores;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errores = new HashMap<>();

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String campoNombre = violation.getPropertyPath().toString();
            String errorMensaje = violation.getMessage();
            errores.put(campoNombre, errorMensaje);
        }
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>( "Ocurrió un error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}