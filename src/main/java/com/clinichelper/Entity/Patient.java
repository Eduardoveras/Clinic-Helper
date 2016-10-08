package com.clinichelper.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="patients")
public class Patient implements Serializable{
    @Id
    private String jascId;
    private String patientName; //
    private String patientLastName;//
    @Column(unique = true)
    private String patientIdCard;//
    private String patientTelephoneNumber;//
    private String patientEmail;//
    private Date patientBirthDate;//
    private String patientGender;//
    private Date patientRegisteredDate;
    private String patientNationality;
    private String patientAddress;
    private String patientCity;
    private String patientCountry;
    private String patientContactTelephoneNumber;//

    public Patient(){

    }

    // Used only for registration waiting list
    public Patient(String patientName, String patientLastName, String patientTelephoneNumber, String patientEmail){
        this.setJascId(UUID.randomUUID().toString().split("-")[0]);
        this.setPatientName(patientName);
        this.setPatientLastName(patientLastName);
        this.setPatientTelephoneNumber(patientTelephoneNumber);
        this.setPatientEmail(patientEmail);
    }

    // Used to create and register new patients
    public Patient(String patientName, String patientLastName, String patientIdCard, String patientTelephoneNumber, String patientContactTelephoneNumber, String patientGender, String patientEmail, Date patientBirthDate, String patientNationality, String patientAddress, String patientCity, String patientCountry) {
        this.setJascId("JASC-P-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setPatientName(patientName);
        this.setPatientLastName(patientLastName);
        this.setPatientIdCard(patientIdCard);
        this.setPatientTelephoneNumber(patientTelephoneNumber);
        this.setPatientContactTelephoneNumber(patientContactTelephoneNumber);
        this.setPatientEmail(patientEmail);
        this.setPatientBirthDate(patientBirthDate);
        this.setPatientGender(patientGender);
        this.setPatientRegisteredDate(new Date(Calendar.getInstance().getTime().getTime()));
        this.setPatientNationality(patientNationality);
        this.setPatientAddress(patientAddress);
        this.setPatientCity(patientCity);
        this.setPatientCountry(patientCountry);
    }

    public String getJascId() {
        return jascId;
    }

    public void setJascId(String jascId) {
        this.jascId = jascId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientIdCard() {
        return patientIdCard;
    }

    public void setPatientIdCard(String patientIdCard) {
        this.patientIdCard = patientIdCard;
    }

    public String getPatientTelephoneNumber() {
        return patientTelephoneNumber;
    }

    public void setPatientTelephoneNumber(String patientTelephoneNumber) { this.patientTelephoneNumber = patientTelephoneNumber; }

    public String getPatientContactTelephoneNumber() {
        return patientContactTelephoneNumber;
    }

    public void setPatientContactTelephoneNumber(String patientContactTelephoneNumber) { this.patientContactTelephoneNumber = patientContactTelephoneNumber; }

    public Date getPatientBirthDate() {
        return patientBirthDate;
    }

    public void setPatientBirthDate(Date patientBirthDate) {
        this.patientBirthDate = patientBirthDate;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientNationality() {
        return patientNationality;
    }

    public void setPatientNationality(String patientNationality) {
        this.patientNationality = patientNationality;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPatientCity() {
        return patientCity;
    }

    public void setPatientCity(String patientCity) {
        this.patientCity = patientCity;
    }

    public String getPatientCountry() {
        return patientCountry;
    }

    public void setPatientCountry(String patientCountry) {
        this.patientCountry = patientCountry;
    }

    public Date getPatientRegisteredDate() {
        return patientRegisteredDate;
    }

    public void setPatientRegisteredDate(Date patientRegisteredDate) { this.patientRegisteredDate = patientRegisteredDate; }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }
}