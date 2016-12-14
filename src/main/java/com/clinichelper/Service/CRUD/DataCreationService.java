/**
 * Created by Djidjelly Siclait on 12/14/2016.
 */
package com.clinichelper.Service.CRUD;

import com.clinichelper.Entity.*;
import com.clinichelper.Repository.*;
import com.clinichelper.Service.EncryptionService;
import com.clinichelper.Tools.Enums.*;
import freemarker.template.utility.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

@Service
public class DataCreationService {

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
    private ContactRepository contactRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private InsuranceRepository insuranceRepository;
    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private SurgeryRepository surgeryRepository;
    @Autowired
    private UserRepository userRepository;

    // Services
    @Autowired
    private EncryptionService encryptionService;

    // Functions
    // Appointments
    public Appointment createNewAppointment(String clinicId, Timestamp appointmentTime, String patientId, String appointmentDescription) throws Exception {

        if(!doesClinicIdExist(clinicId))
            throw new IllegalArgumentException("\n\nThis is an invalid clinic id");

        if (!doesPatientIdExist(patientId))
            throw new IllegalArgumentException("\n\nThis is an invalid patient Id");

        if (!isGivenDateInTheFuture(appointmentTime))
            throw new IllegalArgumentException("The date of an appointment must be a future date");

        try {
            Appointment appointment = appointmentRepository.save(new Appointment(clinicRepository.findByClinicId(clinicId), appointmentTime, patientRepository.findByPatientId(patientId), appointmentDescription, AppointmentType.CONSULTATION));
            // Consultation is automatically created with every appointment
            consultationRepository.save(new Consultation(appointmentTime, appointmentDescription, appointment));
            return appointment;
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            exp.printStackTrace();
            throw new PersistenceException("\n\nThis appointment was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            exp.printStackTrace();
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            exp.printStackTrace();
            throw new Exception("\n\nAn error occurred when trying to create an appointment -> " + exp.getMessage());
        }
    }

    // Chores
    public Chore createNewCustomTask(String userId, String title, Task type, String description, ArrayList<Repeat> reminders) throws Exception{

        if (!doesUserIdExist(userId))
            throw new IllegalArgumentException("\n\nThis is an invalid user id");

        try {
            return choreRepository.save(new Chore(userRepository.findByUserId(userId), title, type, description, reminders));
        } catch (PersistenceException exp){
            exp.printStackTrace();
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis consultation was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            exp.printStackTrace();
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create a consultation -> " + exp.getMessage());
        }
    }

    // Clinics
    public Clinic createNewClinic(String clinicName, String clinicPrefix, AccountType accountType) throws Exception{

        if (isClinicNameTaken(clinicName))
            throw new IllegalArgumentException("Clinic name is already reserved!");

        try {
            return clinicRepository.save(new Clinic(clinicName, clinicPrefix, accountType));
        } catch (PersistenceException exp){
            exp.printStackTrace();
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis clinic was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            exp.printStackTrace();
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create a clinic -> " + exp.getMessage());
        }
    }

    // Contacts
    public Contact createNewStaffMember(String clinicId, String staffFirstName, String staffLastName, Date staffBirthDate, String staffEmail) throws Exception {

        if (!doesClinicIdExist(clinicId))
            throw new IllegalArgumentException("\n\nThis is an invalid clinic id");

        try{
            return contactRepository.save(new Contact(clinicRepository.findByClinicId(clinicId), staffFirstName, staffLastName, staffBirthDate, staffEmail.toLowerCase(), false));
        } catch (PersistenceException exp){
            exp.printStackTrace();
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis patient was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            exp.printStackTrace();
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create a patient -> " + exp.getMessage());
        }
    }

    // Equipments
    public Equipment createNewEquipment(String clinicId, String equipmentName, String equipmentUse, String equipmentDescription, Integer stock) throws Exception {

        if (!doesClinicIdExist(clinicId))
            throw new IllegalArgumentException("\n\nThis is an invalid clinic id");

        try {
            return equipmentRepository.save(new Equipment(clinicRepository.findByClinicId(clinicId), equipmentName, equipmentUse, equipmentDescription, stock));
        } catch (PersistenceException exp){
            exp.printStackTrace();
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis equipment was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            exp.printStackTrace();
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create a equipment -> " + exp.getMessage());
        }
    }

    // History
    public History createNewHistory(Patient patient, String visitObjective, String observations, String specialConditions, ArrayList<byte[]> photos, SurgeryType surgeryType, ArrayList<String> medicalData, String consultationId) throws Exception {

        if (!doesPatientIdExist(patient.getPatientId()))
            throw new IllegalArgumentException("\n\nThis is an invalid patient id");

        if (!doesConsultationIdExist(consultationId))
            throw new IllegalArgumentException("This is not a valid consultation id");

        try {
            return historyRepository.save(new History(patient, visitObjective,  observations, specialConditions, photos, surgeryType,  medicalData, consultationId));
        } catch (PersistenceException exp){
            exp.printStackTrace();
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis record was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            exp.printStackTrace();
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create a record -> " + exp.getMessage());
        }
    }

    // Insurance
    public Insurance createNewInsurance(String ownerId, String insuranceSerialCode, String supplier, String insurancePlan) throws Exception{

        if (!doesPatientIdExist(ownerId))
            throw new IllegalArgumentException("\n\nThis is an invalid patient id");

        try {
            return insuranceRepository.save(new Insurance(patientRepository.findByPatientId(ownerId), insuranceSerialCode, supplier, insurancePlan));
        } catch (PersistenceException exp){
            exp.printStackTrace();
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis insurance was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            exp.printStackTrace();
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create an insurance -> " + exp.getMessage());
        }
    }

    // Medication
    public Medication createNewMedication(String clinicId, String medicationName, String supplier, String medicationDescription, Float medicationPrice, Integer stock) throws Exception{

        if (!doesClinicIdExist(clinicId))
            throw new IllegalArgumentException("\n\nThis is an invalid clinic id");

        if (medicationPrice < 0.00f)
            throw new IllegalArgumentException("\n\nThe price must be a positive number bigger than 0.00");

        if (stock < 1)
            throw new IllegalArgumentException("\n\nYou cannot register ZERO or less medication");

        try {
            return medicationRepository.save(new Medication(clinicRepository.findByClinicId(clinicId), medicationName, supplier, medicationDescription, medicationPrice, stock));
        } catch (PersistenceException exp){
            exp.printStackTrace();
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis medication was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            exp.printStackTrace();
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create an medication -> " + exp.getMessage());
        }
    }

    // Meetings
    public Meeting createNewMeeting(String clinicId, String title, String objective, Timestamp time, String place, Set<Contact> attendees) throws Exception {

        if (!doesClinicIdExist(clinicId))
            throw new IllegalArgumentException("\n\nThis is an invalid clinic id");

        if (attendees.isEmpty())
            throw new NullArgumentException("\n\nYou can not schedule a meeting without any staff attending. Please choose who will attend");

        if (!isGivenDateInTheFuture(time))
            throw new IllegalArgumentException("The meeting date must be a future date");

        try {
            return meetingRepository.save(new Meeting(clinicRepository.findByClinicId(clinicId), title, objective, time, place, attendees));
        } catch (PersistenceException exp){
            exp.printStackTrace();
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis insurance was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create an insurance -> " + exp.getMessage());
        }
    }

    // Patients
    public Patient createNewPatient(String clinicId, String patientFirstName, String patientLastName, String patientIdCard,
                                    String patientTelephoneNumber, String patientWorkphone ,String patientCellphone,
                                    String patientContactName, String patientContactLastName, String patientContactAddress,
                                    String patientContactCellphone, String patientContactTelephoneNumber,String occupation,
                                    Gender patientGender, String patientEmail, Date patientBirthDate, String patientNationality,
                                    String patientAddress, String patientCity, String patientCountry, ArrayList<String> patientAllergies,
                                    String patientReligion, String PatientHeight, String PatientWeight, String patientBloodType,
                                    ArrayList<String> patientConditions, String insuranceSerialCode, String insuranceSupplier,
                                    String insurancePlan) throws Exception {

        if (!doesClinicIdExist(clinicId))
            throw new IllegalArgumentException("\n\nThis is an invalid clinic id");

        if (differenceInDays(patientBirthDate, new Date(Calendar.getInstance().getTime().getTime())) <= 0)
            throw new IllegalArgumentException("The birth date must be a past date");

        try {
            Patient patient = patientRepository.save(new Patient(clinicRepository.findByClinicId(clinicId), patientFirstName, patientLastName, patientIdCard,
                    patientTelephoneNumber, patientWorkphone,patientCellphone, patientContactName, patientContactLastName, patientContactAddress, patientContactCellphone,
                    patientContactTelephoneNumber, occupation, patientGender, patientEmail.toLowerCase(), patientBirthDate, patientNationality,
                    patientAddress, patientCity, patientCountry, patientAllergies, patientReligion, PatientHeight, PatientWeight,
                    patientBloodType, patientConditions));

            // The patient's medical record is created automatically
            if (patient.getClinic().getAccountType() != AccountType.BASIC)
                recordRepository.save(new Record(patient));

            // Adding insurance information if exist
            if (insuranceSerialCode != null)
                insuranceRepository.save(new Insurance(patient, insuranceSerialCode, insuranceSupplier, insurancePlan));

            return patient;
        } catch (PersistenceException exp){
            exp.printStackTrace();
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis patient was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create a patient -> " + exp.getMessage());
        }
    }

    // Products
    public Product createNewProduct(String clinicId, String productName, String supplier, String productDescription, Float productPrice, Integer stock) throws Exception{
        if (!doesClinicIdExist(clinicId))
            throw new IllegalArgumentException("\n\n\nThis clinic id is not valid");

        if (productPrice < 0.00f)
            throw new IllegalArgumentException("\n\nThe price must be a positive number bigger than 0.00");

        if (stock < 1)
            throw new IllegalArgumentException("\n\nYou cannot register ZERO or less medication");

        try {
            return productRepository.save(new Product(clinicRepository.findByClinicId(clinicId), productName, supplier, productDescription, productPrice, stock));
        } catch (PersistenceException exp){
            exp.printStackTrace();
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis product was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            exp.printStackTrace();
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create a product -> " + exp.getMessage());
        }
    }

    // Surgeries
    public Surgery createNewSurgery(String name, String description, String patientId, Timestamp time) throws Exception {

        if (!doesPatientIdExist(patientId))
            throw new IllegalArgumentException("\n\nThis is an invalid patient id");

        try {
            Appointment appointment = appointmentRepository.save(new Appointment( patientRepository.findByPatientId(patientId).getClinic(), time, patientRepository.findByPatientId(patientId), description, AppointmentType.SURGERY));
            return surgeryRepository.save(new Surgery(name, description, patientRepository.findByPatientId(patientId), time, appointment));
        } catch (PersistenceException exp){
            exp.printStackTrace();
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis surgery was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            exp.printStackTrace();
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create a surgery -> " + exp.getMessage());
        }
    }

    // Users
    public User createNewUserAccount(String clinicId, String email, String firstName, String lastName, Date birthDate, Gender gender, String password, Permission role) throws Exception {

        if (isEmailAlreadyTaken(email.toLowerCase(), clinicId))
            throw new IllegalArgumentException("\n\nThis email is already taken. Please choose another one!");

        try {
            // Add new user automatically in contact list
            contactRepository.save(new Contact(clinicRepository.findByClinicId(clinicId), firstName, lastName, birthDate, email.toLowerCase(), true));
            return userRepository.save(new User(clinicRepository.findByClinicId(clinicId), email.toLowerCase(), firstName, lastName, birthDate, gender, encryptionService.encryptPassword(password), role));
        } catch (PersistenceException exp){
            exp.printStackTrace();
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis user was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            exp.printStackTrace();
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to create a user -> " + exp.getMessage());
        }
    }

    // Auxiliary Functions
    private int differenceInDays(Date start, Date end){
        return (int)((end.getTime() - start.getTime()) / MILLISECONDS_IN_A_DAY);
    }

    private boolean doesClinicIdExist(String clinicId) {
        Clinic clinic = clinicRepository.findByClinicId(clinicId);

        return (clinic != null);
    }

    private boolean doesConsultationIdExist(String consultationId){
        Consultation consultation = consultationRepository.findByConsultationId(consultationId);

        return (consultation != null);
    }

    private boolean doesPatientIdExist(String patientId){
        Patient patient = patientRepository.findByPatientId(patientId);

        return (patient != null);
    }

    private boolean doesUserIdExist(String userId){
        User user = userRepository.findByUserId(userId);

        return (user != null);
    }

    private boolean isClinicNameTaken(String clinicName){
        Clinic clinic = clinicRepository.findByClinicName(clinicName);

        return (clinic != null);
    }

    private boolean isEmailAlreadyTaken(String email, String clinicId){
        User user = userRepository.findUserAccountWithUsernameAndPassword(email.toLowerCase(), clinicId);

        return (user != null);
    }

    private boolean isGivenDateInTheFuture(Timestamp date){
        Calendar today = Calendar.getInstance();
        today.setTime(new Date(Calendar.getInstance().getTime().getTime()));

        Calendar givenDate = Calendar.getInstance();
        givenDate.setTime(new Date(date.getTime()));

        if ((givenDate.get(Calendar.YEAR) - today.get(Calendar.YEAR)) < 0)
            return false;

        if ((givenDate.get(Calendar.MONTH) - today.get(Calendar.MONTH)) < 0)
            return false;

        if ((givenDate.get(Calendar.DAY_OF_MONTH) - today.get(Calendar.DAY_OF_MONTH)) < 0)
            return false;

        return true;
    }
}
