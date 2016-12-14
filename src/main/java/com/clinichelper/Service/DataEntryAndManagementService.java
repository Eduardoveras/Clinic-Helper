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

































    // Editing Functions


    // Auxiliary Functions

    private boolean doesClinicIdExist(String clinicId){
        Clinic clinic = clinicRepository.findByClinicId(clinicId);

        return (clinic != null);
    }







    private boolean doesPatientIdExist(String patientId){
        Patient patient = patientRepository.findByPatientId(patientId);

        return (patient != null);
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
