/**
 * Created by Djidjelly Siclait on 10/8/2016.
 */
package com.clinichelper.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "meetings")
public class Meeting implements Serializable{

    // Attributes
    @Id
    private String meetingId;
    @NotNull
    private String meetingTitle; //
    @NotNull
    private String meetingObjective;
    @NotNull
    private Timestamp meetingTime;
    @NotNull
    private String meetingPlace;//
    @ManyToMany
    @NotNull
    private Set<Contact> attendees;
    @ManyToOne
    private Clinic clinic;

    // Constructors
    public Meeting(){

    }

    public Meeting(Clinic clinic, String meetingTitle, String meetingObjective, Timestamp meetingTime, String meetingPlace, Set<Contact> attendees){
        this.setMeetingId(clinic.getClinicPrefix() + "-M-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setMeetingTitle(meetingTitle.toUpperCase());
        this.setMeetingObjective(meetingObjective.toUpperCase());
        this.setMeetingTime(meetingTime);
        this.setMeetingPlace(meetingPlace);
        this.setAttendees(attendees);
        this.setClinic(clinic);
    }

    // Getters and Setters
    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getMeetingTitle() {
        return meetingTitle;
    }

    public void setMeetingTitle(String meetingTitle) {
        this.meetingTitle = meetingTitle;
    }

    public String getMeetingObjective() {
        return meetingObjective;
    }

    public void setMeetingObjective(String meetingObjective) {
        this.meetingObjective = meetingObjective;
    }

    public Timestamp getMeetingTime() {
        return meetingTime;
    }

    public String getStringMeetingTime(){
        return meetingTime.toString();
    }

    public void setMeetingTime(Timestamp meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getMeetingPlace() {
        return meetingPlace;
    }

    public void setMeetingPlace(String meetingPlace) {
        this.meetingPlace = meetingPlace;
    }

    public Set<Contact> getAttendees() {
        return attendees;
    }

    public void setAttendees(Set<Contact> attendees) {
        this.attendees = attendees;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
}
