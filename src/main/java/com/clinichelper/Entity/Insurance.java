/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package com.clinichelper.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "insurance")
public class Insurance implements Serializable{
    // Attributes
    @Id
    private String insuranceId;
    @ManyToOne
    @NotNull
    private Patient owner;
    @NotNull
    private String insuranceSerialCode;
    @NotNull
    private String insuranceSupplier;
    @NotNull
    private String insurancePlan;

    // Constructors
    public Insurance(){

    }

    public Insurance(Patient owner, String insuranceSerialCode, String insuranceSupplier, String insurancePlan){
        this.setInsuranceId( owner.getPatientId() + "-I-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setOwner(owner);
        this.setInsuranceSerialCode(insuranceSerialCode);
        this.setInsuranceSupplier(insuranceSupplier);
        this.setInsurancePlan(insurancePlan);
    }

    // Getters and Setters
    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Patient getOwner() { return owner; }

    public void setOwner(Patient owner) { this.owner = owner; }

    public String getInsuranceSerialCode() {
        return insuranceSerialCode;
    }

    public void setInsuranceSerialCode(String insuranceSerialCode) {
        this.insuranceSerialCode = insuranceSerialCode;
    }

    public String getInsurancePlan() {
        return insurancePlan;
    }

    public void setInsurancePlan(String insurancePlan) {
        this.insurancePlan = insurancePlan;
    }

    public String getInsuranceSupplier() {
        return insuranceSupplier;
    }

    public void setInsuranceSupplier(String insuranceSupplier) {
        this.insuranceSupplier = insuranceSupplier;
    }
}
