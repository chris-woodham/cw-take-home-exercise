package cw.exercise.model;

import java.util.List;

public class Treatment {

    private String scientificName;
    private String commonName;
    private String description;
    private int dailyDoseMg;
    private Questions consultationQuestions;
    private List<Treatment> relatedTreatments;
    private List<Condition> conditionsTreated;

    public Treatment() {}

    public Treatment(String scientificName, String commonName, String description, int dailyDoseMg,
            Questions consultationQuestions, List<Treatment> relatedTreatments, List<Condition> conditionsTreated) {
        this.scientificName = scientificName;
        this.commonName = commonName;
        this.description = description;
        this.dailyDoseMg = dailyDoseMg;
        this.consultationQuestions = consultationQuestions;
        this.relatedTreatments = relatedTreatments;
        this.conditionsTreated = conditionsTreated;
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

    public int getDailyDoseMg() {
        return dailyDoseMg;
    }

    public Questions getConsultationQuestions() {
        return consultationQuestions;
    }

    public List<Treatment> getRelatedTreatments() {
        return relatedTreatments;
    }

    public void setRelatedTreatments(List<Treatment> relatedTreatments) {
        this.relatedTreatments = relatedTreatments;
    }

    public List<Condition> getConditionsTreated() {
        return conditionsTreated;
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
        Treatment other = (Treatment) obj;
        if (scientificName == null) {
            if (other.scientificName != null)
                return false;
        } else if (!scientificName.equals(other.scientificName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Treatment [scientificName=" + scientificName + ", commonName=" + commonName + ", description="
                + description + ", dailyDoseMg=" + dailyDoseMg + "]";
    }

}
