package Entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="APPOINTMENTS")
public class Appointment {
    private String appointmentId;
    private Date appointmentDate;
    private Timestamp appointmentTime;
    private Patient appointmentPatient;
    private enum appointmentAccessFrom{
        Web,
        Visit;
    }
}
