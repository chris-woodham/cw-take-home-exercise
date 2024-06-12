package cw.exercise.model;

import java.util.List;

public class Condition {

    private String scientificName;
    private String commonName;
    private String description;
    private String symptoms; // Note - in this future this may become: List<Symptom> symptoms
    private List<Treatment> potentialTreatments;

    public Condition() {}

    public Condition(String scientificName, String commonName, String description, String symptoms,
            List<Treatment> potentialTreatments) {
        this.scientificName = scientificName;
        this.commonName = commonName;
        this.description = description;
        this.symptoms = symptoms;
        this.potentialTreatments = potentialTreatments;
    }

    public String getScientificName() {
        return scientificName;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getDescription() {
        return description;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public List<Treatment> getPotentialTreatments() {
        return potentialTreatments;
    }

    public void setPotentialTreatments(List<Treatment> potentialTreatments) {
        this.potentialTreatments = potentialTreatments;
    }

    // In this model the "scientificName" of a Condition is its unique identifier
    // And therefore equals and hashCode are overridden using "this.scientificName"
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((scientificName == null) ? 0 : scientificName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Condition other = (Condition) obj;
        if (scientificName == null) {
            if (other.scientificName != null)
                return false;
        } else if (!scientificName.equals(other.scientificName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Condition [scientificName=" + scientificName + ", commonName=" + commonName + ", description="
                + description + ", symptoms=" + symptoms + "]";
    }

}
