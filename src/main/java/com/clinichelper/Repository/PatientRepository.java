/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package com.clinichelper.Repository;

import com.clinichelper.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, String>{

    Patient findByJascId(String jascId);

    @Query("select p from Patient p where p.patientIdCard = :patientIdCard")
    Patient findPatientByIdentificationCard(@Param("patientIdCard") String identificationNumber);

    @Query("select p from Patient p where p.patientName = :firstName and p.patientLasName = :lastName and p.patientTelephoneNumber = :telephone and p.patientEmail = :email")
    List<Patient> findRegisteredPatientByFLTEFields(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("telephone") String telephone, @Param("email") String email);

}
