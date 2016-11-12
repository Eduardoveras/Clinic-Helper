/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package com.clinichelper.Repository;

import com.clinichelper.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, String>{

    Patient findByPatientId(String patientId);

    @Query("select p from Patient p where p.patientIdCard = :patientIdCard and p.clinic.clinicId = :clinic")
    Patient findByPatientIdCard(@Param("patientIdCard") String identificationNumber,@Param("clinic") String clinicId);

    @Query("select p from Patient p where p.clinic.clinicId = :clinic")
    List<Patient> findByClinicId(@Param("clinic") String clinicId);

    @Query("select p from Patient p where p.patientEmail = :email and p.clinic.clinicId = :clinic")
    List<Patient> findByPatientEmail(@Param("email") String patientEmail, @Param("clinic") String clinicId);

    @Query("select p from Patient p where p.patientBirthDate = :birthDate and p.clinic.clinicId = :clinic")
    List<Patient> findByPatientBirthDate(@Param("birthDate") Date patientBirthDate, @Param("clinic") String clinicId);

    @Query("select p from Patient p where p.patientBirthDate between :starting and :ending and p.clinic.clinicId = :clinic")
    List<Patient> findByPatientBirthDateRange(@Param("starting") Date start, @Param("ending") Date end, @Param("clinic") String clinicId);

    @Query("select p from Patient p where p.patientFirstName = :firstName and p.patientLastName = :lastName and p.clinic.clinicId = :clinic")
    List<Patient> findByFullName(@Param("firstName") String patientFristName, @Param("lastName") String patientLastName, @Param("clinic") String clinicId);

    @Query("select p from Patient p where p.patientFirstName = :firstName and p.patientLastName = :lastName and p.patientTelephoneNumber = :telephone and p.patientEmail = :email and p.clinic.clinicId = :clinic")
    List<Patient> findByFLTEFields(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("telephone") String telephone, @Param("email") String email, @Param("clinic") String clinicId);

}
