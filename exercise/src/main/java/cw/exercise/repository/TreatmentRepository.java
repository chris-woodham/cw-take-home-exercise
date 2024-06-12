package cw.exercise.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Repository;

import cw.exercise.model.Questions;
import cw.exercise.model.Treatment;

@Repository
public class TreatmentRepository {

    private DummyTreatmentData dummyTreatmentData;

    public TreatmentRepository(DummyTreatmentData dummyTreatmentData) {
        this.dummyTreatmentData = dummyTreatmentData;
    }

    public Optional<Questions> findQuestionsForTreatment(String treatmentName) {
        List<Treatment> treatments = dummyTreatmentData.getAllTreatments();
        for (Treatment treatment : treatments) {
            if (treatment.getScientificName().equals(treatmentName)) {
                return Optional.of(treatment.getConsultationQuestions());
            }
        }
        return Optional.empty();
    }

    public Optional<Treatment> findTreatmentByName(String treatmentName) {
        List<Treatment> treatments = dummyTreatmentData.getAllTreatments();
        for (Treatment treatment : treatments) {
            if (treatment.getScientificName().equals(treatmentName)) {
                return Optional.of(treatment);
            }
        }
        return Optional.empty();
    }

}
