package com.clinichelper.Repository;

import com.clinichelper.Entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by eva_c on 10/8/2016.
 */
public interface ConsultationRepository extends JpaRepository<Consultation, String> {
    Consultation findByJascId (String jascId);

    @Query("select C from Consultation C where C.consultationDate = :consultationDate")
    List<Consultation> findConsultationByconsultationDate(@Param("consultationDate") Date consultationDate);

}
