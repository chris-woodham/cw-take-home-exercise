package cw.exercise.controller;

import org.springframework.web.bind.annotation.RestController;

import cw.exercise.exceptions.TreatmentNotFoundException;
import cw.exercise.model.Questions;
import cw.exercise.repository.TreatmentRepository;
import io.swagger.v3.oas.annotations.Parameter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TreatmentController {

    private TreatmentRepository treatmentRepository;

    public TreatmentController(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @GetMapping("/treatments/{treatmentName}/questions")
    public ResponseEntity<?> getMethodName(@Parameter(description = "scientific name of treatment for consulation questions") 
                                @PathVariable String treatmentName) {
        Questions questions = treatmentRepository.findQuestionsForTreatment(treatmentName).orElseThrow(
                                    () -> new TreatmentNotFoundException("Could not find treatment with name: " + treatmentName));
        return new ResponseEntity<Questions>(questions, HttpStatus.OK);  
    }
    

}
