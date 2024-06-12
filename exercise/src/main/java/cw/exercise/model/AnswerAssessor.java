package cw.exercise.model;

import cw.exercise.exceptions.InvalidAnswersException;
import cw.exercise.exceptions.InvalidTreatmentRiskException;

public class AnswerAssessor {

    private static int LOW_RISK_NO_THRESHOLD = 4;
    private static int LOW_RISK_MAYBE_THRESHOLD = 3;
    private static int MEDIUM_RISK_NO_THRESHOLD = 2;
    private static int MEDIUM_RISK_MAYBE_THRESHOLD = 1;
    private static int HIGH_RISK_NO_THRESHOLD = 0;
    private static int HIGH_RISK_MAYBE_THRESHOLD = 0;

    public static AssessmentOutcome assess(Treatment treatment, Answers answers) throws InvalidTreatmentRiskException {

        if (answers.getAnswers().size() != treatment.getConsultationQuestions().getQuestions().size()) {
            throw new InvalidAnswersException("Error, must provide one answer for each individual question");
        }
        for (String answer : answers.getAnswers()) {
            if (!(answer.toUpperCase().equals("NO") || answer.toUpperCase().equals("YES"))) {
                throw new InvalidAnswersException("Error, all answers must be either \"YES\" or \"NO\"");
            } 
        }
        
        int numberNoAnswers = getNumberNoAnswers(answers);
        TreatmentRisk treatmentRisk = treatment.getTreatmentRisk();

        switch (treatmentRisk) {
            case Low:
                return getAssessmentOutcomeForRisk(treatmentRisk, numberNoAnswers, LOW_RISK_NO_THRESHOLD, LOW_RISK_MAYBE_THRESHOLD);
            case Medium:
                return getAssessmentOutcomeForRisk(treatmentRisk, numberNoAnswers, MEDIUM_RISK_NO_THRESHOLD, MEDIUM_RISK_MAYBE_THRESHOLD);
            case High:
                return getAssessmentOutcomeForRisk(treatmentRisk, numberNoAnswers, HIGH_RISK_NO_THRESHOLD, HIGH_RISK_MAYBE_THRESHOLD);
            default:
                // Log error - as the return from treatment.getTreatmentRisk() should always be one of these values (and should never be null)
                throw new InvalidTreatmentRiskException("Treatment risk of: " + treatmentRisk + " is invalid. Unable to assess answers for this treatment.");
        }

    }

    public static int getNumberNoAnswers(Answers answers) {
        return (int) answers.getAnswers().stream().map(string -> string.toUpperCase()).filter(string -> string.equals("NO")).count();
    }

    public static AssessmentOutcome getAssessmentOutcomeForRisk(TreatmentRisk risk, int numberNoAnswers, int noThreshold, int maybeThreshold) {
        if (numberNoAnswers >= noThreshold) {
            return AssessmentOutcome.NO;
        } else if (numberNoAnswers >= maybeThreshold) {
            return AssessmentOutcome.MAYBE;
        } else {
            return AssessmentOutcome.YES;
        } 
    }

}
