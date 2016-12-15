/**
 * Created by Djidjelly Siclait on 10/8/2016.
 */
package com.clinichelper.Service;

import com.clinichelper.Entity.*;
import com.clinichelper.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
    private HttpSession session;
    @Autowired
    private EncryptionService EncriptService;



    public Object getSessionAttr(String name)
    {
        return session.getAttribute(name);
    }

    public void setSessionAttr(String name,Object obj)
    {
        session.setAttribute(name,obj);
    }

    // Appointment Queries


    // Chores Queries
    //public Chore findRegisteredCustomTask(String choreId){ return choreRepository.findByChoreId(choreId); }

    //public List<Chore> findAllRegisteredCustomTasksForClinic(String clinicId){ return choreRepository.findByClinicId(clinicId); }



    // Equipment Queries
    public Equipment findRegisteredEquipment(String equipmentId) { return equipmentRepository.findByEquipmentId(equipmentId); }

    public List<Equipment> findAllRegisteredEquipmentsForClinic(String clinicId){ return equipmentRepository.findByClinic(clinicId); }

    public List<Equipment> findRegisteredEquipmentByName(String clinicId, String searchName){ return equipmentRepository.findByEquipmentName(searchName, clinicId); }


    // Insurance Queries
    public Insurance findRegisteredInsurance(String insuranceId){ return insuranceRepository.findByInsuranceId(insuranceId); }

    public List<Insurance> findAllRegisteredInsurances(){ return insuranceRepository.findAll(); }

    public List<Insurance> findRegisteredInsyranceByInsuranceSerialCode(String insuranceSerialCode){ return insuranceRepository.findByInsuranceSerialCode(insuranceSerialCode); }

    public List<Insurance> findRegisteredInsuranceByOwner(String searchID){ return insuranceRepository.findByOwnerId(searchID); }

    // Medication Queries
    public Medication findRegisteredMedication(String medicationId) { return medicationRepository.findByMedicationId(medicationId); }

    public List<Medication> findRegisteredMedicationByClinic(String clinicId) { return medicationRepository.findByClinic(clinicId); }

    public List<Medication> findRegisteredMedicationByName(String clinicId, String medicationName) { return medicationRepository.findByMedicationName(medicationName, clinicId); }

    public List<Medication> findRegisteredMedicationBySupplier(String clinicId, String supplier) { return medicationRepository.findBySupplier(supplier, clinicId); }



    // Patient Queries
    public Patient findRegisteredPatient(String patientId){ return patientRepository.findByPatientId(patientId);}

    public List<Patient> findAllRegisteredPatientsForClinic(String clinicId){ return patientRepository.findByClinicId(clinicId); }

    public Patient findRegisteredPatientByIdCard(String clinicId, String searchIdCard){ return patientRepository.findByPatientIdCard(searchIdCard, clinicId); }

    //public List<Patient> findRegisteredPatientByEmail(String clinicId, String patientEmail){ return patientRepository.findByPatientEmail(patientEmail, clinicId); }

    //public List<Patient> findRegisteredPatientByBirthDate(String clinicId, Date patientBirthDate){ return patientRepository.findByPatientBirthDate(patientBirthDate, clinicId); }

    //public List<Patient> findRegisteredPatientByFullName(String clinicId, String searchFistName, String searchLastName){ return patientRepository.findByFullName(searchFistName, searchLastName, clinicId); }
/*
    public List<Patient> findRegisteredPatientByGeneralInformation(String clinicId, String searchFistName, String searchLastName,
                                                                  String searchTelephone,String searchEmail ){
        return patientRepository.findByFLTEFields(searchFistName,searchLastName,searchTelephone,searchEmail, clinicId);
    }
*/

    // Product Queries
    public Product findRegisteredProduct(String productId) { return productRepository.findByProductId(productId); }

    public List<Product> findRegisteredProductForClinic(String clinicId) { return productRepository.findByClinic(clinicId); }

    public List<Product> findRegisteredProductByName(String clinicId, String productName) { return productRepository.findByProductName(productName, clinicId); }


    // Record Queries
    public Record findRegisteredRecord(String recordId){ return recordRepository.findByRecordId(recordId); }

    public Record findPatientsRegisteredRecord(String patientId){

        if (!doesPatientIdExist(patientId))
            throw new IllegalArgumentException("\n\nThis is an invalid patient Id");

        return recordRepository.findByPatientId(patientId);
    }

    public List<Record> findRegisteredRecordsForClinic(String clinicId){ return recordRepository.findByClinicId(clinicId); }





    // User Queries
    public User findUserInformation(String userId) { return userRepository.findByUserId(userId); }

    public User findRegisteredUserAccount(String email, String password){ return userRepository.findUserAccountWithUsernameAndPassword(email,EncriptService.encryptPassword(password)); }

    public List<User> findAllAllRegisteredUsersForClinic(String clinicId) { return userRepository.findByClinicId(clinicId); }

    public boolean validateUserAccountCredentials(String username, String password) {

        User user = userRepository.findUserAccountWithUsernameAndClinicIdAndPassword(username.toLowerCase(), EncriptService.encryptPassword(password));

        return (user != null);
    }



    // Auxiliary Functions
    private boolean doesPatientIdExist(String patientId){
        Patient patient = patientRepository.findByPatientId(patientId);

        return (patient != null);
    }

    public boolean isUserLoggedIn() {
        return null != session.getAttribute("user");
    }

    public void logOut()
    {
        session.invalidate();
    }

    public User getCurrentLoggedUser()
    {
        return (User)session.getAttribute("user");
    }

}


