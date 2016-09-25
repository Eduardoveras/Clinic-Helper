package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="STAFF")
public class Staff {
    @Id
    @GeneratedValue
    private String staffId;
    private String staffName;
    private String staffClinicId;
    private String staffRole;
    private String staffRoleDescription;

    public Staff(){

    }

    public Staff(String staffName, String staffClinicId, String staffRole, String staffRoleDescription) {
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

    public void setStaffRoleDescription(String staffRoleDescription) {
        this.staffRoleDescription = staffRoleDescription;
    }
}
