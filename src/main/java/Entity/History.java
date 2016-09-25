package Entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="history")
public class History {
    private String historyId;
    private Patient historyPatient;
    private String Details;

}
