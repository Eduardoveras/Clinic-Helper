package com.clinichelper.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="appointments")
public class Appointment implements Serializable{
    // Attributes
    @Id
    private String appointmentId;
    @NotNull
    private Date appointmentDate;
    @NotNull
    private Timestamp appointmentTime;
    @ManyToOne
    @NotNull
    private Patient patient;
    @Column(length = 500)
    private String appointmentDescription;
    private String appointmentAccessFrom;
    @ManyToOne
    private Clinic clinic;

    // Constructors
    public Appointment(){

    }

    public Appointment(Clinic clinic, Date appointmentDate, Patient patient, String appointmentDescription, String appointmentAccessFrom){
        this.setAppointmentId(clinic.getClinicPrefix() + "-SOLICIT-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setAppointmentDate(appointmentDate);
        this.setPatient(patient);
        this.setAppointmentDescription(appointmentDescription);
        this.setAppointmentAccessFrom(appointmentAccessFrom);
        this.setClinic(clinic);
    }

    public Appointment(Clinic clinic, Date appointmentDate, Timestamp appointmentTime, Patient patient, String appointmentDescription, String appointmentAccessFrom){
        this.setAppointmentId(clinic.getClinicPrefix() + "-A-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setAppointmentDate(appointmentDate);
        this.setAppointmentTime(appointmentTime);
        this.setPatient(patient);
        this.setAppointmentDescription(appointmentDescription);
        this.setAppointmentAccessFrom(appointmentAccessFrom);
        this.setClinic(clinic);
    }

    //Getters and Setters
    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Timestamp getAppointmentTime() {
        return appointmentTime;
    }

    public String getStringAppointmentTime(){
        Date date = new Date(appointmentTime.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        return sdf.format(date).substring(11,16);
    }

    public void setAppointmentTime(Timestamp appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getAppointmentAccessFrom() {
        return appointmentAccessFrom;
    }

    public void setAppointmentAccessFrom(String appointmentAccessFrom) {
        this.appointmentAccessFrom = appointmentAccessFrom;
    }

    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
}
