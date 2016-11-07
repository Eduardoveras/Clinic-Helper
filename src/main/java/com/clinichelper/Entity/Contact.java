package com.clinichelper.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="contact")
public class Contact implements Serializable{
    // Attributes
    @Id
    private String contactId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Date birthDate;
    @Column(unique = true)
    @NotNull
    private String email;
    @ManyToOne
    private Clinic clinic;

    // Constructors
    public Contact(){

    }

    public Contact(Clinic clinic, String firstName, String lastName, Date birthDate, String email) {
        this.setContactId(clinic.getClinicPrefix() + "-STAFF-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setFirstName(firstName.toLowerCase());
        this.setLastName(lastName.toUpperCase());
        this.setBirthDate(birthDate);
        this.setEmail(email.toLowerCase());
        this.setClinic(clinic);
    }

    // Getters and Setters

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() { return firstName.toUpperCase() + " " + lastName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date staffBirthDate) {
        this.birthDate = staffBirthDate;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
}
