/**
 * Created by Djidjelly Siclait on 10/23/2016.
 */
package com.clinichelper.Entity;

import com.clinichelper.Tools.Task;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Chore implements Serializable{
    // Attributes
    @Id
    private String jascId;
    @NotNull
    private String title;
    @NotNull
    private Task type;
    @NotNull
    @Column(length = 500)
    private String description;
    @Transient
    private boolean completed;

    // Constructors
    public Chore(){

    }

    public Chore(String title, Task type, String description){
        this.setJascId("JASC-CH-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setTitle(title);
        this.setType(type);
        this.setDescription(description);
        this.setCompleted(false);
    }

    // Getters and Setters
    public String getJascId() {
        return jascId;
    }

    public void setJascId(String jascId) {
        this.jascId = jascId;
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
}
