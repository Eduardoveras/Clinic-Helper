package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="EQUIPMENTS")
public class Equipment {
    @Id
    @GeneratedValue
    private String equipmentId;
    private String equipmentName;
    private String equipmentUse;
    private String equipmentDescription;

   public  Equipment(){

   }

    public Equipment(String equipmentName, String equipmentUse, String equipmentDescription) {
        this.equipmentName = equipmentName;
        this.equipmentUse = equipmentUse;
        this.equipmentDescription = equipmentDescription;
    }
}
