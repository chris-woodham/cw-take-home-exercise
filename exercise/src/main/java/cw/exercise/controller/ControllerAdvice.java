package cw.exercise.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cw.exercise.exceptions.TreatmentNotFoundException;
import cw.exercise.exceptions.UnableToPrescribeTreatmentException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(TreatmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String treatmentNotFoundExceptionHandler(TreatmentNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(UnableToPrescribeTreatmentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String unableToPrescribeTreatmentExceptionHandler(UnableToPrescribeTreatmentException exception) {
        return "Apologies, there is currently an error in our system for this treatment meaning we cannot prescribe it. We will rectify this issue as quickly as we can";
    }

}
