/**
 * Created by Djidjelly Siclait on 25-Sep-16.
 */
package Service;

import Entity.Consult;

public class ConsultationORM  extends GenericORMService<Consult>{
    private static ConsultationORM instance;

    private ConsultationORM(){
        super(Consult.class);
    }

    public static ConsultationORM getInstance(){
        if(instance == null)
            instance = new ConsultationORM();

        return instance;
    }
}
