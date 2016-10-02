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
public class Insurrance implements Serializable{
    // Attributes
    @Id
    private String jascID;
    @ManyToOne
    private Patient ownwer;
    private String insurranceSerialCode;
    private String insurrancePlan;

    // Constructors
    public Insurrance(){

    }

    public Insurrance(Patient owner, String insurranceSerialCode, String insurrancePlan){
        this.setJascID("JASC-I-" + UUID.randomUUID().toString().split("-")[0]);
        this.setOwnwer(owner);
        this.setInsurranceSerialCode(insurranceSerialCode);
        this.setInsurrancePlan(insurrancePlan);
    }

    public String getJascID() {
        return jascID;
    }

    public void setJascID(String jascID) {
        this.jascID = jascID;
    }

    public Patient getOwnwer() {
        return ownwer;
    }

    public void setOwnwer(Patient ownwer) {
        this.ownwer = ownwer;
    }

    public String getInsurranceSerialCode() {
        return insurranceSerialCode;
    }

    public void setInsurranceSerialCode(String insurranceSerialCode) {
        this.insurranceSerialCode = insurranceSerialCode;
    }

    public String getInsurrancePlan() {
        return insurrancePlan;
    }

    public void setInsurrancePlan(String insurrancePlan) {
        this.insurrancePlan = insurrancePlan;
    }
}
