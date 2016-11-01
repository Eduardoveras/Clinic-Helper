/**
 * Created by Djidjelly Siclait on 11/1/2016.
 */
package com.clinichelper.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "medications")
public class Medication implements Serializable{
    // Attributes
    @Id
    private String medicationId;
    private String medicationName;
    private String supplier;
    @Column(length = 500)
    private String medicationDescription;
    private Integer medicationInStock;
    @ManyToOne
    private Clinic clinic;

    // Constructors
    public Medication(){

    }

    public Medication(Clinic clinic, String medicationName, String supplier, String medicationDescription, Integer medicationInStock){
        this.setMedicationId(clinic.getClinicPrefix() + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setMedicationName(medicationName);
        this.setSupplier(supplier);
        this.setMedicationDescription(medicationDescription);
        this.setMedicationInStock(medicationInStock);
    }

    // Getters and Setters
    public String getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getMedicationDescription() {
        return medicationDescription;
    }

    public void setMedicationDescription(String medicationDescription) {
        this.medicationDescription = medicationDescription;
    }

    public Integer getMedicationInStock() {
        return medicationInStock;
    }

    public void setMedicationInStock(Integer medicationInStock) {
        this.medicationInStock = medicationInStock;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
}
