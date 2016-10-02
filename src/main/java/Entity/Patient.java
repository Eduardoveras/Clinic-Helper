package Entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="PATIENTS")
public class Patient {
    @Id
    private String jascId;
    private String patientName;
    private String patientLastName;
    @Column(unique = true)
    private String patientIdCard;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    private String patientTelephoneNumber;
    private String patientEmail;
=======
    private Number patientTelephoneNumber;
    private Number patientContactTelephoneNumber;
>>>>>>> Stashed changes
=======
    private Number patientTelephoneNumber;
    private Number patientContactTelephoneNumber;
>>>>>>> Stashed changes
    private Date patientBirthDate;
    private String patientGender;
    private String patientMail;
    private Date patientRegisteredDate;
    private String patientNationality;
    private String patientAddress;
    private String patientCity;
    private String patientCountry;

    public Patient(){

    }

<<<<<<< Updated upstream
<<<<<<< Updated upstream
    // Used only for registration waiting list
    public Patient(String patientName, String patientLastName, String patientTelephoneNumber, String patientEmail){
        this.setJascId("JASC-P-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setPatientName(patientName);
        this.setPatientLastName(patientLastName);
        this.setPatientTelephoneNumber(patientTelephoneNumber);
        this.setPatientEmail(patientEmail);
    }

    // Used to create and register new patients
    public Patient(String patientName, String patientLastName, String patientIdCard, String patientTelephoneNumber, String patientEmail, Date patientBirthDate, String patientNationality, String patientAddress, String patientCity, String patientCountry) {
        this.setJascId("JASC-P-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
=======
    public Patient(String patientName, String patientLastName, String patientIdCard, Number patientTelephoneNumber, Number patientContactTelephoneNumber, Date patientBirthDate, String patientGender, String patientMail,String patientNationality, String patientAddress, String patientCity, String patientCountry) {
>>>>>>> Stashed changes
=======
    public Patient(String patientName, String patientLastName, String patientIdCard, Number patientTelephoneNumber, Number patientContactTelephoneNumber, Date patientBirthDate, String patientGender, String patientMail,String patientNationality, String patientAddress, String patientCity, String patientCountry) {
>>>>>>> Stashed changes
        this.setPatientName(patientName);
        this.setPatientLastName(patientLastName);
        this.setPatientIdCard(patientIdCard);
        this.setPatientTelephoneNumber(patientTelephoneNumber);
<<<<<<< Updated upstream
<<<<<<< Updated upstream
        this.setPatientEmail(patientEmail);
=======
        this.setPatientContactTelephoneNumber(patientContactTelephoneNumber);
>>>>>>> Stashed changes
=======
        this.setPatientContactTelephoneNumber(patientContactTelephoneNumber);
>>>>>>> Stashed changes
        this.setPatientBirthDate(patientBirthDate);
        this.setPatientGender(patientGender);
        this.setPatientMail(patientMail);
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

    public Number getPatientTelephoneNumber() {
        return patientTelephoneNumber;
    }

    public void setPatientTelephoneNumber(Number patientTelephoneNumber) {
        this.patientTelephoneNumber = patientTelephoneNumber;
    }

    public Number getPatientContactTelephoneNumber() {
        return patientContactTelephoneNumber;
    }

    public void setPatientContactTelephoneNumber(Number patientContactTelephoneNumber) {
        this.patientContactTelephoneNumber = patientContactTelephoneNumber;
    }

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

    public String getPatientMail() {
        return patientMail;
    }

    public void setPatientMail(String patientMail) {
        this.patientMail = patientMail;
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

    public void setPatientRegisteredDate(Date patientRegisteredDate) {
        this.patientRegisteredDate = patientRegisteredDate;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }
}
