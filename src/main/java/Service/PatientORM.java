/**
 * Created by Eduardo veras on 25-Sep-16.
 */
package Service;


import Entity.Patient;

public class PatientORM extends GenericORMService<Patient>{
    private static PatientORM instance;

    private PatientORM(){
        super(Patient.class);
    }

    public static PatientORM getInstance(){
        if(instance == null)
            instance = new PatientORM();

        return instance;
    }
}
