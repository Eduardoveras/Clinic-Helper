/**
 * Created by Djidjelly Siclait on 10/8/2016.
 */
package com.clinichelper.Service;

import com.clinichelper.Entity.*;
import com.clinichelper.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.sql.Date;
import java.sql.Timestamp;

@Service
public class DataEntryAndManagementService {
    // Auxiliary tools
    private final static long MILLISECONDS_IN_A_DAY = 24 * 60 * 60 * 1000;

    // Repositories
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private InsuranceRepository insuranceRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private UserRepository userRepository;

    // Creation functions
    public Appointment createNewAppointment(Date appointmentDate, Timestamp appointmentTime, String patientJascId, String appointmentDescription, String appointmentAccessFrom) throws Exception {

        if (isRequestedDateExpired(appointmentDate))
            throw new IllegalArgumentException("\n\nThe requested date has passed; it is no longer valid");

        if (!doesPatientJascIdExist(patientJascId))
            throw new IllegalArgumentException("\n\nThis is an invalid patient jascId");

        try {
            return appointmentRepository.save(new Appointment(appointmentDate, appointmentTime, patientRepository.findByJascId(patientJascId), appointmentDescription, appointmentAccessFrom));
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis appointment was not able to persist -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occured when trying to create an appointment -> " + exp.getMessage());
        }
    }

    public Insurance createNewInsurance(String ownerJascId, String insuranceSerialCode, String insurancePlan) throws Exception{

        if (!doesPatientJascIdExist(ownerJascId))
            throw new IllegalArgumentException("\n\nThis is an invalid patient jascId");

        try {
            return insuranceRepository.save(new Insurance(patientRepository.findByJascId(ownerJascId), insuranceSerialCode, insurancePlan));
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis insurance was not able to persist -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occured when trying to create an insurance -> " + exp.getMessage());
        }
    }

    public Patient createNewPatient(String patientFirstName, String patientLastName, String patientIdCard, String patientTelephoneNumber, String patientContactTelephoneNumber, String patientGender, String patientEmail, Date patientBirthDate, String patientNationality, String patientAddress, String patientCity, String patientCountry) throws Exception {

        try {
            return patientRepository.save(new Patient(patientFirstName, patientLastName, patientIdCard, patientTelephoneNumber, patientContactTelephoneNumber, patientGender, patientEmail, patientBirthDate, patientNationality, patientAddress, patientCity, patientCountry));
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis patient was not able to persist -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create a patient -> " + exp.getMessage());
        }
    }

    public Staff createNewStaffMember(String staffFirstName,String staffLastName, String staffEmail, String staffClinicId) throws Exception {

        try{
            return staffRepository.save(new Staff(staffFirstName, staffLastName, staffEmail, staffClinicId));
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis patient was not able to persist -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create a patient -> " + exp.getMessage());
        }
    }

    public User createNewUserAccount(String username, String staffJascId, String password, String role) throws Exception {

        if (isUsernameAlreadyTaken(username))
            throw new IllegalArgumentException("\n\nThis username is already taken. Please choose another one!");

        if (!doesStaffJascIdExist(staffJascId))
            throw new IllegalArgumentException("\n\nThis staff jasc id is invalid");

        try {
            return userRepository.save(new User(username, staffRepository.findByJascId(staffJascId), password, role));
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis patient was not able to persist -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create a patient -> " + exp.getMessage());
        }
    }

    // Auxiliary Function
    private boolean doesPatientJascIdExist(String jascId){
        Patient patient = patientRepository.findByJascId(jascId);

        return (patient != null);
    }

    private boolean doesStaffJascIdExist(String jascId){
        Staff staff = staffRepository.findByJascId(jascId);

        return (staff != null);
    }

    private boolean isUsernameAlreadyTaken(String username){
        User user = userRepository.findByUsername(username);

        return (user != null);
    }

    private boolean isRequestedDateExpired(Date requestedDate){
        java.util.Date utilDate = new java.util.Date();
        Date today = new Date(utilDate.getTime()); // getting today's date;

        if (differenceInDays(requestedDate, today) >= 0) // checking if requested date is valid
            return true; // the requested date has expired; it is too late
        else
            return false; // the requested date still has not passed; date is still valid
    }

    private int differenceInDays(Date start, Date end){
        return (int)((end.getTime() - start.getTime()) / MILLISECONDS_IN_A_DAY);
    }
}
