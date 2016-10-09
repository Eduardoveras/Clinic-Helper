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

    public List<Appointment> findAllRegisteredAppointmentsByTimePeriod(Date beginnigOfTimePeriod, Date endOfTimePeriod){
        return appointmentRepository.findByDateRange(beginnigOfTimePeriod, endOfTimePeriod);
    }

    // Consultation Queries
    public Concultation findRegisteredConsultation(String jascId){ return consultationRepository.findByJascId(jascId)}

    public List<Consultation> findResgisteredConcultationByDate(Date searchDate){
        return consultationRepository.findConsultationByconsultationDate(searchDate);
    }
    // Equipment Queries

    // Insurance Queries

    // Meeting Queries

    // Patient Queries

    // Record Queries

    // Staff Queries

    // Surgery Queries

    // User Queries

    // Auxiliary Functions
    private boolean doesPatientJascIdExist(String jascId){
        Patient patient = patientRepository.findByJascId(jascId);

        return (patient != null);
    }
}
