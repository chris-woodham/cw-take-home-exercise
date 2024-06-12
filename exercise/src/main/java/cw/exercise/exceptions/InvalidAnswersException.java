package cw.exercise.exceptions;

public class InvalidAnswersException extends RuntimeException {

    public InvalidAnswersException(String error) {
        super(error);
    }

}
