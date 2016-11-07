package com.clinichelper.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="records")
public class Record implements Serializable{
    // Attributes
    @Id
    private String recordId;
    @OneToOne
    private Patient patient;
    @OneToMany
    private Set<Surgery> surgeries;
    @OneToMany
    private Set<Consultation> consultations;
    @OneToMany
    private Set<History> history;

    // Constructors
    public Record(){

    }

    public Record(Patient patient, Set<Surgery> surgeries, Set<Consultation> consultations, Set<History>history) {
        this.setRecordId(patient.getPatientId() + "-R-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setPatient(patient);
        this.setSurgeries(surgeries);
        this.setConsultations(consultations);
        this.setHistory(history);
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Set<Surgery> getSurgeries() {
        return surgeries;
    }

    public void setSurgeries(Set<Surgery> surgeries) {
        this.surgeries = surgeries;
    }

    public Set<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(Set<Consultation> consultations) {
        this.consultations = consultations;
    }

    public Set<History> getHistory() {
        return history;
    }

    public void setHistory(Set<History> history) {
        this.history = history;
    }
}
