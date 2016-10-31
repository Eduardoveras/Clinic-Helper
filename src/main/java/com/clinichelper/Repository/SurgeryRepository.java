package com.clinichelper.Repository;

import com.clinichelper.Entity.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

/**
 * Created by eva_c on 10/8/2016.
 */
public interface SurgeryRepository extends JpaRepository<Surgery, String> {
    Surgery findBySurgeryId(String surgeryId);

    @Query("select s from Surgery s where s.appointment.clinic.clinicId = :clinic")
    List<Surgery> findByClinicId(@Param("clinic") String clinicId);

    @Query("select s from Surgery s where s.surgeryName = :surgeryName and s.appointment.clinic.clinicId = :clinic" )
    List<Surgery> findBySurgeryName(@Param("surgeryName") String surgeryName, @Param("clinic") String clinicId);

    @Query("select s from Surgery s where s.patient.patientId = :id")
    List<Surgery> findByPatientId(@Param("id") String patientId);

    @Query("select s from Surgery s where s.surgeryDate = :surgeryDate and s.appointment.clinic.clinicId = :clinic" )
    List<Surgery> findBySurgeryDate(@Param("surgeryDate") Date surgeryDate, @Param("clinic") String clinicId);

    @Query("select s from Surgery s where s.appointment.clinic.clinicId = :clinic and s.surgeryDate between :beginning and :ending")
    List<Surgery> findyDateRange(@Param("beginning") Date startDate, @Param("ending") Date endDate, @Param("clinic") String clinicId);

    @Query("select s from Surgery s where s.surgeryRoom = :surgeryRoom and s.appointment.clinic.clinicId = :clinic" )
    List<Surgery> findBySurgeryRoom(@Param("surgeryRoom") String surgeryRoom, @Param("clinic") String clinicId);

    @Query("select s from Surgery s where s.surgeryDate = :surgeryDate and s.surgeryRoom = :surgeryRoom and s.appointment.clinic.clinicId = :clinic" )
    List<Surgery> findBySurgeryDateAndSurgeryRoom(@Param("surgeryDate") Date surgeryDate, @Param("surgeryRoom") String surgeryRoom, @Param("clinic") String clinicId);

}
