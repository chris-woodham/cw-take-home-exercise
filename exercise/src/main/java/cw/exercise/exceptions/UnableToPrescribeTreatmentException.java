package cw.exercise.exceptions;

public class UnableToPrescribeTreatmentException extends RuntimeException {

    public UnableToPrescribeTreatmentException() {
        super();
    }

    public UnableToPrescribeTreatmentException(String error) {
        super(error);
    }

}
