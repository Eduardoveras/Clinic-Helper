package com.clinichelper.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.UUID;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="surgeries")
public class Surgery implements Serializable{
    // Attributes
    @Id
    private String surgeryId;
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
    private Set<Contact> contacts;
    @ManyToMany
    private Set<Equipment> equipments;
    @OneToOne
    @NotNull
    private Appointment appointment;

    // Constructors
    public Surgery(){

    }

    public Surgery(String surgeryName, String surgeryDescription, Patient patient, Date surgeryDate, Timestamp surgeryTime, String surgeryRoom, Set<Contact> contacts, Set<Equipment> equipments, Appointment appointment) {
        this.setSurgeryId(appointment.getClinic().getClinicPrefix() + "-S-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setSurgeryName(surgeryName);
        this.setSurgeryDescription(surgeryDescription);
        this.setPatient(patient);
        this.setSurgeryDate(surgeryDate);
        this.setSurgeryTime(surgeryTime);
        this.setSurgeryRoom(surgeryRoom);
        this.setContacts(contacts);
        this.setEquipments(equipments);
        this.setAppointment(appointment);
    }

    // Getters and Setters
    public String getSurgeryId() {
        return surgeryId;
    }

    public void setSurgeryId(String surgeryId) {
        this.surgeryId = surgeryId;
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

    public String getStringSurgeryTime(){
        Date date = new Date(surgeryTime.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        return sdf.format(date).substring(11,16);
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

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
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
