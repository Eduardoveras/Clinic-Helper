/**
 * Created by Djidjelly Siclait on 25-Sep-16.
 */
package Service;

import Entity.Consultation;

public class ConsultationORM  extends GenericORMService<Consultation>{
    private static ConsultationORM instance;

    private ConsultationORM(){
        super(Consultation.class);
    }

    public static ConsultationORM getInstance(){
        if(instance == null)
            instance = new ConsultationORM();

        return instance;
    }
}
