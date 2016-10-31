/**
 * Created by Djidjelly Siclait on 10/23/2016.
 */
package com.clinichelper.Entity;

import com.clinichelper.Tools.Task;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Chore implements Serializable{
    // Attributes
    @Id
    private String choreId;
    @NotNull
    private String title;
    @NotNull
    private Task type;
    @NotNull
    @Column(length = 500)
    private String description;
    @ManyToOne
    private Clinic clinic;
    @Transient
    private boolean completed;

    // Constructors
    public Chore(){

    }

    public Chore(String title, Task type, String description){
        this.setChoreId(clinic.getClinicPrefix() + "-TASK-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setTitle(title);
        this.setType(type);
        this.setDescription(description);
        this.setCompleted(false);
    }

    // only used for custom task
    public Chore(Clinic clinic, String title, Task type, String description){
        this.setChoreId(clinic.getClinicPrefix() + "-TASK-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setTitle(title);
        this.setType(type);
        this.setDescription(description);
        this.setCompleted(false);
        this.setClinic(clinic);
    }

    // Getters and Setters
    public String getChoreId() {
        return choreId;
    }

    public void setChoreId(String choreId) {
        this.choreId = choreId;
    }

    public Task getType() {
        return type;
    }

    public  String getTypeString() {
        switch (type){
            case PATIENT_BIRTHDAY:
                return "Patient's Birthday";
            case STAFF_BIRTHDAY:
                return "Staff's Birthday";
            case REGISTRATIONDATE:
                return "Registration Birthday";
            case MEETING:
                return "Meeting";
            case SURGERY:
                return "Surgery";
            default:
                return "Reminder";
        }
    }

    @Transient
    public  String getColorHtml() {
        switch (type){
            case PATIENT_BIRTHDAY:
                return "AntiqueWhite";
            case STAFF_BIRTHDAY:
                return "Khaki";
            case REGISTRATIONDATE:
                return "DarkSeaGreen";
            case MEETING:
                return "LightCoral";
            case SURGERY:
                return "Aqua ";
            default:
                return "LightSkyBlue";
        }
    }



    public void setType(Task type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
}
