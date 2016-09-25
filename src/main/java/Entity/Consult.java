package Entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="CONSULTS")
public class Consult {
    @Id
    @GeneratedValue
    private String consultId;
    private Date consultDate;
    private Timestamp consultTime;
    private String consultDetail;
    @OneToOne
    private Surgery consultSurgery;
    @OneToOne
    private Appointment consultAppointment;

    public Consult(){

    }
    public Consult(Date consultDate,Timestamp consultTime, String consultDetail, Surgery consultSurgery,Appointment consultAppointment){
        this.consultDate= consultDate;
        this.consultTime= consultTime;
        this.consultDetail= consultDetail;
        this.consultSurgery= consultSurgery;
        this.consultAppointment= consultAppointment;

    }

}
