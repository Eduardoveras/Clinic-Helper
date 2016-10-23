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
    private Task type;
    @NotNull
    @Column(length = 500)
    private String description;
    @Transient
    private boolean completed;

    // Constructors
    public Chore(){

    }

    public Chore(Task type, String description){
        this.setJascId("JASC-CH-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
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
}
