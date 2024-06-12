package cw.exercise.model;

import java.util.List;

public class Questions {

    private List<String> questions;

    public Questions() {}

    public Questions(List<String> questions) {
        this.questions = questions;
    }

    public List<String> getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        return "Questions [questions=" + questions + "]";
    }

}
