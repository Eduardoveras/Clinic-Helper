package com.clinichelper.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
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
    private Set<Surgery> surgeryLog;
    @OneToMany
    private Set<Consultation> consultationLog;
    @OneToMany
    private Set<History> historyLog;

    // Constructors
    public Record(){

    }

    public Record(Patient patient) {
        this.setRecordId(patient.getPatientId() + "-R-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setPatient(patient);
        this.setSurgeryLog(new HashSet<>());
        this.setConsultationLog(new HashSet<>());
        this.setHistoryLog(new HashSet<>());
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

    public Set<Surgery> getSurgeryLog() {
        return surgeryLog;
    }

    public void setSurgeryLog(Set<Surgery> surgeries) {
        this.surgeryLog = surgeries;
    }

    public Set<Consultation> getConsultationLog() {
        return consultationLog;
    }

    public void setConsultationLog(Set<Consultation> consultations) {
        this.consultationLog = consultations;
    }

    public Set<History> getHistoryLog() {
        return historyLog;
    }

    public void setHistoryLog(Set<History> history) {
        this.historyLog = history;
    }
}
