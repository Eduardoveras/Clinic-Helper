package Entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="EQUIPMENTS")
public class Equipment {
    private String equipmentId;
    private String equipmentName;
    private String equipmentUse;
    private String equipmentDescription;
}
