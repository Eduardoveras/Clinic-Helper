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
        this.setAppointmentDate(appointmentDate);
        this.setAppointmentTime(appointmentTime);
        this.setAppointmentPatient(appointmentPatient);
        this.setAppointmentAccessFrom(appointmentAccessFrom);
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Timestamp getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Timestamp appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Patient getAppointmentPatient() {
        return appointmentPatient;
    }

    public void setAppointmentPatient(Patient appointmentPatient) {
        this.appointmentPatient = appointmentPatient;
    }

    public String getAppointmentAccessFrom() {
        return appointmentAccessFrom;
    }

    public void setAppointmentAccessFrom(String appointmentAccessFrom) {
        this.appointmentAccessFrom = appointmentAccessFrom;
    }
}
