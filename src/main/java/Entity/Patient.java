package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Calendar;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="PATIENTS")
public class Patient {
    @Id
    @GeneratedValue
    private String patientId;
    private String patientName;
    private String patientLastName;
    private String patientIdCard;
    private String patientTelephoneNumber;
    private Date patientBirthDate;
    private Date patientRegisteredDate;
    private String patientNationality;
    private String patientAddress;
    private String patientCity;
    private String patientCountry;

    public Patient(){

    }

    public Patient(String patientName, String patientLastName, String patientIdCard, String patientTelephoneNumber, Date patientBirthDate, String patientNationality, String patientAddress, String patientCity, String patientCountry) {
        this.setPatientName(patientName);
        this.setPatientLastName(patientLastName);
        this.setPatientIdCard(patientIdCard);
        this.setPatientTelephoneNumber(patientTelephoneNumber);
        this.setPatientBirthDate(patientBirthDate);
        this.setPatientRegisteredDate(new Date(Calendar.getInstance().getTime().getTime()));
        this.setPatientNationality(patientNationality);
        this.setPatientAddress(patientAddress);
        this.setPatientCity(patientCity);
        this.setPatientCountry(patientCountry);
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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

    public void setPatientTelephoneNumber(String patientTelephoneNumber) {
        this.patientTelephoneNumber = patientTelephoneNumber;
    }

    public Date getPatientBirthDate() {
        return patientBirthDate;
    }

    public void setPatientBirthDate(Date patientBirthDate) {
        this.patientBirthDate = patientBirthDate;
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
}
