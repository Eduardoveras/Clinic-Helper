/**
 * Created by Djidjelly Siclait on 10/23/2016.
 */
package com.clinichelper.Entity;

import com.clinichelper.Tools.Enums.Repeat;
import com.clinichelper.Tools.Enums.Task;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
    private User user;
    private boolean completed;
    private Timestamp nextReminder;
    private ArrayList<Repeat> reminders;

    // Constructors
    public Chore(){

    }

    public Chore(User user, String title, Task type, String description, ArrayList<Repeat> reminders){
        this.setChoreId(user.getClinic().getClinicPrefix() + "-TASK-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setTitle(title);
        this.setType(type);
        this.setDescription(description);
        this.setCompleted(false);
        this.setUser(user);
        this.setReminders(reminders);
        this.setNextReminder();
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

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Timestamp getNextReminder() {
        return nextReminder;
    }

    public void setNextReminder() {
        this.nextReminder = setFirstNextReminder(this.reminders.get(0));
    }

    public ArrayList<Repeat> getReminders() {
        return reminders;
    }

    public void setReminders(ArrayList<Repeat> reminders) {
        this.reminders = reminders;
    }

    // Auxiliary Functions
    private Timestamp setFirstNextReminder(Repeat repeat){
        Calendar today = Calendar.getInstance();
        today.setTime(Calendar.getInstance().getTime());

        switch (repeat){
            case EVERY_DAY:
                today.add(Calendar.DATE, 1); // Tomorrow
                break;
            case MONTHLY:
                today.add(Calendar.MONTH, 1); // Next Month
                break;
            case MONDAY:
                do{
                    today.add(Calendar.DATE, 1);
                } while (today.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY);

                break;
            case TUESDAY:
                do{
                    today.add(Calendar.DATE, 1);
                } while (today.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY);

                break;
            case WEDNESDAY:
                do{
                    today.add(Calendar.DATE, 1);
                } while (today.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY);

                break;
            case THURSDAY:
                do{
                    today.add(Calendar.DATE, 1);
                } while (today.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY);

                break;
            case FRIDAY:
                do{
                    today.add(Calendar.DATE, 1);
                } while (today.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY);

                break;
            case SATURDAY:
                do{
                    today.add(Calendar.DATE, 1);
                } while (today.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY);

                break;
            case YEARLY: // every year
                today.add(Calendar.YEAR, 1); // Next Year
                break;
            default:
                return new Timestamp(Calendar.getInstance().getTime().getTime());
        }

        return new Timestamp(today.getTime().getTime());
    }
}
