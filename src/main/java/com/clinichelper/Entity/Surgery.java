package com.clinichelper.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="surgeries")
public class Surgery implements Serializable{
    @Id
    private String jascId;
    @NotNull
    private String surgeryName;
    private String surgeryDescription;
    @ManyToOne
    @NotNull
    private Patient patient;
    @NotNull
    private Date surgeryDate;
    @NotNull
    private Timestamp surgeryTime;
    @NotNull
    private String surgeryRoom;
    @ManyToMany
    @NotNull
    private Set<Staff> staffs;
    @OneToMany
    private Set<Equipment> equipments;
    @OneToOne
    @NotNull
    private Appointment appointment;

    public Surgery(){

    }

    public Surgery(String surgeryName, String surgeryDescription, Patient patient, Date surgeryDate, Timestamp surgeryTime, String surgeryRoom, Set<Staff> staffs, Set<Equipment> equipments, Appointment appointment) {
        this.setJascId("JASC-S-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setSurgeryName(surgeryName);
        this.setSurgeryDescription(surgeryDescription);
        this.setPatient(patient);
        this.setSurgeryDate(surgeryDate);
        this.setSurgeryTime(surgeryTime);
        this.setSurgeryRoom(surgeryRoom);
        this.setStaffs(staffs);
        this.setEquipments(equipments);
        this.setAppointment(appointment);
    }

    public String getJascId() {
        return jascId;
    }

    public void setJascId(String jascId) {
        this.jascId = jascId;
    }

    public String getSurgeryName() {
        return surgeryName;
    }

    public void setSurgeryName(String surgeryName) {
        this.surgeryName = surgeryName;
    }

    public String getSurgeryDescription() {
        return surgeryDescription;
    }

    public void setSurgeryDescription(String surgeryDescription) {
        this.surgeryDescription = surgeryDescription;
    }

    public Date getSurgeryDate() {
        return surgeryDate;
    }

    public void setSurgeryDate(Date surgeryDate) {
        this.surgeryDate = surgeryDate;
    }

    public Timestamp getSurgeryTime() {
        return surgeryTime;
    }

    public void setSurgeryTime(Timestamp surgeryTime) {
        this.surgeryTime = surgeryTime;
    }

    public String getSurgeryRoom() {
        return surgeryRoom;
    }

    public void setSurgeryRoom(String surgeryRoom) {
        this.surgeryRoom = surgeryRoom;
    }

    public Set<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(Set<Staff> staffs) {
        this.staffs = staffs;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Set<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(Set<Equipment> equipments) {
        this.equipments = equipments;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
