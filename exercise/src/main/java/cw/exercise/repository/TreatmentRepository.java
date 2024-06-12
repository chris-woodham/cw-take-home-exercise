package cw.exercise.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import cw.exercise.model.Questions;
import cw.exercise.model.Treatment;

@Repository
public class TreatmentRepository {

    public Optional<Questions> findQuestionsForTreatment(String scientificName) {
        return Optional.empty();
    }

    public Optional<Treatment> findTreatmentByName(String treatmentName) {
        return Optional.empty();
    }

}
