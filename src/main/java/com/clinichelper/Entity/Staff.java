package com.clinichelper.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="staff")
public class Staff {
    @Id
    private String staffId;
    private String staffName;
    private String staffClinicId;
    private String staffRole;
    private String staffRoleDescription;

    public Staff(){

    }

    public Staff(String staffName, String staffClinicId, String staffRole, String staffRoleDescription) {
        this.setStaffId("JASC-STAFF-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setStaffName(staffName);
        this.setStaffClinicId(staffClinicId);
        this.setStaffRole(staffRole);
        this.setStaffRoleDescription(staffRoleDescription);
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
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
}
