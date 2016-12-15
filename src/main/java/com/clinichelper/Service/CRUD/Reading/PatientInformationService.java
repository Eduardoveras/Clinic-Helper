/**
 * Created by Djidjelly Siclait on 12/14/2016.
 */
package com.clinichelper.Service.CRUD.Reading;

import com.clinichelper.Entity.Insurance;
import com.clinichelper.Entity.Patient;
import com.clinichelper.Entity.Record;
import com.clinichelper.Repository.InsuranceRepository;
import com.clinichelper.Repository.PatientRepository;
import com.clinichelper.Repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientInformationService {

    // Repositories
    @Autowired
    private InsuranceRepository insuranceRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private RecordRepository recordRepository;

    // Insurance
    public Insurance findRegisteredInsurance(String insuranceId){ return insuranceRepository.findByInsuranceId(insuranceId); }

    public List<Insurance> findAllRegisteredInsurances(){ return insuranceRepository.findAll(); }

    public List<Insurance> findRegisteredInsyranceByInsuranceSerialCode(String insuranceSerialCode){ return insuranceRepository.findByInsuranceSerialCode(insuranceSerialCode); }

    public List<Insurance> findRegisteredInsuranceByOwner(String searchID){ return insuranceRepository.findByOwnerId(searchID); }

    // Patients
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

    // Records
    public Record findRegisteredRecord(String recordId){ return recordRepository.findByRecordId(recordId); }

    public Record findPatientsRegisteredRecord(String patientId){

        if (!doesPatientIdExist(patientId))
            throw new IllegalArgumentException("\n\nThis is an invalid patient Id");

        return recordRepository.findByPatientId(patientId);
    }

    public List<Record> findRegisteredRecordsForClinic(String clinicId){ return recordRepository.findByClinicId(clinicId); }


    // Auxiliary Functions
    private boolean doesPatientIdExist(String patientId){
        Patient patient = patientRepository.findByPatientId(patientId);

        return (patient != null);
    }
}
