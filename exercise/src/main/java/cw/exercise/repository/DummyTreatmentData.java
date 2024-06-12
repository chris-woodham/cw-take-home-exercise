package cw.exercise.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cw.exercise.model.Condition;
import cw.exercise.model.Questions;
import cw.exercise.model.Treatment;
import cw.exercise.model.TreatmentRisk;

@Configuration
public class DummyTreatmentData {

    private List<Treatment> treatments = new ArrayList<Treatment>();

    @Bean
    CommandLineRunner initialiseTreatmentData() {
        return args -> {
            Condition genoviaPearAllergy = new Condition("scientific name of allergy", "Genovia pear allergy", "allergy to Genovia pear (latin name) ...", "Skin rash, sneezing, itchy eyes", null);
            Questions questions1 = new Questions(List.of("Treatment 1 - this is question 1", "Treatment 1 - this is question 2", "Treatment 1 - this is question 3", "Treatment 1 - this is question 4", "Treatment 1 - this is question 5"));
            Questions questions2 = new Questions(List.of("Treatment 2 - this is question 1", "Treatment 2 - this is question 2", "Treatment 2 - this is question 3", "Treatment 2 - this is question 4", "Treatment 2 - this is question 5"));
            Questions questions3 = new Questions(List.of("Treatment 3 - this is question 1", "Treatment 3 - this is question 2", "Treatment 3 - this is question 3", "Treatment 3 - this is question 4", "Treatment 3 - this is question 5"));
            Treatment treatment1 = new Treatment("treatment1_scientific", "treatment1_common", "description in here ...", 100, questions1, new ArrayList<Treatment>(), List.of(genoviaPearAllergy), TreatmentRisk.Low);
            Treatment treatment2 = new Treatment("treatment2_scientific", "treatment2_common", "description in here ...", 400, questions2, new ArrayList<Treatment>(), List.of(genoviaPearAllergy), TreatmentRisk.Medium);
            Treatment treatment3 = new Treatment("treatment3_scientific", "treatment3_common", "description in here ...", 700, questions3, new ArrayList<Treatment>(), List.of(genoviaPearAllergy), TreatmentRisk.High);
            treatments.add(treatment1);
            treatments.add(treatment2);
            treatments.add(treatment3);
        };
    }

    public List<Treatment> getAllTreatments() {
        return this.treatments;
    }

    public Treatment addNewTreatment(Treatment newTreatment) {
        this.treatments.add(newTreatment);
        return newTreatment;
    }
}
