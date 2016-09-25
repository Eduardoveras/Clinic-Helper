package Entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="SURGERIES")
public class Surgery {
    @Id
    @GeneratedValue
    private String surgeryId;
    private String surgeryName;
    private String surgeryBodyPart;
    private Date surgeryDate;
    private Timestamp surgeryTime;
    private String surgeryRoom;
    private Staff surgeryStaff;
    @OneToMany
    private Equipment surgeryEquipment;
    @OneToOne
    private Appointment surgeryAppointment;

    public Surgery(){

    }

    public Surgery(String surgeryName, String surgeryBodyPart, Date surgeryDate, Timestamp surgeryTime, String surgeryRoom, Staff surgeryStaff, Equipment surgeryEquipment, Appointment surgeryAppointment) {
        this.surgeryName = surgeryName;
        this.surgeryBodyPart = surgeryBodyPart;
        this.surgeryDate = surgeryDate;
        this.surgeryTime = surgeryTime;
        this.surgeryRoom = surgeryRoom;
        this.surgeryStaff = surgeryStaff;
        this.surgeryEquipment = surgeryEquipment;
        this.surgeryAppointment = surgeryAppointment;
    }
}
