/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package com.clinichelper.Repository;

import com.clinichelper.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    Appointment findByAppointmentId(String appointmentId);

    @Query("select a from Appointment a where a.clinic.clinicId = :clinic")
    List<Appointment> findByClinicId(@Param("clinic") String clinicId);

    @Query("select a from Appointment a where a.clinic.clinicId = :clinic and a.appointmentTime between :beginning and :ending")
    List<Appointment> findByDateRange(@Param("beginning") Timestamp startDate, @Param("ending") Timestamp endDate, @Param("clinic") String clinicId);

    @Query("select a from Appointment a where a.patient.patientId = :id")
    List<Appointment> findByPatientId(@Param("id") String patientId);
}
