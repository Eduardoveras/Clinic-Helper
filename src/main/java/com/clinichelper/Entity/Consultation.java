package com.clinichelper.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="consultation")
public class Consultation implements Serializable{
    // Attributes
    @Id
    private String consultationId;
    @NotNull
    private Timestamp consultationTime;
    private String consultationDetail;
    @OneToOne
    @NotNull
    private Appointment appointment;

    // Constructors
    public Consultation(){

    }

    public Consultation(Timestamp consultationTime, String consultationDetail, Appointment appointment){
        this.setConsultationId(appointment.getClinic().getClinicPrefix() + "-C-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setConsultationTime(consultationTime);
        this.setConsultationDetail(consultationDetail);
        this.setAppointment(appointment);

    }

    //Getters and Setters
    public String getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(String consultationId) {
        this.consultationId = consultationId;
    }

    public Timestamp getConsultationTime() {
        return consultationTime;
    }

    public void setConsultationTime(Timestamp consultationTime) {
        this.consultationTime = consultationTime;
    }

    public String getConsultationDetail() {
        return consultationDetail;
    }

    public void setConsultationDetail(String consultationDetail) {
        this.consultationDetail = consultationDetail;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
