package Entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="APPOINTMENTS")
public class Appointment {
    @Id
    @GeneratedValue
    private String appointmentId;
    private Date appointmentDate;
    private Timestamp appointmentTime;
    @ManyToOne
    private Patient appointmentPatient;
    private String appointmentAccessFrom;

    public Appointment(){

    }

    public Appointment(Date appointmentDate, Timestamp appointmentTime,Patient appointmentPatient, String appointmentAccessFrom){
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.appointmentPatient = appointmentPatient;
        this.appointmentAccessFrom = appointmentAccessFrom;
    }
}
