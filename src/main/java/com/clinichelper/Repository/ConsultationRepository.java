package com.clinichelper.Repository;

import com.clinichelper.Entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

/**
 * Created by eva_c on 10/8/2016.
 */
public interface ConsultationRepository extends JpaRepository<Consultation, String> {
    Consultation findByJascId (String jascId);

    @Query("select c from Consultation c where c.consultationDate = :consultationDate")
    List<Consultation> findByConsultationDate(@Param("consultationDate") Date consultationDate);

    @Query("select c from Consultation c where c.consultationDate between :beginning and :ending")
    List<Consultation> findByConsultationDateRange(@Param("beginning") Date startDate, @Param("ending") Date endDate);
}
