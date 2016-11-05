/**
 * Created by Djidjelly Siclait on 11/5/2016.
 */
package com.clinichelper.Tools.Classes;

import java.sql.Timestamp;

public class CalendarEvent {
    // Attributes
    private String title;
    private Timestamp dateAndTime;
    private String description;
    private String status;

    // Constructors
    public CalendarEvent(){

    }

    public CalendarEvent(String title, Timestamp dateAndTime, String description, String status){
        this.setTitle(title);
        this.setDateAndTime(dateAndTime);
        this.setDescription(description);
        this.setStatus(status);
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Timestamp dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
