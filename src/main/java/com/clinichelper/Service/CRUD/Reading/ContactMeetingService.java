/**
 * Created by Djidjelly Siclait on 12/14/2016.
 */
package com.clinichelper.Service.CRUD.Reading;

import com.clinichelper.Entity.Contact;
import com.clinichelper.Entity.Meeting;
import com.clinichelper.Repository.ContactRepository;
import com.clinichelper.Repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactMeetingService {

    // Repositories
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private MeetingRepository meetingRepository;

    // Contact
    public Contact findRegisteredContact(String contactId){ return contactRepository.findByContactId(contactId); }

    public List<Contact> findAllRegisteredContactsForClinic(String clinicId){ return contactRepository.findByClinicId(clinicId); }

    public Contact findRegisteredStaffByEmail(String clinicId, String staffEmail){ return contactRepository.findByStaffEmail(staffEmail, clinicId); }

    public List<Contact> findRegisteredStaffByFirstNameAndLastName(String clinicId, String searchFirstName, String searchLastName){ return contactRepository.findByFirstNameAndLastName(searchFirstName, searchLastName, clinicId); }

    // Meeting Queries
    public Meeting findRegisteredMeeting(String meetingId){ return meetingRepository.findByMeetingId(meetingId);}

    public List<Meeting> findAllRegisteredMeetingsForClinic(String clinicId){ return meetingRepository.findByClinicId(clinicId); }

    public List<Meeting> findRegisteredMeetingByTitle(String clinicId, String searchTitle){ return meetingRepository.findByMeetingTitle(searchTitle, clinicId); }

    //public List<Meeting> findRegisteredMeetingByDate (String clinicId, Date searchDate){ return meetingRepository.findByMeetingDate(searchDate, clinicId); }

    public List<Meeting> findRegisteredMeetingByPlace (String clinicId, String searchPlace){ return meetingRepository.findByMeetingPlace(searchPlace, clinicId); }

}
