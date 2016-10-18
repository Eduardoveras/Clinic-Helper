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
    @Id
    private String jascId;
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

    public Appointment(){

    }

    public Appointment(Date appointmentDate, Patient patient, String appointmentDescription, String appointmentAccessFrom){
        this.setJascId("Request-" + UUID.randomUUID().toString().split("-")[0]);
        this.setAppointmentDate(appointmentDate);
        this.setPatient(patient);
        this.setAppointmentDescription(appointmentDescription);
        this.setAppointmentAccessFrom(appointmentAccessFrom);
    }

    public Appointment(Date appointmentDate, Timestamp appointmentTime, Patient patient, String appointmentDescription, String appointmentAccessFrom){
        this.setJascId("JASC-A-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setAppointmentDate(appointmentDate);
        this.setAppointmentTime(appointmentTime);
        this.setPatient(patient);
        this.setAppointmentDescription(appointmentDescription);
        this.setAppointmentAccessFrom(appointmentAccessFrom);
    }

    public String getJascId() {
        return jascId;
    }

    public void setJascId(String jascId) {
        this.jascId = jascId;
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
}
