/**
 * Created by Djidjelly Siclait on 10/8/2016.
 */
package com.clinichelper.Service;

import com.clinichelper.Entity.Appointment;
import com.clinichelper.Entity.Patient;
import com.clinichelper.Repository.AppointmentRepository;
import com.clinichelper.Repository.PatientRepository;
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
    private PatientRepository patientRepository;

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

    public List<Appointment> findAllRegisteredAppointmentsByGivenDate(Date searchDate){
        return appointmentRepository.findByDate(searchDate);
    }

    public List<Appointment> findAllRegisteredAppointmentsByTimePeriod(Date beginningOfTimePeriod, Date endOfTimePeriod){
        return appointmentRepository.findByDateRange(beginningOfTimePeriod, endOfTimePeriod);
    }

    // Consultation Queries
    public Concultation findRegisteredConsultation(String jascId){ return consultationRepository.findByJascId(jascId);}

    public List<Consultation> findResgisteredConcultationByDate(Date searchDate){
        return consultationRepository.findConsultationByconsultationDate(searchDate);
    }
    // Equipment Queries
    public Equipment findRegisteredEquipment(String jascId) { return equipmentRepository.findByJascId(jascId); }

    public List<Equipment> findRegisteredEquipmentByName(String searchName){
        return equipmentRepository.findEquipmentByequipmentName(searchName);
    }
    // Insurance Queries
    public Insurance findRegisteredInsurance(String jascId){ return insuranceRepository.findByJascId(jascId); }

    public List<Insurance> findRegisteredInsuranceByOwner(String searchID){
        return insuranceRepository.findRegisteredPatientsInsuranceInformation(searchID);
    }
    // Meeting Queries
    public Meeting findRegisteredMeeting(String jascId){ return meetingRepository.findByJascId(jascId);}

    public List<Meeting> findRegisteredMeetingByTitle(String searchTitle){
        return meetingRepository.findMeetingBymeetingTitle(searchTitle);
    }

    public List<Meeting> findRegisteredMeetingByDate (Date searchDate){
        return meetingRepository.findmeetingBymeetingDate(searchDate);
    }

    public List<Meeting> findRegisteredMeetingByPlace (String searchPlace){
        return meetingRepository.findmeetingBymeetingPlace(serachPlace);
    }
    // Patient Queries
    public Patient findRegisteredPatient(String jsacId){ return patientRepository.findByJascId(jascId);}

    public List<Patient> findRegisteredPatientByIdCard(String searchIdCard){
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

        return recordRepository.findRecordyBypatient(patientJascId);
    }
    // Staff Queries
    public Staff findRegisteredStaff(String jascId){ return staffRepository.findByJascId(jascId); }

    public List<Staff> findRegisteredStaffByFirstNameAndLastName(String searchFirstName, String searchLastName){
        return staffRepository.findByFirstNameAndLastName(searchFirstName,searchLastName);
    }


    // Surgery Queries

    // User Queries

    // Auxiliary Functions
    private boolean doesPatientJascIdExist(String jascId){
        Patient patient = patientRepository.findByJascId(jascId);

        return (patient != null);
    }
}
