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
    Consultation findByConsultationId(String consultationId);

    @Query("select c from Consultation c where c.appointment.clinic.clinicId = :clinic")
    List<Consultation> findByClinicId(@Param("clinic") String clinicId);

    @Query("select c from Consultation c where c.consultationDate = :consultationDate and c.appointment.clinic.clinicId = :clinic")
    List<Consultation> findByConsultationDate(@Param("consultationDate") Date consultationDate, @Param("clinic") String clinicId);

    @Query("select c from Consultation c where c.consultationDate between :beginning and :ending and c.appointment.clinic.clinicId = :clinic")
    List<Consultation> findByConsultationDateRange(@Param("beginning") Date startDate, @Param("ending") Date endDate, @Param("clinic") String clinicId);
}
