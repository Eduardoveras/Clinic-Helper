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

    @Query("select R from Record R where R.patient.jascId = :patient " )
    List<Record> findRecordyBypatient(@Param("patient") Patient patient);
}
