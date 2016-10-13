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
    Surgery findByJascId(String jascId);

    @Query("select s from Surgery s where s.surgeryName = :surgeryName " )
    List<Surgery> findBySurgeryName(@Param("surgeryName") String surgeryName);

    @Query("select s from Surgery s where s.patient.jascId = :jascId")
    List<Surgery> findByPatientJascId(@Param("jascId") String patientJascId);

    @Query("select s from Surgery s where s.surgeryDate = :surgeryDate " )
    List<Surgery> findBySurgeryDate(@Param("surgeryDate") Date surgeryDate);

    @Query("select s from Surgery s where s.surgeryDate between :beginning and :ending")
    List<Surgery> findyDateRange(@Param("beginning") Date startDate, @Param("ending") Date endDate);

    @Query("select s from Surgery s where s.surgeryRoom = :surgeryRoom " )
    List<Surgery> findBySurgeryRoom(@Param("surgeryRoom") String surgeryRoom );

    @Query("select s from Surgery s where s.surgeryDate = :surgeryDate and s.surgeryRoom = :surgeryRoom " )
    List<Surgery> findBySurgeryDateAndSurgeryRoom(@Param("surgeryDate") Date surgeryDate, @Param("surgeryRoom") String surgeryRoom );

}
