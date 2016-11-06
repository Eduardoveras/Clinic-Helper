package com.clinichelper.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
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
    @OneToOne
    @NotNull
    private Patient patient;
    @NotNull
    private String visitObjective;
    @NotNull
    private String observations;

    private String specialConditions;

    private ArrayList<byte[]> photos;
    private String surgeryType;
    private ArrayList<String> medicalData;



    public History(){

    }

    public History(Patient patient, String visitObjective, String observations, String specialConditions, ArrayList<byte[]> photos, String surgeryType, ArrayList<String> medicalData) {
        this.setHistoryId(patient.getPatientId() + "-R-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.patient = patient;
        this.visitObjective = visitObjective;
        this.observations = observations;
        this.specialConditions = specialConditions;
        this.photos = photos;
        this.surgeryType = surgeryType;
        this.medicalData = medicalData;
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

    public ArrayList<byte[]> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<byte[]> photos) {
        this.photos = photos;
    }

    public String getSurgeryType() {
        return surgeryType;
    }

    public void setSurgeryType(String surgeryType) {
        this.surgeryType = surgeryType;
    }

    public ArrayList<String> getMedicalData() {
        return medicalData;
    }

    public void setMedicalData(ArrayList<String> medicalData) {
        this.medicalData = medicalData;
    }
}
