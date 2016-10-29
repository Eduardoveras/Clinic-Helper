/**
 * Created by Djidjelly Siclait on 10/8/2016.
 */
package com.clinichelper.Service;

import com.clinichelper.Entity.*;
import com.clinichelper.Repository.*;
import com.clinichelper.Tools.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class DataQueryService {
    // Repositories
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ChoreRepository choreRepository;
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
    public Appointment findRegisteredAppointment(String jascId){
        return appointmentRepository.findByJascId(jascId);
    }

    public List<Appointment> findAllRegisteredAppointments(){
        return appointmentRepository.findAll();
    }

    public List<Appointment> findPatientsRegisteredAppointments(String patientJascId){

        if (!doesPatientJascIdExist(patientJascId))
            throw new IllegalArgumentException("\n\nThis is an invalid patient jascId");

        return appointmentRepository.findByPatientJascId(patientJascId);
    }

    public List<Appointment> findAllRegisteredAppointmentsByGivenDate(Date searchDate){ return appointmentRepository.findByDate(searchDate); }

    public List<Appointment> findAllRegisteredAppointmentsByTimePeriod(Date beginningOfTimePeriod, Date endOfTimePeriod){ return appointmentRepository.findByDateRange(beginningOfTimePeriod, endOfTimePeriod); }

    // Chores Queries
    public Chore findRegisteredCustomTask(String jascId){ return choreRepository.findByJascId(jascId); }

    public List<Chore> findAllRegisteredCustomTasks(){ return choreRepository.findAll(); }

    public List<Chore> findRegisteredCustomTaksByType(Task type) { return choreRepository.findByType(type); }


    // Consultation Queries
    public Consultation findRegisteredConsultation(String jascId){ return consultationRepository.findByJascId(jascId);}

    public List<Consultation> findAllRegisteredConsultations(){ return consultationRepository.findAll(); }

    public List<Consultation> findResgisteredConcultationByDate(Date searchDate){ return consultationRepository.findByConsultationDate(searchDate); }

    public  List<Consultation> findAllRegisteredConsultationsByTimePeriod(Date beginningOfTimePeriod, Date endOfTimePeriod){ return consultationRepository.findByConsultationDateRange(beginningOfTimePeriod, endOfTimePeriod); }


    // Equipment Queries
    public Equipment findRegisteredEquipment(String jascId) { return equipmentRepository.findByJascId(jascId); }

    public List<Equipment> findAllRegisteredEquipments(){ return equipmentRepository.findAll(); }

    public List<Equipment> findRegisteredEquipmentByName(String searchName){ return equipmentRepository.findByEquipmentName(searchName); }


    // Insurance Queries
    public Insurance findRegisteredInsurance(String jascId){ return insuranceRepository.findByJascId(jascId); }

    public List<Insurance> findAllRegisteredInsurances(){ return insuranceRepository.findAll(); }

    public List<Insurance> findRegisteredInsyranceByInsuranceSerialCode(String insuranceSerialCode){ return insuranceRepository.findByInsuranceSerialCode(insuranceSerialCode); }

    public List<Insurance> findRegisteredInsuranceByOwner(String searchID){ return insuranceRepository.findByOwnerJascId(searchID); }


    // Meeting Queries
    public Meeting findRegisteredMeeting(String jascId){ return meetingRepository.findByJascId(jascId);}

    public List<Meeting> findAllRegisteredMeetings(){ return meetingRepository.findAll(); }

    public List<Meeting> findRegisteredMeetingByTitle(String searchTitle){ return meetingRepository.findByMeetingTitle(searchTitle); }

    public List<Meeting> findRegisteredMeetingByDate (Date searchDate){ return meetingRepository.findByMeetingDate(searchDate); }

    public List<Meeting> findRegisteredMeetingByPlace (String searchPlace){ return meetingRepository.findByMeetingPlace(searchPlace); }


    // Patient Queries
    public Patient findRegisteredPatient(String jascId){ return patientRepository.findByJascId(jascId);}

    public List<Patient> findAllRegisteredPatients(){ return patientRepository.findAll(); }

    public Patient findRegisteredPatientByIdCard(String searchIdCard){ return patientRepository.findByPatientIdCard(searchIdCard); }

    public List<Patient> findRegisteredPatientByEmail(String patientEmail){ return patientRepository.findByPatientEmail(patientEmail); }

    public List<Patient> findRegisteredPatientByBirthDate(Date patientBirthDate){ return patientRepository.findByPatientBirthDate(patientBirthDate); }

    public List<Patient> findRegisteredPatientByFullName(String searchFistName, String searchLastName){ return patientRepository.findByFullName(searchFistName, searchLastName); }

    public List<Patient> findRegisteredPatientByGeneralInfomation(String searchFistName, String searchLastName,
                                                                  String searchTelephone,String searchEmail ){
        return patientRepository.findByFLTEFields(searchFistName,searchLastName,searchTelephone,searchEmail);
    }


    // Record Queries
    public Record findRegisteredRecord(String jascId){ return recordRepository.findByJascId(jascId); }

    public List<Record> findRegisteredRecords(){ return recordRepository.findAll(); }

    public List<Record> findPatientsRegisteredRecord(String patientJascId){

        if (!doesPatientJascIdExist(patientJascId))
            throw new IllegalArgumentException("\n\nThis is an invalid patient jascId");

        return recordRepository.findByPatientJascId(patientJascId);
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

    public List<Surgery> findRegisteredSurgeryByPatient(String patientJascId){ return surgeryRepository.findByPatientJascId(patientJascId);}

    public List<Surgery> findRegisteredSurgeryByDate(Date searchDate){ return surgeryRepository.findBySurgeryDate(searchDate); }

    public List<Surgery> findRegisteredSurgeryByTimePeriod(Date startDate, Date endDate){ return surgeryRepository.findyDateRange(startDate, endDate);}

    public List<Surgery> findRegisteredSurgeryByRoom(String searchRoom){ return surgeryRepository.findBySurgeryRoom(searchRoom); }

    public List<Surgery> findResgisteredSurgeryByDateAndRoom(String searchRoom, Date searchDate){ return surgeryRepository.findBySurgeryDateAndSurgeryRoom(searchDate,searchRoom); }


    // User Queries
    public User findRegisteredUserAccount(String username){ return userRepository.findByUsername(username); }

    public  List<User> findAllRegisteredUserAccounts(){ return userRepository.findAll(); }

    public User findRegisteredUserAccountOfRegisteredStaff(String staffJascId){ return userRepository.findByStaffJascId(staffJascId); }

    public boolean validateUserAccountCredentials(String username, String password) {

        User user = userRepository.findUserAccountWithUsernameAndPassword(username.toLowerCase(), password);

        return (user != null);
    }


    // Auxiliary Functions
    private boolean doesPatientJascIdExist(String jascId){
        Patient patient = patientRepository.findByJascId(jascId);

        return (patient != null);
    }
}
