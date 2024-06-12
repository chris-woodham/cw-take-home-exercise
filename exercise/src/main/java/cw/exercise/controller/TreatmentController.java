package cw.exercise.controller;

import org.springframework.web.bind.annotation.RestController;

import cw.exercise.exceptions.InvalidTreatmentRiskException;
import cw.exercise.exceptions.TreatmentNotFoundException;
import cw.exercise.exceptions.UnableToPrescribeTreatmentException;
import cw.exercise.model.AnswerAssessor;
import cw.exercise.model.Answers;
import cw.exercise.model.AssessmentOutcome;
import cw.exercise.model.Questions;
import cw.exercise.model.Treatment;
import cw.exercise.repository.TreatmentRepository;
import io.swagger.v3.oas.annotations.Parameter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class TreatmentController {

    private TreatmentRepository treatmentRepository;

    public TreatmentController(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @GetMapping("/treatments/{treatmentName}/questions")
    public ResponseEntity<?> getQuestionsForTreatment(@Parameter(description = "scientific name of treatment for consulation questions") @PathVariable String treatmentName) {
        Questions questions = treatmentRepository.findQuestionsForTreatment(treatmentName).orElseThrow(
                                    () -> new TreatmentNotFoundException("Could not find treatment with name: " + treatmentName));
        return new ResponseEntity<Questions>(questions, HttpStatus.OK);  
    }

    @PostMapping("/treatments/{treatmentName}/questions")
    public ResponseEntity<?> postAnswersForTreatmentQuestions(@Parameter(description = "List of \"YES\" or \"NO\" answers for this treatment's questions") @RequestBody Answers answers, 
                                                              @PathVariable String treatmentName) {
        Treatment treatment = treatmentRepository.findTreatmentByName(treatmentName).orElseThrow(
                                    () -> new TreatmentNotFoundException("Could not find treatment with name: " + treatmentName));
        try {
            AssessmentOutcome outcome = AnswerAssessor.assess(treatment, answers);
            return new ResponseEntity<AssessmentOutcome>(outcome, HttpStatus.OK);
        } catch (InvalidTreatmentRiskException exception) {
            throw new UnableToPrescribeTreatmentException();
        }
    }
    
}
