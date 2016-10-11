/**
 * Created by Djidjelly Siclait on 10/8/2016.
 */
package com.clinichelper.Service;

import com.clinichelper.Entity.*;
import com.clinichelper.Repository.*;
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

    public List<Appointment> findPatientsRegisteredAppointments(String patientJascId){

        if (!doesPatientJascIdExist(patientJascId))
            throw new IllegalArgumentException("\n\nThis is an invalid patient jascId");

        return appointmentRepository.findByPatientJascId(patientJascId);
    }

    public List<Appointment> findAllRegisteredAppointments(){
        return appointmentRepository.findAll();
    }

    public List<Appointment> findAllRegisteredAppointmentsByGivenDate(Date searchDate){ return appointmentRepository.findByDate(searchDate); }

    public List<Appointment> findAllRegisteredAppointmentsByTimePeriod(Date beginningOfTimePeriod, Date endOfTimePeriod){ return appointmentRepository.findByDateRange(beginningOfTimePeriod, endOfTimePeriod); }




    // Consultation Queries
    public Consultation findRegisteredConsultation(String jascId){ return consultationRepository.findByJascId(jascId);}

    public List<Consultation> findResgisteredConcultationByDate(Date searchDate){ return consultationRepository.findByConsultationDate(searchDate); }

    public  List<Consultation> findAllRegisteredConsultationsByTimePeriod(Date beginningOfTimePeriod, Date endOfTimePeriod){ return consultationRepository.findByConsultationDateRange(beginningOfTimePeriod, endOfTimePeriod); }




    // Equipment Queries
    public Equipment findRegisteredEquipment(String jascId) { return equipmentRepository.findByJascId(jascId); }

    public List<Equipment> findRegisteredEquipmentByName(String searchName){ return equipmentRepository.findByEquipmentName(searchName); }





    // Insurance Queries
    public Insurance findRegisteredInsurance(String jascId){ return insuranceRepository.findByJascId(jascId); }

    public List<Insurance> findRegisteredInsyranceByInsuranceSerialCode(String insuranceSerialCode){ return insuranceRepository.findByInsuranceSerialCode(insuranceSerialCode); }

    public List<Insurance> findRegisteredInsuranceByOwner(String searchID){ return insuranceRepository.findByOwnerJascId(searchID); }




    // Meeting Queries
    public Meeting findRegisteredMeeting(String jascId){ return meetingRepository.findByJascId(jascId);}

    public List<Meeting> findRegisteredMeetingByTitle(String searchTitle){ return meetingRepository.findByMeetingTitle(searchTitle); }

    public List<Meeting> findRegisteredMeetingByDate (Date searchDate){ return meetingRepository.findByMeetingDate(searchDate); }

    public List<Meeting> findRegisteredMeetingByPlace (String searchPlace){ return meetingRepository.findByMeetingPlace(searchPlace); }

    // Patient Queries
    public Patient findRegisteredPatient(String jascId){ return patientRepository.findByJascId(jascId);}

    public Patient findRegisteredPatientByIdCard(String searchIdCard){
        return patientRepository.findPatientByIdentificationCard(searchIdCard);
    }

    public List<Patient> findRegisteredPatientByGeneralInfomation(String searchFistName, String searchLastName,
                                                                  String searchTelephone,String searchEmail ){
        return patientRepository.findRegisteredPatientByFLTEFields(searchFistName,searchLastName,searchTelephone,searchEmail);
    }
    // Record Queries
    public Record findRegisteredRecord(String jascId){return recordRepository.findByJascId(jascId);}

    public List<Record> findPatientsRegisteredRecord(String patientJascId){

        if (!doesPatientJascIdExist(patientJascId))
            throw new IllegalArgumentException("\n\nThis is an invalid patient jascId");

        return recordRepository.findRecordyBypatient(patientRepository.findByJascId(patientJascId));
    }
    // Staff Queries
    public Staff findRegisteredStaff(String jascId){ return staffRepository.findByJascId(jascId); }

    public List<Staff> findRegisteredStaffByFirstNameAndLastName(String searchFirstName, String searchLastName){
        return staffRepository.findByFirstNameAndLastName(searchFirstName,searchLastName);
    }


    // Surgery Queries
    public Surgery findRegisteredSurgery (String jascId){return surgeryRepository.findByJascId(jascId);}

    public List<Surgery> findRegisteredSurgeryByName(String searchSurgeryName){
        return surgeryRepository.findSurgeryBysurgeryName(searchSurgeryName);
    }
    public List<Surgery> findRegisteredSurgeryByDate(Date searchDate){
        return surgeryRepository.findSurgeryBysurgeryDate(searchDate);
    }

    public List<Surgery> findRegisteredSurgeryByRoom(String searchRoom){
        return surgeryRepository.findSurgeryBysurgeryRoom(searchRoom);
    }

    public List<Surgery> findResgisteredSurgeryByDateAndRoom(String searchRoom, Date searchDate){
        return surgeryRepository.findSurgeryBysurgeryDateAndsurgeryRoom(searchDate,searchRoom);
    }
    
    // User Queries

    // Auxiliary Functions
    private boolean doesPatientJascIdExist(String jascId){
        Patient patient = patientRepository.findByJascId(jascId);

        return (patient != null);
    }
}
