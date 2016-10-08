package com.clinichelper.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="staff")
public class Staff implements Serializable{
    @Id
    private String jascId;
    private String staffFirstName;
    private String staffLastName;
    @Column(unique = true)
    private String staffEmail;
    @Column(unique = true)
    private String staffClinicId;

    public Staff(){

    }

    public Staff(String staffFirstName,String staffLastName, String staffEmail, String staffClinicId) {
        this.setJascId("JASC-STAFF-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setStaffFirstName(staffFirstName);
        this.setStaffLastName(staffLastName);
        this.setStaffEmail(staffEmail);
        this.setStaffClinicId(staffClinicId);
    }

    public String getJascId() {
        return jascId;
    }

    public void setJascId(String jascId) {
        this.jascId = jascId;
    }

    public String getStaffFirstName() {
        return staffFirstName;
    }

    public void setStaffFirstName(String staffFirstName) {
        this.staffFirstName = staffFirstName;
    }

    public String getStaffClinicId() {
        return staffClinicId;
    }

    public void setStaffClinicId(String staffClinicId) {
        this.staffClinicId = staffClinicId;
    }

    public String getStaffLastName() {
        return staffLastName;
    }

    public void setStaffLastName(String staffLastName) {
        this.staffLastName = staffLastName;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }
}
