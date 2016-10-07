package com.clinichelper.Entity;

import com.clinichelper.Entity.Consultation;
import com.clinichelper.Entity.Patient;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="RECORDS")
public class Record {
    @Id
    @GeneratedValue
    private String recordId;
    @OneToOne
    private Patient patient;
    private String recordDetails;
    @OneToMany
    private Set<Surgery> surgeries;
    @OneToMany
    private Set<Consultation> consultations;


    public Record(){

    }

    public Record(Patient patient, String recordDetails, Set<Surgery> surgeries, Set<Consultation> consultations) {
        this.setPatient(patient);
        this.setRecordDetails(recordDetails);
        this.setSurgeries(surgeries);
        this.setConsultations(consultations);
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

    public String getRecordDetails() {
        return recordDetails;
    }

    public void setRecordDetails(String recordDetails) {
        this.recordDetails = recordDetails;
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
}
