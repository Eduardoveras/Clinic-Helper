package com.clinichelper.Entity;

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
    private String staffEmail;
    private String staffClinicId;
    private String staffRole;
    private String staffRoleDescription;

    public Staff(){

    }

    public Staff(String staffFirstName,String staffLastName, String staffEmail, String staffClinicId, String staffRole, String staffRoleDescription) {
        this.setJascId("JASC-STAFF-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setStaffFirstName(staffFirstName);
        this.setStaffLastName(staffLastName);
        this.setStaffEmail(staffEmail);
        this.setStaffClinicId(staffClinicId);
        this.setStaffRole(staffRole);
        this.setStaffRoleDescription(staffRoleDescription);
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

    public String getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(String staffRole) {
        this.staffRole = staffRole;
    }

    public String getStaffRoleDescription() {
        return staffRoleDescription;
    }

    public void setStaffRoleDescription(String staffRoleDescription) { this.staffRoleDescription = staffRoleDescription; }

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
