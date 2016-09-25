package Entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="PATIENTS")
public class Patient {
    private String patientId;
    private String patientName;
    private String patientLastName;
    private String patientIdCard;
    private String patientTelephoneNumber;
    private String patientBirthDate;
    private String patientNationality;
    private String patientAddress;
}
