package com.clinichelper.Repository;

import com.clinichelper.Entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by eva_c on 10/8/2016.
 */
public interface MeetingRepository extends JpaRepository<Meeting, String> {
    Meeting findByJascId (String jascId);

    @Query("select M from Meeting M where M.meetingTitle = :meetingTitle")
    List<Meeting> findByMeetingTitle(@Param("meetingTitle") String meetingTitle);

    @Query("select M from Meeting M where M.meetingDate = :meetingDate")
    List<Meeting> findByMeetingDate(@Param("meetingDate") Date meetingDate);

    @Query("select M from Meeting M where M.meetingPlace = :meetingPlace")
    List<Meeting> findByMeetingPlace(@Param("meetingPlace") String meetingPlace);
}
