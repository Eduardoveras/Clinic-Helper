package com.clinichelper.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="equipments")
public class Equipment implements Serializable{
    @Id
    private String equipmentId;
    @NotNull
    private String equipmentName;
    @NotNull
    private String equipmentUse;
    private String equipmentDescription;
    @ManyToOne
    private Clinic clinic;

    // Constructores
    public  Equipment(){

    }

    public Equipment(Clinic clinic, String equipmentName, String equipmentUse, String equipmentDescription) {
        this.setEquipmentId(clinic.getClinicPrefix() + "-E-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setEquipmentName(equipmentName.toUpperCase());
        this.setEquipmentUse(equipmentUse);
        this.setEquipmentDescription(equipmentDescription);
        this.clinic = clinic;
    }

    //Getters and Setters
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
