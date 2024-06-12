package cw.exercise.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cw.exercise.exceptions.TreatmentNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(TreatmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String treatmentNotFoundExceptionHandler(TreatmentNotFoundException exception) {
        return exception.getMessage();
    }

}
