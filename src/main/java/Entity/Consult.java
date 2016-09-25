package Entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="CONSULTS")
public class Consult {
    private String consultId;
    private Date consultDate;
    private Timestamp consultTime;
    private String consultDetail;
    private Surgery ConsultSurgery;
    private Appointment consultAppointment;

}
