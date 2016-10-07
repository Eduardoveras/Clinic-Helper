/**
 * Created by Djidjelly Siclait on 9/25/2016.
 */
package Tools;

import Entity.Appointment;
import Entity.Insurance;
import Entity.Patient;
import Service.AppointmentORM;
import Service.PatientORM;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DatabaseManager {
    // Attributes
    private static List<Patient> patientRegistrationWaitingList = new ArrayList<Patient>();
    private static List<Appointment> appointmentRequestWaitingList = new ArrayList<Appointment>();

    // Declaring Singleton
    private DatabaseManager(){

    }

    public static void bootDatabaseServer(){
        //Setting up default configurations
        //List<User> users = UserORM.getInstance().FindAll();
/*
        if (users.size() == 0){
            System.out.println("Creating True Admin ...");
            //Creating True Admin for Application
            UserORM.getInstance().Create(new User("admin", "JASC", "ITTT", "djidjellypas92@gmail.com", "admin", "admin"));
            System.out.println("True Admin created...");
        }*/
    }

    // Auxiliary Functions
    public static void createNewAppointmentRequest(Date requestedDate, Patient solicitor, String description, String accessFrom){
        appointmentRequestWaitingList.add(new Appointment(requestedDate, solicitor, description, accessFrom));
    }

    public static List<Appointment> showAllAppointmentRequest(){
        return appointmentRequestWaitingList;
    }

    public static void approveAppointmentRequest(String appointmentJascId, Date registerdDate,  Timestamp appointmentTime, Patient solicitor){
        Appointment request = null;

        for (Appointment a:
             appointmentRequestWaitingList)
            if(a.getJascId().equals(appointmentJascId)){
                request = a;
                break;
            }

        if (request != null)
            try{
                // Approving new appointment request
                AppointmentORM.getInstance().Create(new Appointment(registerdDate, appointmentTime, solicitor, request.getAppointmentDescription(), request.getAppointmentAccessFrom()));
                System.out.println("\n\nAppointment registered...");
                appointmentRequestWaitingList.remove(request);
            } catch (EntityNotFoundException exp){
                System.out.println("\n\nEntity ERROR! --> " + exp.getMessage() + "\n");
                throw exp;
            } catch (TransactionRequiredException exp) {
                System.out.println("\n\nTransaction ERROR! --> " + exp.getMessage() + "\n");
                throw exp;
            } catch (PersistenceException exp){
                System.out.println("\n\nPersistence ERROR! --> " + exp.getMessage() + "\n");
                throw exp;
            } catch (Exception exp) {
                System.out.println("\n\nGeneral ERROR! --> " + exp.getMessage() + "\n");
                throw exp;
            }
    }

    public static void addUnregisteredPatientInRegistrationWaitingList(String patientFirstName, String patientLastName, String patientTelephoneNumber, String patientEmail){
        patientRegistrationWaitingList.add(new Patient(patientFirstName, patientLastName, patientTelephoneNumber, patientEmail));
    }

    public static List<Patient> showAllUnregisteredPatients(){
        return patientRegistrationWaitingList;
    }

    public static void approveNewPatientForRegistration(String patientJascId, String patientIdCard, String patientContactTelephoneNumber, String patientGender,  Date patientBirthDate, String patientNationality, String patientAddress, String patientCity, String patientCountry){
        Patient candidate = null;

        for (Patient p:
             patientRegistrationWaitingList)
            if(p.getJascId().equals(patientJascId)){
                candidate = p;
                break;
            }

        if (candidate != null)
            try{
                // Approving new patient
                PatientORM.getInstance().Create(new Patient(candidate.getPatientName(), candidate.getPatientLastName(), patientIdCard, candidate.getPatientTelephoneNumber(), patientContactTelephoneNumber, patientGender, candidate.getPatientEmail(), patientBirthDate, patientNationality, patientAddress, patientCity, patientCountry));
                System.out.println("\n\nNew patient approved...");
                patientRegistrationWaitingList.remove(candidate);
            } catch (EntityNotFoundException exp){
                System.out.println("\n\nEntity ERROR! --> " + exp.getMessage() + "\n");
                throw exp;
            } catch (TransactionRequiredException exp) {
                System.out.println("\n\nTransaction ERROR! --> " + exp.getMessage() + "\n");
                throw exp;
            } catch (PersistenceException exp){
                System.out.println("\n\nPersistence ERROR! --> " + exp.getMessage() + "\n");
                throw exp;
            } catch (Exception exp) {
                System.out.println("\n\nGeneral ERROR! --> " + exp.getMessage() + "\n");
                throw exp;
            }
    }

    // User Related Functions
 /*   public static boolean isUsernameTaken(String username){
        User user = UserORM.getInstance().Find(username);

        return ( user != null );
    }

    public static boolean createNewUser(String username, String firstName, String lastName, String email, String password, String role){
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

    public static boolean doesUserAccountExist(String username, String password){
        User account = UserORM.findUserAccountWithUsernameAndPassword(username.toUpperCase(), password);

        return (account != null) ? true : false;
    }

    public static void deleteUserAccount(String username){
        UserORM.getInstance().Delete(UserORM.getInstance().Find(username.toUpperCase()));
    }
*/
    // Patient Related Function
    public static boolean createNewPatient(String firstName, String lastName, String identificationCard, String telephoneNumber, String patientEmail, Date dateOfBirth, String nationality, String address, String city, String country){
        try {
            //PatientORM.getInstance().Create(new Patient(firstName.toUpperCase(), lastName.toUpperCase(), identificationCard.toUpperCase(), telephoneNumber, patientEmail.toLowerCase(), dateOfBirth, nationality.toUpperCase(), address.toUpperCase(), city.toUpperCase(), country.toUpperCase()));
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

    public static Patient findRegisteredPatient(String firstName, String lastName, String telephone, String email){
        return PatientORM.findRegisteredPatientByFLTEFields(firstName, lastName, telephone, email);
    }

    public static Patient findRegisteredPatient(String searchKey){
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
    public static List<Patient> generalSearchForPatientsByIdentifier(String identifier){
        return PatientORM.generateListOfPatientsThatMatchRegEx(identifier);
    }

    // Appointment Related Functions
    public static boolean createNewAppointment(Date appointmentDate, Timestamp appointmentTime, String patientId, String appointmentDescription, String appointmentAccessFrom){
        try {
            // Creating a new Appointment
            AppointmentORM.getInstance().Create(new Appointment(appointmentDate, appointmentTime, findRegisteredPatient(patientId), appointmentDescription, appointmentAccessFrom));
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

    public static void deleteRegisteredAppointment(String appointmentId){
        AppointmentORM.getInstance().Delete(AppointmentORM.getInstance().Find(appointmentId));
    }

    public static List<Appointment> findAllAppointmentsForToday(){
        return findAllAppointmentsForGivenDay(new Date(Calendar.getInstance().getTime().getTime()));
    }

    public static List<Appointment> findAllAppointmentsForGivenDay(Date date){
        return AppointmentORM.findAppointmentsByDate(date);
    }

    public static List<Appointment> findAllAppointmentsForAGivenMonth(Date startDate){
        return AppointmentORM.findAppointmentsByMonth(startDate);
    }

    // Insurance Related Functions
    /*
    public static boolean registerNewInsuranceForPatient(String patientJascId, String insuranceSerialCode, String insurancePlan){
        try{
            // Registering patient's insurance information
            InsuranceORM.getInstance().Create(new Insurance(PatientORM.getInstance().Find(patientJascId), insuranceSerialCode, insurancePlan));
            return true;
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
    }*/
}
