package Entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="APPOINTMENTS")
public class Appointment {
    @Id
    private String jascId;
    private Date appointmentDate;
    private Timestamp appointmentTime;
    @ManyToOne
    private Patient appointmentPatient;
    @Column(length = 500)
    private String appointmentDescription;
    private String appointmentAccessFrom;

    public Appointment(){

    }

    public Appointment(Date appointmentDate, Patient appointmentPatient, String appointmentDescription, String appointmentAccessFrom){
        this.setJascId("JASC-A-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setAppointmentDate(appointmentDate);
        this.setAppointmentPatient(appointmentPatient);
        this.setAppointmentDescription(appointmentDescription);
        this.setAppointmentAccessFrom(appointmentAccessFrom);
    }

    public Appointment(Date appointmentDate, Timestamp appointmentTime, Patient appointmentPatient, String appointmentDescription, String appointmentAccessFrom){
        this.setAppointmentDate(appointmentDate);
        this.setAppointmentTime(appointmentTime);
        this.setAppointmentPatient(appointmentPatient);
        this.setAppointmentDescription(appointmentDescription);
        this.setAppointmentAccessFrom(appointmentAccessFrom);
    }

    public String getJascId() {
        return jascId;
    }

    public void setJascId(String jascId) {
        this.jascId = jascId;
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

    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }
}
