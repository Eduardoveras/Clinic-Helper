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

    Appointment findByJascId(String jascID);
/*
    @Query("select a from Appointment a where a.appointmentDate = :appointmentDate")
    List<Appointment> findAppointmentsByDate(@Param("appointmentDate") Date searchdate);

    @Query("select a from Appointment a where a.pappointmentDate between :beginning and :ending")
    List<Appointment> findAppointmentsByRange(@Param("beginning") Date startDate, @Param("ending") Date endDate);
    */
}
