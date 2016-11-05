package com.clinichelper.Repository;

import com.clinichelper.Entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by eva_c on 10/8/2016.
 */
public interface MeetingRepository extends JpaRepository<Meeting, String> {
    Meeting findByMeetingId(String meetingId);

    @Query("select M from Meeting M where M.clinic.clinicId = :clinic")
    List<Meeting> findByClinicId(@Param("clinic") String clinicId);

    @Query("select M from Meeting M where M.meetingTitle = :meetingTitle and M.clinic.clinicId = :clinic")
    List<Meeting> findByMeetingTitle(@Param("meetingTitle") String meetingTitle, @Param("clinic") String clinicId);

    @Query("select M from Meeting M where M.clinic.clinicId = :clinic and M.meetingTime between :beginning and :ending")
    List<Meeting> findByMeetingDate(@Param("beginning") Timestamp start, @Param("ending") Timestamp end, @Param("clinic") String clinicId);

    @Query("select M from Meeting M where M.meetingPlace = :meetingPlace and M.clinic.clinicId = :clinic")
    List<Meeting> findByMeetingPlace(@Param("meetingPlace") String meetingPlace, @Param("clinic") String clinicId);
}
