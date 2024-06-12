package cw.exercise.exceptions;

public class TreatmentNotFoundException extends RuntimeException {

    public TreatmentNotFoundException(String error) {
        super(error);
    }

}
