package cw.exercise.model;

import java.util.List;

public class Answers {

    private List<String> answers;

    public Answers() {}

    public Answers(List<String> answers) {
        this.answers = answers;
    }

    public List<String> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Answers [answers=" + answers + "]";
    }

}
