package com.clinichelper.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="records")
public class Record implements Serializable{
    @Id
    private String jascId;
    @OneToOne
    @NotNull
    private Patient patient;
    private String recordDetails; // TODO: Turn this into an historial
    @OneToMany
    private Set<Surgery> surgeries;
    @OneToMany
    private Set<Consultation> consultations;


    public Record(){

    }

    public Record(Patient patient, String recordDetails, Set<Surgery> surgeries, Set<Consultation> consultations) {
        this.setJascId("JASC-R-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setPatient(patient);
        this.setRecordDetails(recordDetails);
        this.setSurgeries(surgeries);
        this.setConsultations(consultations);
    }

    public String getJascId() {
        return jascId;
    }

    public void setJascId(String jascId) {
        this.jascId = jascId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getRecordDetails() {
        return recordDetails;
    }

    public void setRecordDetails(String recordDetails) {
        this.recordDetails = recordDetails;
    }

    public Set<Surgery> getSurgeries() {
        return surgeries;
    }

    public void setSurgeries(Set<Surgery> surgeries) { this.surgeries = surgeries; }

    public Set<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(Set<Consultation> consultations) {
        this.consultations = consultations;
    }
}
