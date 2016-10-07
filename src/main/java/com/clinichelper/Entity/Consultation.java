package com.clinichelper.Entity;

import com.clinichelper.Entity.Appointment;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="CONSULTS")
public class Consultation {
    @Id
    @GeneratedValue
    private String jascId;
    private Date consultationDate;
    private Timestamp consultationTime;
    private String consultationDetail;
    @OneToOne
    private Surgery surgery;
    @OneToOne
    private Appointment appointment;

    public Consultation(){

    }
    public Consultation(Date consultationDate, Timestamp consultationTime, String consultationDetail, Surgery surgery, Appointment appointment){
        this.setConsultationDate(consultationDate);
        this.setConsultationTime(consultationTime);
        this.setConsultationDetail(consultationDetail);
        this.setSurgery(surgery);
        this.setAppointment(appointment);

    }

    public String getJascId() {
        return jascId;
    }

    public void setJascId(String jascId) {
        this.jascId = jascId;
    }

    public Date getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(Date consultationDate) {
        this.consultationDate = consultationDate;
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

    public Surgery getSurgery() {
        return surgery;
    }

    public void setSurgery(Surgery surgery) {
        this.surgery = surgery;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
