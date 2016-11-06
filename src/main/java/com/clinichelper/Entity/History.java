package com.clinichelper.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * Created by eva_c on 11/6/2016.
 */
@Entity
@Table(name="history")
public class History implements Serializable {

    @Id
    private String historyId;
    @ManyToOne
    @NotNull
    private Patient patient;
    @NotNull
    private String visitObjective;
    @NotNull
    private String observations;
    private String specialConditions;
    private String surgeryType;

    private Set<byte[]> photos;
    private Set<String> medicalData;



    public History(){

    }

    public History(Patient patient, String visitObjective, String observations, String specialConditions, Set<byte[]> photos, String surgeryType, Set<String> medicalData) {
        this.setHistoryId(patient.getPatientId() + "-H-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setPatient(patient);
        this.setVisitObjective(visitObjective);
        this.setObservations(observations);
        this.setSpecialConditions(specialConditions);
        this.setPhotos(photos);
        this.setSurgeryType(surgeryType);
        this.setMedicalData(medicalData);
    }

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getVisitObjective() {
        return visitObjective;
    }

    public void setVisitObjective(String visitObjective) {
        this.visitObjective = visitObjective;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getSpecialConditions() {
        return specialConditions;
    }

    public void setSpecialConditions(String specialConditions) {
        this.specialConditions = specialConditions;
    }

    public Set<byte[]> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<byte[]> photos) {
        this.photos = photos;
    }

    public String getSurgeryType() {
        return surgeryType;
    }

    public void setSurgeryType(String surgeryType) {
        this.surgeryType = surgeryType;
    }

    public Set<String> getMedicalData() {
        return medicalData;
    }

    public void setMedicalData(Set<String> medicalData) {
        this.medicalData = medicalData;
    }
}
