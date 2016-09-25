package Entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="STAFF")
public class Staff {
    private String staffId;
    private String staffName;
    private String staffClinicId;
    private String staffRole;
    private String staffRoleDescription;
}
