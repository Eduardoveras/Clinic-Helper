/**
 * Created by Djidjelly Siclait on 25-Sep-16.
 */
package Service;

import Entity.Appointment;

public class AppointmentORM extends GenericORMService<Appointment>{
    private static AppointmentORM instance;

    private AppointmentORM(){
        super(Appointment.class);
    }

    public static AppointmentORM getInstance(){
        if(instance == null)
            instance = new AppointmentORM();

        return instance;
    }
}
