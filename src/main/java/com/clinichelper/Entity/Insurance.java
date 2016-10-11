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
    private String jascId;
    @ManyToOne
    @NotNull
    private Patient owner;
    @NotNull
    private String insuranceSerialCode;
    @NotNull
    private String insurancePlan;

    // Constructors
    public Insurance(){

    }

    public Insurance(Patient owner, String insuranceSerialCode, String insurancePlan){
        this.setJascId("JASC-I-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setOwner(owner);
        this.setInsuranceSerialCode(insuranceSerialCode);
        this.setInsurancePlan(insurancePlan);
    }

    public String getJascId() {
        return jascId;
    }

    public void setJascId(String jascId) {
        this.jascId = jascId;
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
}
