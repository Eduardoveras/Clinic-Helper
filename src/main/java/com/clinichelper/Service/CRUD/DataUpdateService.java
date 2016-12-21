/**
 * Created by Djidjelly Siclait on 12/14/2016.
 */
package com.clinichelper.Service.CRUD;

import com.clinichelper.Entity.*;
import com.clinichelper.Repository.*;
import com.clinichelper.Service.Security.EncryptionService;
import com.clinichelper.Tools.Enums.Permission;
import freemarker.template.utility.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;

@Service
public class DataUpdateService {

    // Repositories
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ChoreRepository choreRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
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
    private UserRepository userRepository;

    // Services
    @Autowired
    private EncryptionService encryptionService;

    // Appointments
    public void editAppointment(Appointment appointment) throws Exception {

        if (appointment == null)
            throw new NullArgumentException("\n\nThis appoint was sent with a value of NULL");

        try {
            appointmentRepository.save(appointment);
        } catch (PersistenceException exp){
            exp.printStackTrace();
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis appointment was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            exp.printStackTrace();
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to edit an appointment -> " + exp.getMessage());
        }
    }

    // Chores
    public void editCustomTask(Chore chore) throws Exception{

        if (chore == null)
            throw new NullArgumentException("This chore is null");

        try {
            choreRepository.save(chore);
        } catch (PersistenceException exp){
            exp.printStackTrace();
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis chore was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            exp.printStackTrace();
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to edit a chore -> " + exp.getMessage());
        }
    }

    // Contacts
    public void editStaffMember(Contact contact) throws Exception{

        if (contact == null)
            throw new NullArgumentException("This contact is invalid");

        try {
            contactRepository.save(contact);
        } catch (PersistenceException exp){
            exp.printStackTrace();
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis staff was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            exp.printStackTrace();
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to edit a staff -> " + exp.getMessage());
        }
    }

    // Equipments
    public void editEquipment(Equipment equipment) throws Exception {

        if (equipment == null)
            throw new NullArgumentException("\n\nThis equipment object is NULL");

        try {
            equipmentRepository.save(equipment);
        } catch (PersistenceException exp){
            exp.printStackTrace();
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis equipment was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            exp.printStackTrace();
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to edit an equipment -> " + exp.getMessage());
        }
    }

    // Insurance
    public void editInsurance(Insurance insurance) throws Exception {

        if (insurance == null)
            throw new NullArgumentException("\n\nThis insurance has a NULL value");

        try {
            insuranceRepository.save(insurance);
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
            throw new Exception("\n\nAn error occurred when trying to edit an insurance -> " + exp.getMessage());
        }
    }

    // Medication
    public void editMedication(Medication medication) throws Exception{

        if (medication == null)
            throw new NullArgumentException("\n\nThis medication has a NULL value");

        try {
            medicationRepository.save(medication);
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
            throw new Exception("\n\nAn error occurred when trying to edit a medication -> " + exp.getMessage());
        }
    }

    // Meeting
    public void editMeeting(Meeting meeting) throws Exception {

        if (meeting == null)
            throw new NullArgumentException("\n\nthe meeting object was sent with a NULL value");

        try {
            meetingRepository.save(meeting);
        } catch (PersistenceException exp){
            exp.printStackTrace();
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis meeting was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            exp.printStackTrace();
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to edit a meeting -> " + exp.getMessage());
        }
    }

    // Patient
    public void editPatient(Patient patient) throws Exception {

        if (patient == null)
            throw new NullArgumentException("\n\nThe patient object is NULL");

        try {
            patientRepository.save(patient);
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
            throw new Exception("\n\nAn error occurred when trying to edit a patient -> " + exp.getMessage());
        }
    }

    // Product
    public void editProduct(Product product) throws Exception{

        if (product == null)
            throw new NullArgumentException("\n\nThis product has a NULL value");

        try {
            productRepository.save(product);
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
            throw new Exception("\n\nAn error occurred when trying to edit a product -> " + exp.getMessage());
        }
    }

    // Record
    public void editRecord(Record record) throws Exception{
        if (record == null)
            throw new NullArgumentException("\n\nThis record has a NULL value");

        try {
            recordRepository.save(record);
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
            throw new Exception("\n\nAn error occurred when trying to edit a record -> " + exp.getMessage());
        }
    }

    // Users
    public void editUserAccountCredentials(String email, String clinicId, String password, Permission role) throws Exception{

        try {
            User user = userRepository.findUserAccountWithUsernameAndPassword(email.toLowerCase(), clinicId);
            user.setPassword(encryptionService.encryptPassword(password));
            user.setRole(role);
            userRepository.save(user);
        } catch (PersistenceException exp){
            exp.printStackTrace();
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis username was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            exp.printStackTrace();
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to edit username-> " + exp.getMessage());
        }
    }

    public void editUserPhoto(String email, String clinic, Byte[] photo) throws Exception{

        try {
            User user = userRepository.findUserAccountWithUsernameAndPassword(email.toLowerCase(), clinic);
            user.setPhoto(photo);
            userRepository.save(user);
        } catch (PersistenceException exp){
            exp.printStackTrace();
            System.out.println("\n\nPersistence Error! -> " + exp.getMessage());
            throw new PersistenceException("\n\nThis username was not able to persist -> " + exp.getMessage());
        } catch (NullPointerException exp) {
            exp.printStackTrace();
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred when trying to edit username-> " + exp.getMessage());
        }
    }
}
