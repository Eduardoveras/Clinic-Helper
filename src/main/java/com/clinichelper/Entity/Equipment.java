package com.clinichelper.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="equipments")
public class Equipment {
    @Id
    private String equipmentId;
    private String equipmentName;
    private String equipmentUse;
    private String equipmentDescription;

   public  Equipment(){

   }

    public Equipment(String equipmentName, String equipmentUse, String equipmentDescription) {
        this.setEquipmentId("JASC-E-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setEquipmentName(equipmentName);
        this.setEquipmentUse(equipmentUse);
        this.setEquipmentDescription(equipmentDescription);
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentUse() {
        return equipmentUse;
    }

    public void setEquipmentUse(String equipmentUse) {
        this.equipmentUse = equipmentUse;
    }

    public String getEquipmentDescription() {
        return equipmentDescription;
    }

    public void setEquipmentDescription(String equipmentDescription) {
        this.equipmentDescription = equipmentDescription;
    }
}
