/**
 * Created by Djidjelly Siclait on 10/8/2016.
 */
package com.clinichelper.Service;

import com.clinichelper.Entity.*;
import com.clinichelper.Repository.*;
import com.clinichelper.Tools.Enums.*;
import freemarker.template.utility.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

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
    private ContactRepository contactRepository;
    @Autowired
    private SurgeryRepository surgeryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  HistoryRepository historyRepository;
    @Autowired
    private EncryptionService EncriptService;

    // Creation functions












































    // Elimination Functions
    public void deleteRegisteredAppointment(String appointmentId) throws Exception {

        if (!doesAppointmentIdExist(appointmentId))
            throw new IllegalArgumentException("\n\nThis appointment jasc id is not valid");

        try {
            // Fetching patients records
            //Record record = recordRepository.findByPatientId(appointmentRepository.findByAppointmentId(appointmentId).getPatient().getPatientId());

            // Applying cascade to any existing Consultation
            Consultation consultation = consultationRepository.findByAppointmentId(appointmentId);
            if (consultation != null) {
                //Set<Consultation> consultations = record.getConsultations();
                //consultations.remove(consultation);
                //record.setConsultations(consultations);
                consultationRepository.delete(consultation);
            }

            // Applying cascade to any existing Surgery
            Surgery surgery = surgeryRepository.findByAppointmentId(appointmentId);
            if (surgery != null) {
                //Set<Surgery> surgeries = record.getSurgeries();
                //surgeries.remove(surgery);
                //record.setSurgeries(surgeries);
                surgeryRepository.delete(surgery);
            }

            //recordRepository.save(record);

            appointmentRepository.delete(appointmentId);
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred while deleting an appointment -> " + exp.getMessage());
        }
    }




    public void deleteCustomTask(String taskId) throws Exception{

        if (!doesCustomTaskIdExist(taskId))
            throw new IllegalArgumentException("This Task jasc id is invalid");

        try{
            choreRepository.delete(taskId);
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred while deleting a custom task -> " + exp.getMessage());
        }
    }




    public void deleteRegisteredEquipment(String equipmentId) throws Exception {

        if (!doesEquipmentIdExist(equipmentId))
            throw new IllegalArgumentException("\n\nThis equipment jasc id is not valid");

        try {
            equipmentRepository.delete(equipmentId);
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred while deleting an equipment -> " + exp.getMessage());
        }
    }




    public void deleteRegisteredInsurance(String insuranceId) throws Exception {

        if (!doesInsuranceIdExist(insuranceId))
            throw new IllegalArgumentException("\n\nThis insurance jasc Id is invalid,! Please try again.");

        try {
            insuranceRepository.delete(insuranceId);
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred while deleting an insurance -> " + exp.getMessage());
        }
    }




    public void deleteRegisteredMedication(String medicationId) throws Exception{
        if (!doesMedicationIdExist(medicationId))
            throw new IllegalArgumentException("\n\nThis is an invalid medication id");

        try {
            medicationRepository.delete(medicationId);
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred while deleting a medication -> " + exp.getMessage());
        }
    }




    public void deleteRegisteredMeeting(String meetingId) throws Exception {

        if (!doesMeetingIdExist(meetingId))
            throw new IllegalArgumentException("\n\nThis meeting id is invalid");

        try {
            meetingRepository.delete(meetingId);
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred while deleting a meeting -> " + exp.getMessage());
        }
    }




    public void deleteRegisteredStaff(String staffId) throws Exception {

        if (!doesStaffIdExist(staffId))
            throw new IllegalArgumentException("\n\nThis staff id is not valid");

        if (staffId.equals("JASC-STAFF-ADMIN"))
            throw new IllegalArgumentException("\n\nDANGER: YOU CAN NOT ERASE ADMIN ACCOUNT!");

        try {
            Contact target = contactRepository.findByContactId(staffId);

            // Applying cascade to meetings
            for (Meeting m:
                 meetingRepository.findByClinicId(target.getClinic().getClinicId())) {

                Set<Contact> attendees = m.getAttendees();

                attendees.remove(target);
            }

            // TODO: Apply Cascade to Surgeries

            contactRepository.delete(target);
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred while deleting a staff member -> " + exp.getMessage());
        }
    }



    public void deleteRegisteredProduct(String productId) throws Exception{
        if (!doesProductIdExist(productId))
            throw new IllegalArgumentException("\n\nThis is an invalid productId");

        try {
            productRepository.delete(productId);
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAn object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred while deleting a product -> " + exp.getMessage());
        }
    }



    public void deleteRegisteredUserAccount(String userId) throws Exception {

        if (!doesUserIdExist(userId))
            throw new IllegalArgumentException("\n\nThis user account does not exist");

        if (userRepository.findByUserId(userId).getRole().equals(Permission.ADMIN))
            throw new IllegalArgumentException("\n\nDANGER: YOU CAN NOT ERASE ADMIN ACCOUNT!");

        try {
            List<Chore> chores = choreRepository.findByUserId(userId);

            for (Chore c:
                 chores) {
                choreRepository.delete(c); // Erasing any chore data created by the user
            }

            userRepository.delete(userId);
        } catch (NullPointerException exp) {
            System.out.println("\n\nNull Pointer Error! -> " + exp.getMessage());
            throw new NullPointerException("\n\nAN object or process has risen a null value -> " + exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
            System.out.println("\n\nGeneral Error! -> " + exp.getMessage());
            throw new Exception("\n\nAn error occurred while deleting a user -> " + exp.getMessage());
        }
    }




    // Editing Functions






























    // Auxiliary Functions
    private boolean doesAppointmentIdExist(String appointmentId) {
        Appointment appointment = appointmentRepository.findByAppointmentId(appointmentId);

        return (appointment != null);
    }

    private boolean doesClinicIdExist(String clinicId){
        Clinic clinic = clinicRepository.findByClinicId(clinicId);

        return (clinic != null);
    }


    private boolean doesCustomTaskIdExist(String taskId){
        Chore chore = choreRepository.findByChoreId(taskId);

        return (chore != null);
    }

    private boolean doesEquipmentIdExist(String equipmentId){
        Equipment equipment = equipmentRepository.findByEquipmentId(equipmentId);

        return (equipment != null);
    }

    private boolean doesInsuranceIdExist(String insuranceId){
        Insurance insurance = insuranceRepository.findByInsuranceId(insuranceId);

        return (insurance != null);
    }

    private boolean doesMedicationIdExist(String medicationId){
        Medication medication = medicationRepository.findByMedicationId(medicationId);

        return (medication != null);
    }

    private boolean doesMeetingIdExist(String meetingId){
        Meeting meeting = meetingRepository.findByMeetingId(meetingId);

        return (meeting != null);
    }

    private boolean doesPatientIdExist(String patientId){
        Patient patient = patientRepository.findByPatientId(patientId);

        return (patient != null);
    }

    private boolean doesStaffIdExist(String staffId){
        Contact contact = contactRepository.findByContactId(staffId);

        return (contact != null);
    }

    private boolean doesProductIdExist(String productId){
        Product product = productRepository.findByProductId(productId);

        return (product != null);
    }


    private boolean doesUserIdExist(String userId){
        User user = userRepository.findByUserId(userId);

        return (user != null);
    }

    private boolean isRequestedDateExpired(Date requestedDate){
        Date today = new Date(Calendar.getInstance().getTime().getTime()); // getting today's date;
        return !(differenceInDays(today, requestedDate) >= 0); // checking if requested date is valid
    }

    private int differenceInDays(Date start, Date end){
        return (int)((end.getTime() - start.getTime()) / MILLISECONDS_IN_A_DAY);
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
