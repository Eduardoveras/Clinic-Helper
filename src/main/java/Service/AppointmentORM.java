/**
 * Created by Djidjelly Siclait on 25-Sep-16.
 */
package Service;

import Entity.Appointment;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

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

    public static List<Appointment> findAppointmentsByDate(Date searchdate){
        EntityManager em = GetEntityManager();

        TypedQuery<Appointment> query = em.createQuery("select a from Appointment as a where a.appointmentDate = '" + searchdate + "'", Appointment.class);

        return query.getResultList();
    }

    // TODO: Translate following functions into AppointmentRepository interface
    public static List<Appointment> findAppointmentsByWeek(Date startDate){
        // Calculating last date of the week given the start date
        Calendar lastDate = Calendar.getInstance();
        lastDate.setTime(startDate);
        lastDate.add(Calendar.DATE, 7);

        // Executing Query
        EntityManager em = GetEntityManager();

        TypedQuery<Appointment> query = em.createQuery("select a from Appointment as a where a.appointmentDate between '" + startDate + "' and '" + lastDate + "'", Appointment.class);

        return query.getResultList();
    }

    public static List<Appointment> findAppointmentsByMonth(Date startDate){
        // Calculating last date of the week given the start date
        Calendar lastDate = Calendar.getInstance();
        lastDate.setTime(startDate);
        lastDate.add(Calendar.DATE, getNumberOfDaysInTheMonthOfAGivenDate(startDate));

        // Executing Query
        EntityManager em = GetEntityManager();

        TypedQuery<Appointment> query = em.createQuery("select a from Appointment as a where a.appointmentDate between '" + startDate + "' and '" + lastDate + "'", Appointment.class);

        return query.getResultList();
    }

    // Auxiliary Functions
    private static int getNumberOfDaysInTheMonthOfAGivenDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        switch (calendar.get(Calendar.MONTH)){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            // TODO: improve leap year if logic, use KISS
            case 2: // Calculating Leap years
                if(calendar.get(Calendar.YEAR) % 4 == 0)
                    if(calendar.get(Calendar.YEAR) % 100 == 0)
                        if (calendar.get(Calendar.YEAR) % 400 == 0)
                            return 29;
                        else
                            return 28;
                    else
                        return 29;
                else
                    return 28;
            default:
                return 30;
        }
    }
}
