/**
 * Created by Djidjelly Siclait on 9/25/2016.
 */
package Tools;

import Entity.Appointment;
import Entity.Patient;
import Entity.User;
import Service.AppointmentORM;
import Service.PatientORM;
import Service.UserORM;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public class DatabaseManager {

    // Declaring Singleton
    private DatabaseManager(){

    }

    public void bootDatabaseServer(){
        //Setting up default configurations
        List<User> users = UserORM.getInstance().FindAll();

        if (users.size() == 0){
            System.out.println("Creating True Admin ...");
            //Creating True Admin for Application
            UserORM.getInstance().Create(new User("admin", "JASC", "ITTT", "djidjellypas92@gmail.com", "admin", "admin"));
            System.out.println("True Admin created...");
        }
    }

    // User Related Functions
    public boolean isUsernameTaken(String username){
        User user = UserORM.getInstance().Find(username);

        return (user != null ) ? true : false;
    }

    public boolean createNewUser(String username, String firstName, String lastName, String email, String password, String role){
        try{
            UserORM.getInstance().Create(new User(username.toUpperCase(), firstName.toUpperCase(), lastName.toUpperCase(), email.toLowerCase(), password, role));
            System.out.println("Created new user");
            return true;
        } catch (EntityNotFoundException exp){
            System.out.println("\n\nEntity ERROR! --> " + exp.getMessage() + "\n");
            return false;
        } catch (TransactionRequiredException exp) {
            System.out.println("\n\nTransaction ERROR! --> " + exp.getMessage() + "\n");
            return false;
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence ERROR! --> " + exp.getMessage() + "\n");
            return false;
        } catch (Exception exp){
            System.out.println("\n\nGeneral ERROR! --> " + exp.getMessage() + "\n");
            return false;
        }
    }

    public boolean doesUserAccountExist(String username, String password){
        User account = UserORM.findUserAccountWithUsernameAndPassword(username.toUpperCase(), password);

        return (account != null) ? true : false;
    }

    public void deleteUserAccount(String username){
        UserORM.getInstance().Delete(UserORM.getInstance().Find(username.toUpperCase()));
    }

    // Patient Related Function
    public boolean createNewPatient(String firstName, String lastName, String identificationCard, String telephoneNumber, Date dateOfBirth, String nationality, String address, String city, String country){
        try {
            PatientORM.getInstance().Create(new Patient(firstName.toUpperCase(), lastName.toUpperCase(), identificationCard.toUpperCase(), telephoneNumber, dateOfBirth, nationality.toUpperCase(), address.toUpperCase(), city.toUpperCase(), country.toUpperCase()));
            System.out.println("Created new patient!");
            return true;
        } catch (EntityNotFoundException exp){
            System.out.println("\n\nEntity ERROR! --> " + exp.getMessage() + "\n");
            return false;
        } catch (TransactionRequiredException exp) {
            System.out.println("\n\nTransaction ERROR! --> " + exp.getMessage() + "\n");
            return false;
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence ERROR! --> " + exp.getMessage() + "\n");
            return false;
        } catch (Exception exp){
            System.out.println("\n\nGeneral ERROR! --> " + exp.getMessage() + "\n");
            return false;
        }
    }

    public Patient findRegisteredPatient(String searchKey){
        Patient patient;

        // Search by System Id
        patient = PatientORM.getInstance().Find(searchKey.toUpperCase());

        if (patient != null)
            return patient;

        // Search by Identification Card
        patient = PatientORM.findPatientByIdentificationCard(searchKey.toUpperCase());

        if (patient != null)
            return patient;

        return null;
    }

    // TODO: test this function
    public List<Patient> generalSearchForPatientsByIdentifier(String identifier){
        return PatientORM.generateListOfPatientsThatMatchRegEx(identifier);
    }

    // Appointment Related Functions
    public boolean createNewAppointment(Date appointmentDate, Timestamp appointmentTime, String patientId, String appointmentAccessFrom){
        try {
            // Creating a new Appointment
            AppointmentORM.getInstance().Create(new Appointment(appointmentDate, appointmentTime, findRegisteredPatient(patientId), appointmentAccessFrom));
            System.out.println("Created new Appointment!");
            return true;
        } catch (EntityNotFoundException exp){
            System.out.println("\n\nEntity ERROR! --> " + exp.getMessage() + "\n");
            return false;
        } catch (TransactionRequiredException exp) {
            System.out.println("\n\nTransaction ERROR! --> " + exp.getMessage() + "\n");
            return false;
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence ERROR! --> " + exp.getMessage() + "\n");
            return false;
        } catch (Exception exp){
            System.out.println("\n\nGeneral ERROR! --> " + exp.getMessage() + "\n");
            return false;
        }
    }

    public void deleteRegisteredAppointment(String appointmentId){
        AppointmentORM.getInstance().Delete(AppointmentORM.getInstance().Find(appointmentId));
    }

    public List<Appointment> findAllAppointmentsForToday(){
        return findAllAppointmentsForGivenDay(new Date(Calendar.getInstance().getTime().getTime()));
    }

    public List<Appointment> findAllAppointmentsForGivenDay(Date date){
        return AppointmentORM.findAppointmentsByDate(date);
    }

    public List<Appointment> findAllAppointmentsForAGivenMonth(Date startDate){
        return AppointmentORM.findAppointmentsByMonth(startDate);
    }
}
