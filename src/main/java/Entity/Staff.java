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
        this.staffName = staffName;
        this.staffClinicId = staffClinicId;
        this.staffRole = staffRole;
        this.staffRoleDescription = staffRoleDescription;
    }
}
