/**
 * Created by Djidjelly Siclait on 12/14/2016.
 */
package com.clinichelper.Service.CRUD.Reading;

import com.clinichelper.Entity.Appointment;
import com.clinichelper.Entity.Consultation;
import com.clinichelper.Entity.Patient;
import com.clinichelper.Entity.Surgery;
import com.clinichelper.Repository.AppointmentRepository;
import com.clinichelper.Repository.ConsultationRepository;
import com.clinichelper.Repository.PatientRepository;
import com.clinichelper.Repository.SurgeryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class AppointmentConsultationSurgeryService {

    // Repositories
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private SurgeryRepository surgeryRepository;

    // Appointments
    public Appointment findRegisteredAppointment(String appointmentId){
        return appointmentRepository.findByAppointmentId(appointmentId);
    }

    public List<Appointment> findAllRegisteredAppointmentsForClinic(String clinicId){
        return appointmentRepository.findByClinicId(clinicId);
    }

    public List<Appointment> findPatientsRegisteredAppointments(String patientId){

        if (!doesPatientIdExist(patientId))
            throw new IllegalArgumentException("\n\nThis is an invalid patient Id");

        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> findAllRegisteredAppointmentsForToday(String clinicId) {
        try {
            return findAllRegisteredAppointmentsByGivenDate(new Date(Calendar.getInstance().getTime().getTime()), clinicId);
        } catch (Exception exp){
            exp.printStackTrace();
            return null;
        }
    }

    public List<Appointment> findAllRegisteredAppointmentsByGivenDate(Date searchDate, String clinicId) throws Exception{
        try{
            return appointmentRepository.findByDateRange(new Timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(searchDate.toString() + " 00:00:00").getTime()), new Timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(searchDate.toString() + " 23:59:00").getTime()), clinicId);
        } catch (Exception exp){
            exp.printStackTrace();
            throw new  Exception("Error during search for appointments of a specific date");
        }
    }

    // Consultation Queries
    public Consultation findRegisteredConsultation(String consultationId){ return consultationRepository.findByConsultationId(consultationId);}

    public List<Consultation> findAllRegisteredConsultationsForClinic(String clinicId){ return consultationRepository.findByClinicId(clinicId); }

    //public  List<Consultation> findAllRegisteredConsultationsByTimePeriod(String clinicId, Date beginningOfTimePeriod, Date endOfTimePeriod){ return consultationRepository.findByConsultationDateRange(beginningOfTimePeriod, endOfTimePeriod, clinicId); }

    // Surgery Queries
    public Surgery findRegisteredSurgery (String surgeryId){ return surgeryRepository.findBySurgeryId(surgeryId); }

    public List<Surgery> findAllRegisteredSurgeries(String clinicId){ return surgeryRepository.findByClinicId(clinicId); }

    public List<Surgery> findRegisteredSurgeryByName(String clinicId, String searchSurgeryName){ return surgeryRepository.findBySurgeryName(searchSurgeryName, clinicId); }

    public List<Surgery> findRegisteredSurgeryByPatient(String patientId){ return surgeryRepository.findByPatientId(patientId);}

    //public List<Surgery> findRegisteredSurgeryByTimePeriod(String clinicId, Date startDate, Date endDate){ return surgeryRepository.findByDateRange(startDate, endDate, clinicId);}

    public List<Surgery> findRegisteredSurgeryByRoom(String clinicId, String searchRoom){ return surgeryRepository.findBySurgeryRoom(searchRoom, clinicId); }

    //public List<Surgery> findResgisteredSurgeryByDateAndRoom(String clinicId, String searchRoom, Date searchDate){ return surgeryRepository.findBySurgeryDateAndSurgeryRoom(searchDate, searchRoom, clinicId); }


    // Auxiliary Functions
    private boolean doesPatientIdExist(String patientId){
        Patient patient = patientRepository.findByPatientId(patientId);

        return (patient != null);
    }
}
