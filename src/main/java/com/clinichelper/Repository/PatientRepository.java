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

    Patient findByJascId(String jascId);

    @Query("select p from Patient p where p.patientIdCard = :patientIdCard")
    Patient findByPatientIdCard(@Param("patientIdCard") String identificationNumber);

    @Query("select p from Patient p where p.patientEmail = :email")
    List<Patient> findByPatientEmail(@Param("email") String patientEmail);

    @Query("select p from Patient p where p.patientBirthDate = :birthDate")
    List<Patient> findByPatientBirthDate(@Param("birthDate") Date patientBirthDate);

    @Query("select p from Patient p where p.patientFirstName = :firstName and p.patientLastName = :lastName")
    List<Patient> findByFullName(@Param("firstName") String patientFristName, @Param("lastName") String patientLastName);

    @Query("select p from Patient p where p.patientFirstName = :firstName and p.patientLastName = :lastName and p.patientTelephoneNumber = :telephone and p.patientEmail = :email")
    List<Patient> findByFLTEFields(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("telephone") String telephone, @Param("email") String email);

}
