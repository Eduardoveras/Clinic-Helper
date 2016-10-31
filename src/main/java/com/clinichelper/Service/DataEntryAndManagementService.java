/**
 * Created by Djidjelly Siclait on 10/8/2016.
 */
package com.clinichelper.Service;

import com.clinichelper.Entity.*;
import com.clinichelper.Repository.*;
import com.clinichelper.Tools.AppointmentType;
import com.clinichelper.Tools.Gender;
import com.clinichelper.Tools.Permission;
import com.clinichelper.Tools.Task;
import freemarker.template.utility.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Set;

@Service
public class DataEntryAndManagementService {
    // Auxiliary tools
    private final static long MILLISECONDS_IN_A_DAY = 24 * 60 * 60 * 1000;

    // Repositories
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ChoreRepository choreRepository;
    @Autowired
    private ClinicRepository clinicRepository;
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
    private RecordRepository recordRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private SurgeryRepository surgeryRepository;
    @Autowired
    private UserRepository userRepository;

    // Creation functions
    public Appointment createNewAppointment(String clinicId, Date appointmentDate, Timestamp appointmentTime, String patientJascId, String appointmentDescription, AppointmentType appointmentType) throws Exception {

        if(!doesClinicIdExist(clinicId))
            throw new IllegalArgumentException("\n\nThis is an invalid clinic id");

        if (isRequestedDateExpired(appointmentDate))
            throw new IllegalArgumentException("\n\nThe requested date has passed; it is no longer valid");

        if (!doesPatientJascIdExist(patientJascId))
            throw new IllegalArgumentException("\n\nThis is an invalid patient jascId");

        java.util.Date utilDate = new java.util.Date();

        if (differenceInDays(new Date(utilDate.getTime()), appointmentDate) <= 0)
            throw new IllegalArgumentException("The appointment date must be a future date");

        try {
            return appointmentRepository.save(new Appointment(clinicRepository.findByClinicId(clinicId), appointmentDate, appointmentTime, patientRepository.findByPatientId(patientJascId), appointmentDescription, appointmentType));
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

    public Chore createNewCustomTask(String clinicId, String title, Task type, String description) throws Exception{

        if (!doesClinicIdExist(clinicId))
            throw new IllegalArgumentException("\n\nThis is an invalid clinic id");

        try {
            return choreRepository.save(new Chore(clinicRepository.findByClinicId(clinicId), title, type, description));
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis consultation was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create a consultation -> " + exp.getMessage());
        }
    }

    public Consultation createNewConsultation(Date date, Timestamp time, String detail, String appointmentId) throws Exception{

        if (!doesAppointmentIdExist(appointmentId))
            throw new IllegalArgumentException("\n\nThis appointment id is not valid");

        try {
            return consultationRepository.save(new Consultation(date, time, detail, appointmentRepository.findByAppointmentId(appointmentId)));
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

    public Equipment createNewEquipment(String clinicId, String equipmentName, String equipmentUse, String equipmentDescription) throws Exception {

        if (!doesClinicIdExist(clinicId))
            throw new IllegalArgumentException("\n\nThis is an invalid clinic id");

        try {
            return equipmentRepository.save(new Equipment(clinicRepository.findByClinicId(clinicId), equipmentName, equipmentUse, equipmentDescription));
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
            return insuranceRepository.save(new Insurance(patientRepository.findByPatientId(ownerJascId), insuranceSerialCode, insurancePlan));
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

    public Meeting createNewMeeting(String clinicId, String title, String objective, Date date, Timestamp time, String place, Set<Contact> attendees) throws Exception {

        if (!doesClinicIdExist(clinicId))
            throw new IllegalArgumentException("\n\nThis is an invalid clinic id");

        if (attendees.isEmpty())
            throw new NullArgumentException("\n\nYou can not schedule a meeting without any staff attending. Please choose who will attend");

        if (differenceInDays(new Date(Calendar.getInstance().getTime().getTime()), date) <= 0)
            throw new IllegalArgumentException("The meeting date must be a future date");

        try {
            return meetingRepository.save(new Meeting(clinicRepository.findByClinicId(clinicId), title,objective, date, time, place, attendees));
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

    public Patient createNewPatient(String clinicId, String patientFirstName, String patientLastName, String patientIdCard,
                                    String patientTelephoneNumber, String patientContactTelephoneNumber,
                                    String occupation, String patientGender, String patientEmail,
                                    Date patientBirthDate, String patientNationality, String patientAddress,
                                    String patientCity, String patientCountry) throws Exception {

        if (!doesClinicIdExist(clinicId))
            throw new IllegalArgumentException("\n\nThis is an invalid clinic id");

        if (differenceInDays(patientBirthDate ,new Date(Calendar.getInstance().getTime().getTime())) <= 0)
            throw new IllegalArgumentException("The birth date must be a past date");

        try {
            return patientRepository.save(new Patient(clinicRepository.findByClinicId(clinicId), patientFirstName, patientLastName, patientIdCard,
                    patientTelephoneNumber, patientContactTelephoneNumber,
                    occupation, patientGender, patientEmail, patientBirthDate, patientNationality,
                    patientAddress, patientCity, patientCountry));
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

    public Record createNewRecord(String patientJascId, String recordDetails, Set<Surgery> surgeries, Set<Consultation> consultations) throws Exception {

        if (!doesPatientJascIdExist(patientJascId))
            throw new IllegalArgumentException("\n\nThis is an invalid patient jascId");

        try {
            return recordRepository.save(new Record(patientRepository.findByPatientId(patientJascId), recordDetails, surgeries, consultations));
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis record was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create a record -> " + exp.getMessage());
        }
    }

    public Contact createNewStaffMember(String clinicId, String staffFirstName, String staffLastName, Date staffBirthDate, String staffEmail) throws Exception {

        if (!doesClinicIdExist(clinicId))
            throw new IllegalArgumentException("\n\nThis is an invalid clinic id");

        try{
            return contactRepository.save(new Contact(clinicRepository.findByClinicId(clinicId), staffFirstName, staffLastName, staffBirthDate, staffEmail));
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis patient was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create a patient -> " + exp.getMessage());
        }
    }

    public Surgery createNewSurgery(String name, String description, String patientJascId, Date date, Timestamp time, String surgeryRoom, Set<Contact> contacts, Set<Equipment> equipments, String appointmentId) throws Exception {

        if (!doesPatientJascIdExist(patientJascId))
            throw new IllegalArgumentException("\n\nThis is an invalid patient jascId");

        if (!doesAppointmentIdExist(appointmentId))
            throw new IllegalArgumentException("\n\nThis appointment jasc id is not valid");

        if (contacts.isEmpty())
            throw new NullArgumentException("\n\nYou may not preform a surgery without staff. Please Choose at least one staff member.");

        try {
            return surgeryRepository.save(new Surgery(name, description, patientRepository.findByPatientId(patientJascId), date, time, surgeryRoom, contacts, equipments, appointmentRepository.findByAppointmentId(appointmentId)));
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis surgery was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create a surgery -> " + exp.getMessage());
        }
    }

    public User createNewUserAccount(String email, String firstName, String lastName, Date birthDate, Gender gender, String password, Permission role, String clinicId) throws Exception {

        if (isUsernameAlreadyTaken(email, clinicId))
            throw new IllegalArgumentException("\n\nThis email is already taken. Please choose another one!");

        try {
            return userRepository.save(new User(email, firstName, lastName, birthDate, gender, password, role, clinicRepository.findByClinicId(clinicId)));
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

        if (!doesAppointmentIdExist(jascId))
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

    public void deleteCustomTask(String jascId) throws Exception{

        if (!doesCustomTaskJascIdExist(jascId))
            throw new IllegalArgumentException("This Task jasc id is invalid");

        try{
            choreRepository.delete(jascId);
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred while deleting a custom task -> " + exp.getMessage());
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

        if (jascId.equals("JASC-STAFF-ADMIN"))
            throw new IllegalArgumentException("\n\nDANGER: YOU CAN NOT ERASE ADMIN ACCOUNT!");

        try {
            contactRepository.delete(jascId);
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred while deleting a staff member -> " + exp.getMessage());
        }
    }

    public void deleteRegisteredUserAccount(String userId) throws Exception {

        if (!doesUserIdExist(userId))
            throw new IllegalArgumentException("\n\nThis user account does not exist");

        if (userRepository.findByUserId(userId).getRole().equals(Permission.ADMIN))
            throw new IllegalArgumentException("\n\nDANGER: YOU CAN NOT ERASE ADMIN ACCOUNT!");

        try {
            userRepository.delete(userId);
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
            throw new NullArgumentException("\n\nThis appoint was sent with a value of NULL");

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

    public void editUserAccountCredentials(String email, String clinicId, String password, Permission role) throws Exception{

        try {
            User user = userRepository.findUserAccountWithUsernameAndClinicID(email, clinicId);
            user.setPassword(password);
            user.setRole(role);
            userRepository.save(user);
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis username was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to edit username-> " + exp.getMessage());
        }
    }

    public void editUserPhoto(String email, String clinic, Byte[] photo) throws Exception{

        try {
            User user = userRepository.findUserAccountWithUsernameAndClinicID(email, clinic);
            user.setPhoto(photo);
            userRepository.save(user);
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis username was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to edit username-> " + exp.getMessage());
        }
    }

    public void editStaff(Contact contact) throws Exception{

        try {
            contactRepository.save(contact);

        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis staff was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to edit a staff -> " + exp.getMessage());
        }
    }


    // Auxiliary Functions
    private boolean doesAppointmentIdExist(String appointmentId) {
        Appointment appointment = appointmentRepository.findByAppointmentId(appointmentId);

        return (appointment != null);
    }

    private boolean doesClinicIdExist(String clinicId){
        Clinic clinic = clinicRepository.findByClinicId(clinicId);

        return (clinic != null);
    }

    private boolean doesCustomTaskJascIdExist(String jascId){
        Chore chore = choreRepository.findByChoreId(jascId);

        return (chore != null);
    }

    private boolean doesEquipmentJascIdExist(String jascId){
        Equipment equipment = equipmentRepository.findByEquipmentId(jascId);

        return (equipment != null);
    }

    private boolean doesInsuranceJascIdExist(String jascId){
        Insurance insurance = insuranceRepository.findByInsuranceId(jascId);

        return (insurance != null);
    }

    private boolean doesMeetingJascIdExist(String jascId){
        Meeting meeting = meetingRepository.findByMeetingId(jascId);

        return (meeting != null);
    }

    private boolean doesPatientJascIdExist(String jascId){
        Patient patient = patientRepository.findByPatientId(jascId);

        return (patient != null);
    }

    private boolean doesStaffJascIdExist(String jascId){
        Contact contact = contactRepository.findByContactId(jascId);

        return (contact != null);
    }

    private boolean isUsernameAlreadyTaken(String email, String clinicId){
        User user = userRepository.findUserAccountWithUsernameAndClinicID(email, clinicId);

        return (user != null);
    }

    private boolean doesUserIdExist(String userId){
        User user = userRepository.findByUserId(userId);

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
