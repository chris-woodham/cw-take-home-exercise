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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;



@RestController
public class TreatmentController {

    private TreatmentRepository treatmentRepository;

    public TreatmentController(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @Operation(summary = "Get the consulation questions for a specific treatment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consultation questions for this specific treatment",
                     content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Questions.class)) }),
        @ApiResponse(responseCode = "404", description = "No treatment with this specific name was found", content = @Content)
        }
    )
    @GetMapping("/treatments/{treatmentName}/questions")
    public ResponseEntity<?> getQuestionsForTreatment(@Parameter(description = "scientific name of treatment for consulation questions") @PathVariable String treatmentName) {
        Questions questions = treatmentRepository.findQuestionsForTreatment(treatmentName).orElseThrow(
                                    () -> new TreatmentNotFoundException("Could not find treatment with name: " + treatmentName));
        return new ResponseEntity<Questions>(questions, HttpStatus.OK);  
    }

    @Operation(summary = "Provide answers to the consulation questions for a specific treatment - the response will contain an intiial prescription decision")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Initial prescription decision (YES, MAYBE or NO)",
                     content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AssessmentOutcome.class)) }),
        @ApiResponse(responseCode = "404", description = "No treatment with this specific name was found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal error when assessing suitability of this treatment", content = @Content),
        @ApiResponse(responseCode = "422", description = "Answers provided in incorrect format, unable to provide initial prescription decision", content = @Content)
        }
    )
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
