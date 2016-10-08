/**
 * Created by Djidjelly Siclait on 10/8/2016.
 */
package com.clinichelper.Service;

import com.clinichelper.Entity.*;
import com.clinichelper.Repository.*;
import freemarker.template.utility.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Service
public class DataEntryAndManagementService {
    // Auxiliary tools
    private final static long MILLISECONDS_IN_A_DAY = 24 * 60 * 60 * 1000;

    // Repositories
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private InsuranceRepository insuranceRepository;
    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private SurgeryRepository surgeryRepository;
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
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create an appointment -> " + exp.getMessage());
        }
    }

    public Consultation createNewConsultation(Date date, Timestamp time, String detail, String surgeryJascId, String appointmentJascId) throws Exception{

        if (!doesSurguryJascIdExist(surgeryJascId))
            throw new IllegalArgumentException("\n\nThis surgery jasc id is noe valid");

        if (!doesAppointmentJascIdExist(appointmentJascId))
            throw new IllegalArgumentException("\n\nThis appointment jasc id is not valid");

        try {
            return consultationRepository.save(new Consultation(date, time, detail, surgeryRepository.findByJascId(surgeryJascId), appointmentRepository.findByJascId(appointmentJascId)));
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis consultation was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create a consultation -> " + exp.getMessage());
        }
    }

    public Equipment createNewEquipment(String equipmentName, String equipmentUse, String equipmentDescription) throws Exception {

        try {
            return equipmentRepository.save(new Equipment(equipmentName, equipmentUse, equipmentDescription));
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis equipment was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create a equipment -> " + exp.getMessage());
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
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create an insurance -> " + exp.getMessage());
        }
    }

    public Meeting createNewMeeting(String title, String objective, Date date, Timestamp time, String place, Set<Staff> attendees) throws Exception {

        if (attendees.isEmpty())
            throw new NullArgumentException("\n\nYou can not schedule a meeting without any staff attending. Please choose who will attend");

        try {
            return meetingRepository.save(new Meeting(title,objective, date, time, place, attendees));
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis insurance was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create an insurance -> " + exp.getMessage());
        }
    }

    public Patient createNewPatient(String patientFirstName, String patientLastName, String patientIdCard, String patientTelephoneNumber, String patientContactTelephoneNumber, String patientGender, String patientEmail, Date patientBirthDate, String patientNationality, String patientAddress, String patientCity, String patientCountry) throws Exception {

        try {
            return patientRepository.save(new Patient(patientFirstName, patientLastName, patientIdCard, patientTelephoneNumber, patientContactTelephoneNumber, patientGender, patientEmail, patientBirthDate, patientNationality, patientAddress, patientCity, patientCountry));
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis patient was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
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
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
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
            throw new PersistenceException("\n\nThis user was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create a user -> " + exp.getMessage());
        }
    }

    // Elimination Functions
    public void deleteRegisteredAppointment(String jascId) throws Exception {

        if (!doesAppointmentJascIdExist(jascId))
            throw new IllegalArgumentException("\n\nThis appointment jasc id is not valid");

        try {
            appointmentRepository.delete(jascId);
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred while deleting an appointment -> " + exp.getMessage());
        }
    }

    public void deleteRegisteredEquipment(String jascId) throws Exception {

        if (!doesEquipmentJascIdExist(jascId))
            throw new IllegalArgumentException("\n\nThis equipment jasc id is not valid");

        try {
            equipmentRepository.delete(jascId);
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred while deleting an equipment -> " + exp.getMessage());
        }
    }

    public void deleteRegisteredInsurance(String jascId) throws Exception {

        if (!doesInsuranceJascIdExist(jascId))
            throw new IllegalArgumentException("\n\nThis insurance jasc Id is invalid,! Please try again.");

        try {
            insuranceRepository.delete(jascId);
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred while deleting an insurance -> " + exp.getMessage());
        }
    }

    public void deleteRegisteredMeeting(String jascId) throws Exception {

        if (!doesMeetingJascIdExist(jascId))
            throw new IllegalArgumentException("\n\nThis meeting jasc id is invalid");

        try {
            meetingRepository.delete(jascId);
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        }
    }

    public void deleteRegisteredStaff(String jascId) throws Exception {

        if (!doesStaffJascIdExist(jascId))
            throw new IllegalArgumentException("\n\nThis staff jasc id is invalid");

        try {
            staffRepository.delete(jascId);
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred while deleting a staff member -> " + exp.getMessage());
        }
    }

    public void deleteRegisterdUserAccount(String username) throws Exception {

        if (!isUsernameAlreadyTaken(username))
            throw new IllegalArgumentException("\n\nThis user account does not exist");

        if (username.equals("admin"))
            throw new IllegalArgumentException("\n\nDANGER: YOU CAN NOT ERASE ADMIN ACCOUNT!");

        try {
            userRepository.delete(username);
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred while deleting a user -> " + exp.getMessage());
        }
    }

    // Editing Functions
    public void editAppointment(Appointment appointment) throws Exception {

        if (appointment == null)
            throw new NullArgumentException("\n\nTHis appoint was sent with a value of NULL");

        try {
            appointmentRepository.save(appointment);
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis appointment was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to edit an appointment -> " + exp.getMessage());
        }
    }

    public void editEquipment(Equipment equipment) throws Exception {

        if (equipment == null)
            throw new NullArgumentException("\n\nThis equipment object is NULL");

        try {
            equipmentRepository.save(equipment);
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis equipment was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to edit an equipment -> " + exp.getMessage());
        }
    }

    public void editInsurance(Insurance insurance) throws Exception {

        if (insurance == null)
            throw new NullArgumentException("\n\nThis insurance has a NULL value");

        try {

        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis insurance was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to edit an insurance -> " + exp.getMessage());
        }
    }

    public void editMeeting(Meeting meeting) throws Exception {

        if (meeting == null)
            throw new NullArgumentException("\n\nthe meeting object was sent with a NULL value");

        try {

        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        }
    }

    public void editPatient(Patient patient) throws Exception {

        if (patient == null)
            throw new NullArgumentException("\n\nThe patient object is NULL");

        try {
            patientRepository.save(patient);
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis patient was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to edit a patient -> " + exp.getMessage());
        }
    }

    public void editUserAccountPassword(String username, String password) throws Exception{

        try {
            User user = userRepository.findByUsername(username);
            user.setPassword(password);
            userRepository.save(user);
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis username was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to edit username-> " + exp.getMessage());
        }
    }

    // Auxiliary Functions
    private boolean doesAppointmentJascIdExist(String jascId) {
        Appointment appointment = appointmentRepository.findByJascId(jascId);

        return (appointment != null);
    }

    private boolean doesEquipmentJascIdExist(String jascId){
        Equipment equipment = equipmentRepository.findByJascId(jascId);

        return (equipment != null);
    }

    private boolean doesInsuranceJascIdExist(String jascId){
        Insurance insurance = insuranceRepository.findByJascId(jascId);

        return (insurance != null);
    }

    private boolean doesMeetingJascIdExist(String jascId){
        Meeting meeting = meetingRepository.findByJascID(jascId);

        return (meeting != null);
    }

    private boolean doesPatientJascIdExist(String jascId){
        Patient patient = patientRepository.findByJascId(jascId);

        return (patient != null);
    }

    private boolean doesStaffJascIdExist(String jascId){
        Staff staff = staffRepository.findByJascId(jascId);

        return (staff != null);
    }

    private boolean doesSurguryJascIdExist(String jascId){
        Surgery surgery = surgeryRepository.findByJascId(jascId);

        return (surgery != null);
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
