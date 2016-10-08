/**
 * Created by Djidjelly Siclait on 10/8/2016.
 */
package com.clinichelper.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "meetings")
public class Meeting implements Serializable{

    // Attributes
    @Id
    private String jascId;
    private String meetingTitle; //
    private String meetingObjective;
    private Date meetingDate;//
    private Timestamp meetingTime;
    private String meetingPlace;//
    @OneToMany
    private Set<Staff> attendees;

    // Constructores
    public Meeting(){

    }

    public Meeting(String meetingTitle, String meetingObjective, Date meetingDate, Timestamp meetingTime, String meetingPlace, Set<Staff> attendees){
        this.setJascId("JASC-M-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setMeetingTitle(meetingTitle);
        this.setMeetingObjective(meetingObjective);
        this.setMeetingDate(meetingDate);
        this.setMeetingTime(meetingTime);
        this.setMeetingPlace(meetingPlace);
        this.setAttendees(attendees);
    }

    public String getJascId() {
        return jascId;
    }

    public void setJascId(String jascId) {
        this.jascId = jascId;
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

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public Timestamp getMeetingTime() {
        return meetingTime;
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

    public Set<Staff> getAttendees() {
        return attendees;
    }

    public void setAttendees(Set<Staff> attendees) {
        this.attendees = attendees;
    }
}
