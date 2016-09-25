package Entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="SURGERIES")
public class Surgery {
    private String surgeryId;
    private String surgeryName;
    private String surgeryBodyPart;
    private Date surgeryDate;
    private Timestamp surgeryTime;
    private String surgeryRoom;
    private Staff surgeryStaff;
    private Equipment surgeryEquipment;
    private Appointment surgeryAppointment;
}
