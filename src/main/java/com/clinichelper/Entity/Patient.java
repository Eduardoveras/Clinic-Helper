package com.clinichelper.Entity;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private String patientId;
    @NotNull
    private String patientFirstName; //
    @NotNull
    private String patientLastName;//
    @Column(unique = true, nullable = false)
    private String patientIdCard;//
    @Column(length = 5000000)
    private Byte[] patientPhoto;
    @NotNull
    private String patientTelephoneNumber;//
    private String patientContactTelephoneNumber;//
    @NotNull
    private String patientEmail;//
    @NotNull
    private Date patientBirthDate;//
    @NotNull
    private String patientGender;//
    private Date patientRegisteredDate;
    private String occupation;
    private String patientNationality;
    @NotNull
    private String patientAddress;
    @NotNull
    private String patientCity;
    @NotNull
    private String patientCountry;

    public Patient(){

    }

    // Used only for registration waiting list
    public Patient(String patientFirstName, String patientLastName, String patientTelephoneNumber, String patientEmail){
        this.setJascId(UUID.randomUUID().toString().split("-")[0]);
        this.setPatientFirstName(patientFirstName.toLowerCase());
        this.setPatientLastName(patientLastName.toUpperCase());
        this.setPatientTelephoneNumber(patientTelephoneNumber);
        this.setPatientEmail(patientEmail.toLowerCase());
    }

    // Used to create and register new patients
    public Patient(String patientFirstName, String patientLastName, String patientIdCard, String patientTelephoneNumber, String patientContactTelephoneNumber,String occupation, String patientGender, String patientEmail, Date patientBirthDate, String patientNationality, String patientAddress, String patientCity, String patientCountry) {
        this.setJascId("JASC-P-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setPatientFirstName(patientFirstName.toLowerCase());
        this.setPatientLastName(patientLastName.toUpperCase());
        this.setPatientIdCard(patientIdCard);
        this.setPatientTelephoneNumber(patientTelephoneNumber);
        this.setPatientContactTelephoneNumber(patientContactTelephoneNumber);
        this.setOccupation(occupation.toUpperCase());
        this.setPatientGender(patientGender.toUpperCase());
        this.setPatientEmail(patientEmail.toLowerCase());
        this.setPatientBirthDate(patientBirthDate);
        this.setPatientNationality(patientNationality.toUpperCase());
        this.setPatientAddress(patientAddress.toUpperCase());
        this.setPatientCity(patientCity.toUpperCase());
        this.setPatientCountry(patientCountry.toUpperCase());
        this.setPatientRegisteredDate(new Date(Calendar.getInstance().getTime().getTime())); // current date
    }

    public String getJascId() {
        return jascId;
    }

    public void setJascId(String jascId) {
        this.jascId = jascId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientFullName() { return patientFirstName.toUpperCase() + " " + patientLastName; }

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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Byte[] getPatientPhoto() {
        return patientPhoto;
    }

    public void setPatientPhoto(Byte[] patientPhoto) {
        this.patientPhoto = patientPhoto;
    }

    public String displayPatientPhoto(){
        if(this.patientPhoto == null)
            return null;

        byte[] imgBytesAsBase64 = Base64.encodeBase64(toPrimitives(this.patientPhoto));
        return new String(imgBytesAsBase64);
    }

    // Auxiliary Function
    private byte[] toPrimitives(Byte[] buffer) {

        byte[] bytes = new byte[buffer.length];
        for(int i = 0; i < buffer.length; i++){
            bytes[i] = buffer[i];
        }
        return bytes;
    }
}
