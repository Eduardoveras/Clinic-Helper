package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="PATIENTS")
public class Patient {
    @Id
    @GeneratedValue
    private String patientId;
    private String patientName;
    private String patientLastName;
    private String patientIdCard;
    private String patientTelephoneNumber;
    private String patientBirthDate;
    private String patientNationality;
    private String patientAddress;

    public Patient(){

    }

    public Patient(String patientName, String patientLastName, String patientIdCard, String patientTelephoneNumber, String patientBirthDate, String patientNationality, String patientAddress) {
        this.patientName = patientName;
        this.patientLastName = patientLastName;
        this.patientIdCard = patientIdCard;
        this.patientTelephoneNumber = patientTelephoneNumber;
        this.patientBirthDate = patientBirthDate;
        this.patientNationality = patientNationality;
        this.patientAddress = patientAddress;
    }
}
