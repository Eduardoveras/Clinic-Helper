package com.clinichelper.Entity;

import com.clinichelper.Tools.Enums.Gender;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
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
    private String patientWorkphone;
    private String patientCellphone;
    private String patientContactName;
    private String patientContactLastName;
    private String patientContactAddress;
    private String patientContactCellphone;
    @NotNull
    private String patientEmail;//
    @NotNull
    private Date patientBirthDate;//
    @NotNull
    private Gender patientGender;//
    private Date patientRegisteredDate;
    private String occupation;
    private String patientNationality;
    @NotNull
    private String patientAddress;
    @NotNull
    private String patientCity;
    private ArrayList<String> patientAllergies;
    @NotNull
    private String patientCountry;
    private String patientReligion;
    private String patientHeight;
    private String patientWeight;
    private String patientBloodType;
    private ArrayList<String> patientConditions;
    @ManyToOne
    private Clinic clinic;

    public Patient(){

    }

    // Used only for registration waiting list
    public Patient(Clinic clinic, String patientFirstName, String patientLastName, String patientTelephoneNumber, String patientEmail){
        this.setPatientId(UUID.randomUUID().toString().split("-")[0]);
        this.setPatientFirstName(patientFirstName.toLowerCase());
        this.setPatientLastName(patientLastName.toUpperCase());
        this.setPatientTelephoneNumber(patientTelephoneNumber);
        this.setPatientEmail(patientEmail.toLowerCase());
        this.setClinic(clinic);
    }

    // Used to create and register new patients
    public Patient(Clinic clinic, String patientFirstName, String patientLastName, String patientIdCard,
                   String patientTelephoneNumber, String patientWorkphone, String patientCellphone,
                   String patientContactName, String patientContactLastName, String patientContactAddress,
                   String patientContactCellphone, String patientContactTelephoneNumber, String occupation,
                   Gender patientGender, String patientEmail, Date patientBirthDate, String patientNationality,
                   String patientAddress, String patientCity, String patientCountry, ArrayList<String> patientAllergies,
                   String patientReligion, String patientHeight, String patientWeight, String patientBloodType,
                   ArrayList<String> patientConditions
                   ) {
        this.setPatientId(clinic.getClinicPrefix() + "-P-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setPatientFirstName(patientFirstName.toLowerCase());
        this.setPatientLastName(patientLastName.toUpperCase());
        this.setPatientIdCard(patientIdCard);
        this.setPatientTelephoneNumber(patientTelephoneNumber);
        this.setPatientContactTelephoneNumber(patientContactTelephoneNumber);
        this.setOccupation(occupation.toUpperCase());
        this.setPatientGender(patientGender);
        this.setPatientEmail(patientEmail.toLowerCase());
        this.setPatientBirthDate(patientBirthDate);
        this.setPatientNationality(patientNationality.toUpperCase());
        this.setPatientAddress(patientAddress.toUpperCase());
        this.setPatientCity(patientCity.toUpperCase());
        this.setPatientCountry(patientCountry.toUpperCase());
        this.setPatientAllergies(patientAllergies);
        this.setPatientReligion(patientReligion);
        this.setPatientHeight(patientHeight);
        this.setPatientWeight(patientWeight);
        this.setPatientBloodType(patientBloodType);
        this.setPatientConditions(patientConditions);
        this.setPatientWorkphone(patientWorkphone);
        this.setPatientCellphone(patientCellphone);
        this.setPatientContactName(patientContactName);
        this.setPatientContactLastName(patientContactLastName);
        this.setPatientContactAddress(patientContactAddress);
        this.setPatientContatCellphone(patientContactCellphone);
        this.setPatientRegisteredDate(new Date(Calendar.getInstance().getTime().getTime())); // current date
        this.setClinic(clinic);

    }

    // Getters and Setters
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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

    public Gender getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(Gender patientGender) {
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

    public ArrayList<String> getPatientAllergies() {
        return patientAllergies;
    }

    public void setPatientAllergies(ArrayList<String> patientAllergies) {
        this.patientAllergies = patientAllergies;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public String getPatientWorkphone() {
        return patientWorkphone;
    }

    public void setPatientWorkphone(String patientWorkphone) {
        this.patientWorkphone = patientWorkphone;
    }

    public String getPatientCellphone() {
        return patientCellphone;
    }

    public void setPatientCellphone(String patientCellphone) {
        this.patientCellphone = patientCellphone;
    }

    public String getPatientContactName() {
        return patientContactName;
    }

    public void setPatientContactName(String patientContactName) {
        this.patientContactName = patientContactName;
    }

    public String getPatientContactLastName() {
        return patientContactLastName;
    }

    public void setPatientContactLastName(String patientContactLastName) {
        this.patientContactLastName = patientContactLastName;
    }

    public String getPatientContactAddress() {
        return patientContactAddress;
    }

    public void setPatientContactAddress(String patientContactAddress) {
        this.patientContactAddress = patientContactAddress;
    }


    public String getPatientContatCellphone() {
        return patientContactCellphone;
    }

    public void setPatientContatCellphone(String patientContatCellphone) {
        this.patientContactCellphone = patientContatCellphone;
    }

    public String getPatientReligion() {
        return patientReligion;
    }

    public void setPatientReligion(String patientReligion) {
        this.patientReligion = patientReligion;
    }

    public String getPatientHeight() {
        return patientHeight;
    }

    public void setPatientHeight(String patientHeight) {
        this.patientHeight = patientHeight;
    }

    public String getPatientWeight() {
        return patientWeight;
    }

    public void setPatientWeight(String patientWeight) {
        this.patientWeight = patientWeight;
    }

    public String getPatientBloodType() {
        return patientBloodType;
    }

    public void setPatientBloodType(String patientBloodType) {
        this.patientBloodType = patientBloodType;
    }

    public ArrayList<String> getPatientConditions() {
        return patientConditions;
    }

    public void setPatientConditions(ArrayList<String> patientConditions) {
        this.patientConditions = patientConditions;
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
