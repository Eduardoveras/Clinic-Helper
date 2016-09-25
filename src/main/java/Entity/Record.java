package Entity;

import javax.persistence.*;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="RECORDS")
public class Record {
    @Id
    @GeneratedValue
    private String recordId;
    @OneToOne
    private Patient recordPatient;
    private String recordDetails;
    @OneToMany
    private Surgery recordSurgery;
    @OneToMany
    private Consult recordConsult;


    public Record(){

    }

    public Record(Patient recordPatient, String recordDetails, Surgery recordSurgery, Consult recordConsult) {
        this.recordPatient = recordPatient;
        this.recordDetails = recordDetails;
        this.recordSurgery = recordSurgery;
        this.recordConsult = recordConsult;
    }
}
