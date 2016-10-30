/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package com.clinichelper.Repository;

import com.clinichelper.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    Appointment findByAppointmentId(String appointmentId);

    @Query("select a from Appointment a where a.clinic.clinicId = :clinic")
    List<Appointment> findByClinicId(@Param("clinic") String clinicId);

    @Query("select a from Appointment a where a.appointmentDate = :appointmentDate and a.clinic.clinicId = :clinic")
    List<Appointment> findByDate(@Param("appointmentDate") Date searchDate, @Param("clinic") String clinicId);

    @Query("select a from Appointment a where a.clinic.clinicId = :clinic and a.appointmentDate between :beginning and :ending")
    List<Appointment> findByDateRange(@Param("beginning") Date startDate, @Param("ending") Date endDate, @Param("clinic") String clinicId);

    @Query("select a from Appointment a where a.patient.jascId = :id")
    List<Appointment> findByPatientJascId(@Param("id") String patientJascId);
}
