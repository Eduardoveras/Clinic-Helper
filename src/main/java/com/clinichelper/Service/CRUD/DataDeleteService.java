/**
 * Created by Djidjelly Siclait on 12/14/2016.
 */
package com.clinichelper.Service.CRUD;

import com.clinichelper.Entity.*;
import com.clinichelper.Repository.*;
import com.clinichelper.Tools.Enums.AppointmentStatus;
import com.clinichelper.Tools.Enums.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DataDeleteService {

    // Repositories
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ChoreRepository choreRepository;
    @Autowired
    private ConsultationRepository consultationRepository;
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
    private ProductRepository productRepository;
    @Autowired
    private SurgeryRepository surgeryRepository;
    @Autowired
    private UserRepository userRepository;

    // Appointments
    public void deleteRegisteredAppointment(String appointmentId) throws Exception {

        if (!doesAppointmentIdExist(appointmentId))
            throw new IllegalArgumentException("\n\nThis appointment id is not valid");

        if (appointmentRepository.findByAppointmentId(appointmentId).getAppointmentStatus() != AppointmentStatus.PENDING)
            throw new IllegalArgumentException("\n\nNon-pending appointments cannot be eliminated");

        try {
            // Applying cascade to any existing Consultation
            Consultation consultation = consultationRepository.findByAppointmentId(appointmentId);
            if (consultation != null) {
                consultationRepository.delete(consultation);
            }

            // Applying cascade to any existing Surgery
            Surgery surgery = surgeryRepository.findByAppointmentId(appointmentId);
            if (surgery != null) {
                surgeryRepository.delete(surgery);
            }

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

    // Chore
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

    // Contacts
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

    // Equipments
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

    // Insurance
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

    // Medication
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

    // Meeting
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

    // Products
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

    // Users
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

    // Auxiliary Functions
    private boolean doesAppointmentIdExist(String appointmentId) {
        Appointment appointment = appointmentRepository.findByAppointmentId(appointmentId);

        return (appointment != null);
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

    private boolean doesProductIdExist(String productId){
        Product product = productRepository.findByProductId(productId);

        return (product != null);
    }

    private boolean doesStaffIdExist(String staffId){
        Contact contact = contactRepository.findByContactId(staffId);

        return (contact != null);
    }

    private boolean doesUserIdExist(String userId){
        User user = userRepository.findByUserId(userId);

        return (user != null);
    }
}
