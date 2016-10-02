/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Insurance implements Serializable{
    // Attributes
    @Id
    private String jascID;
    @ManyToOne
    private Patient owner;
    private String insuranceSerialCode;
    private String insurancePlan;

    // Constructors
    public Insurance(){

    }

    public Insurance(Patient owner, String insuranceSerialCode, String insurancePlan){
        this.setJascID("JASC-I-" + UUID.randomUUID().toString().split("-")[0]);
        this.setOwner(owner);
        this.setInsuranceSerialCode(insuranceSerialCode);
        this.setInsurancePlan(insurancePlan);
    }

    public String getJascID() {
        return jascID;
    }

    public void setJascID(String jascID) {
        this.jascID = jascID;
    }

    public Patient getOwner() {
        return owner;
    }

    public void setOwner(Patient owner) {
        this.owner = owner;
    }

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
