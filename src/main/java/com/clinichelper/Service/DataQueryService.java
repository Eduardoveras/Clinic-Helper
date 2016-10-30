/**
 * Created by Djidjelly Siclait on 10/8/2016.
 */
package com.clinichelper.Service;

import com.clinichelper.Entity.*;
import com.clinichelper.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class DataQueryService {
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
    private StaffRepository staffRepository;
    @Autowired
    private SurgeryRepository surgeryRepository;
    @Autowired
    private UserRepository userRepository;

    // Appointment Queries
    public Appointment findRegisteredAppointment(String appointmentId){
        return appointmentRepository.findByAppointmentId(appointmentId);
    }

    public List<Appointment> findAllRegisteredAppointmentsForClinic(String clinicId){
        return appointmentRepository.findByClinicId(clinicId);
    }

    public List<Appointment> findPatientsRegisteredAppointments(String patientJascId){

        if (!doesPatientJascIdExist(patientJascId))
            throw new IllegalArgumentException("\n\nThis is an invalid patient jascId");

        return appointmentRepository.findByPatientId(patientJascId);
    }

    public List<Appointment> findAllRegisteredAppointmentsForToday(String clinicId){ return findAllRegisteredAppointmentsByGivenDate(new Date(Calendar.getInstance().getTime().getTime()), clinicId); }

    public List<Appointment> findAllRegisteredAppointmentsByGivenDate(Date searchDate, String clinicId){ return appointmentRepository.findByDate(searchDate, clinicId); }

    public List<Appointment> findAllRegisteredAppointmentsByTimePeriod(String clinicId, Date beginningOfTimePeriod, Date endOfTimePeriod){ return appointmentRepository.findByDateRange(beginningOfTimePeriod, endOfTimePeriod, clinicId); }


    // Chores Queries
    public Chore findRegisteredCustomTask(String choreId){ return choreRepository.findByChoreId(choreId); }

    public List<Chore> findAllRegisteredCustomTasksForClinic(String clinicId){ return choreRepository.findByClinicId(clinicId); }


    // Clinic Queries
    public Clinic findRegisteredClinicByClinicId(String clinicId){ return clinicRepository.findByClinicId(clinicId); }


    // Consultation Queries
    public Consultation findRegisteredConsultation(String consultationId){ return consultationRepository.findByConsultationId(consultationId);}

    public List<Consultation> findAllRegisteredConsultationsForClinic(String clinicId){ return consultationRepository.findByClinicId(clinicId); }

    public List<Consultation> findResgisteredConcultationByDate(String clinicId, Date searchDate){ return consultationRepository.findByConsultationDate(searchDate, clinicId); }

    public  List<Consultation> findAllRegisteredConsultationsByTimePeriod(String clinicId, Date beginningOfTimePeriod, Date endOfTimePeriod){ return consultationRepository.findByConsultationDateRange(beginningOfTimePeriod, endOfTimePeriod, clinicId); }


    // Equipment Queries
    public Equipment findRegisteredEquipment(String equipmentId) { return equipmentRepository.findByEquipmentId(equipmentId); }

    public List<Equipment> findAllRegisteredEquipmentsForClinic(String clinicId){ return equipmentRepository.findByClinic(clinicId); }

    public List<Equipment> findRegisteredEquipmentByName(String clinicId, String searchName){ return equipmentRepository.findByEquipmentName(searchName, clinicId); }


    // Insurance Queries
    public Insurance findRegisteredInsurance(String insuranceId){ return insuranceRepository.findByInsuranceId(insuranceId); }

    public List<Insurance> findAllRegisteredInsurances(){ return insuranceRepository.findAll(); }

    public List<Insurance> findRegisteredInsyranceByInsuranceSerialCode(String insuranceSerialCode){ return insuranceRepository.findByInsuranceSerialCode(insuranceSerialCode); }

    public List<Insurance> findRegisteredInsuranceByOwner(String searchID){ return insuranceRepository.findByOwnerId(searchID); }


    // Meeting Queries
    public Meeting findRegisteredMeeting(String meetingId){ return meetingRepository.findByMeetingId(meetingId);}

    public List<Meeting> findAllRegisteredMeetingsForClinic(String clinicId){ return meetingRepository.findByClinicId(clinicId); }

    public List<Meeting> findRegisteredMeetingByTitle(String clinicId, String searchTitle){ return meetingRepository.findByMeetingTitle(searchTitle, clinicId); }

    public List<Meeting> findRegisteredMeetingByDate (String clinicId, Date searchDate){ return meetingRepository.findByMeetingDate(searchDate, clinicId); }

    public List<Meeting> findRegisteredMeetingByPlace (String clinicId, String searchPlace){ return meetingRepository.findByMeetingPlace(searchPlace, clinicId); }


    // Patient Queries
    public Patient findRegisteredPatient(String patientId){ return patientRepository.findByPatientId(patientId);}

    public List<Patient> findAllRegisteredPatientsForClinic(String clinicId){ return patientRepository.findByCLinicId(clinicId); }

    public Patient findRegisteredPatientByIdCard(String clinicId, String searchIdCard){ return patientRepository.findByPatientIdCard(searchIdCard, clinicId); }

    public List<Patient> findRegisteredPatientByEmail(String clinicId, String patientEmail){ return patientRepository.findByPatientEmail(patientEmail, clinicId); }

    public List<Patient> findRegisteredPatientByBirthDate(String clinicId, Date patientBirthDate){ return patientRepository.findByPatientBirthDate(patientBirthDate, clinicId); }

    public List<Patient> findRegisteredPatientByFullName(String clinicId, String searchFistName, String searchLastName){ return patientRepository.findByFullName(searchFistName, searchLastName, clinicId); }

    public List<Patient> findRegisteredPatientByGeneralInfomation(String clinicId, String searchFistName, String searchLastName,
                                                                  String searchTelephone,String searchEmail ){
        return patientRepository.findByFLTEFields(searchFistName,searchLastName,searchTelephone,searchEmail, clinicId);
    }


    // Record Queries
    public Record findRegisteredRecord(String jascId){ return recordRepository.findByJascId(jascId); }

    public List<Record> findRegisteredRecords(){ return recordRepository.findAll(); }

    public List<Record> findPatientsRegisteredRecord(String patientJascId){

        if (!doesPatientJascIdExist(patientJascId))
            throw new IllegalArgumentException("\n\nThis is an invalid patient jascId");

        return recordRepository.findByPatientId(patientJascId);
    }

    // Staff Queries
    public Staff findRegisteredStaff(String jascId){ return staffRepository.findByJascId(jascId); }

    public List<Staff> findAllRegisteredStaffs(){ return staffRepository.findAll(); }

    public Staff findRegisteredStaffByEmail(String staffEmail){ return staffRepository.findByStaffEmail(staffEmail); }

    public List<Staff> findRegisteredStaffByFirstNameAndLastName(String searchFirstName, String searchLastName){ return staffRepository.findByFirstNameAndLastName(searchFirstName,searchLastName); }


    // Surgery Queries
    public Surgery findRegisteredSurgery (String jascId){ return surgeryRepository.findByJascId(jascId); }

    public List<Surgery> findAllRegisteredSurgeries(){ return surgeryRepository.findAll(); }

    public List<Surgery> findRegisteredSurgeryByName(String searchSurgeryName){ return surgeryRepository.findBySurgeryName(searchSurgeryName); }

    public List<Surgery> findRegisteredSurgeryByPatient(String patientJascId){ return surgeryRepository.findByPatientId(patientJascId);}

    public List<Surgery> findRegisteredSurgeryByDate(Date searchDate){ return surgeryRepository.findBySurgeryDate(searchDate); }

    public List<Surgery> findRegisteredSurgeryByTimePeriod(Date startDate, Date endDate){ return surgeryRepository.findyDateRange(startDate, endDate);}

    public List<Surgery> findRegisteredSurgeryByRoom(String searchRoom){ return surgeryRepository.findBySurgeryRoom(searchRoom); }

    public List<Surgery> findResgisteredSurgeryByDateAndRoom(String searchRoom, Date searchDate){ return surgeryRepository.findBySurgeryDateAndSurgeryRoom(searchDate,searchRoom); }


    // User Queries
    public User findUserInformation(String userId) { return userRepository.findByUserId(userId); }

    public User findRegisteredUserAccount(String email, String clinicId){ return userRepository.findUserAccountWithUsernameAndClinicID(email, clinicId); }

    public List<User> findAllAllRegisteredUsersForClinic(String clinicId) { return userRepository.findByClinicId(clinicId); }

    public boolean validateUserAccountCredentials(String username, String clinicId, String password) {

        User user = userRepository.findUserAccountWithUsernameAndClinicIdAndPassword(username.toLowerCase(), clinicId, password);

        return (user != null);
    }


    // Auxiliary Functions
    private boolean doesPatientJascIdExist(String jascId){
        Patient patient = patientRepository.findByPatientId(jascId);

        return (patient != null);
    }
}
