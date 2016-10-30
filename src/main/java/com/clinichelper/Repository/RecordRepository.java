package com.clinichelper.Repository;

import com.clinichelper.Entity.Patient;
import com.clinichelper.Entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by eva_c on 10/8/2016.
 */
public interface RecordRepository extends JpaRepository<Record, String > {
    Record findByJascId(String jascId);

    @Query("select r from Record r where r.patient.patientId = :id" )
    List<Record> findByPatientId(@Param("id") String patientId);
}
