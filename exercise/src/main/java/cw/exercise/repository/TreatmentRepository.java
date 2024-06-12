package cw.exercise.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import cw.exercise.model.Questions;

@Repository
public class TreatmentRepository {

    public Optional<Questions> findQuestionsForTreatment(String scientificName) {
        return Optional.empty();
    }

}
