/**
 * Created by Djidjelly Siclait on 10/11/2016.
 */
package com.clinichelper.Service;

import com.clinichelper.Entity.*;
import com.clinichelper.Repository.*;
import com.clinichelper.Tools.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ToolKitService {

    // Attributes
    private List<Chore> todoList = new ArrayList<>();

    public List<Chore> getTodoList() {
        return todoList;
    }

    // Repositories
    @Autowired
    private ChoreRepository choreRepository;
    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ContactRepository contactRepository;


    public void InitializeTodoList(String clinicId){

        // Adding Custom created tasks
        FetchCustomTasks(clinicId);

        // Adding Birthday reminder tasks
        FetchBirthAndRegistrationDatesReminders(clinicId);

        // Adding all of today's meeting;
        //FetchMeetings(clinicId);

        // TODO: Add surgery mechanic
    }

    // TodoList Functions
    private void FetchCustomTasks(String clinicId){

        todoList.addAll(choreRepository.findByClinicId(clinicId));
    }

    private void FetchBirthAndRegistrationDatesReminders(String clinicId){

        todoList.addAll(findAllPatientBirthdayForNextWeek(clinicId));

       // todoList.addAll(findAllStaffBirthdayForNextWeek(clinicId));

        //todoList.addAll(findAllPatientWhoCompletedAnotherYear(clinicId));
    }

    private void FetchMeetings(String clinicId){
        //todoList.addAll(findAllOfTodaysMeetings(clinicId));
    }

    // Auxiliary Function
   /* private List<Chore> findAllOfTodaysMeetings(String clinicId){

        List<Chore> chores = new ArrayList<>();

        java.util.Date utilDate = new java.util.Date();

        for (Meeting m:
             meetingRepository.findByMeetingDate(new Date(utilDate.getTime()))) {

            String staff = "**";

            for (Staff s:
                 m.getAttendees()) {
                staff += s.getStaffFullName() + "** ";
            }

            chores.add(new Chore("Meeting Today: " + m.getMeetingTitle() + " At " + m.getMeetingTime().toString().substring(10),
                    Task.MEETING,
                    "Place: " + m.getMeetingPlace() + "\nAttendees: " + staff + "\nObjective: " + m.getMeetingObjective()));
        }

        return chores;
    }
*/
    private List<Chore> findAllPatientBirthdayForNextWeek(String clinicId){
        Calendar today = Calendar.getInstance();
        Calendar seventhDay = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();

        List<Chore> chores = new ArrayList<>();

        java.util.Date utilDate = new java.util.Date();

        today.setTime(new Date(utilDate.getTime()));
        seventhDay.setTime(today.getTime());
        seventhDay.add(Calendar.DATE, 7);

        for (Patient p:
                patientRepository.findByClinicId(clinicId)) {

            birthDate.setTime(p.getPatientBirthDate());

            if ((birthDate.get(Calendar.MONTH) - today.get(Calendar.MONTH)) == 0) {
                if ((today.get(Calendar.DAY_OF_MONTH) - birthDate.get(Calendar.DAY_OF_MONTH)) == 0)
                    chores.add(new Chore(clinicRepository.findByClinicId(clinicId), "Happy Birthday " + p.getPatientFullName() + "!",
                            Task.PATIENT_BIRTHDAY,
                            "Celebrate " + p.getPatientFullName() + "'s special day! We have such awesome patients!"));
                else if ((birthDate.get(Calendar.DAY_OF_MONTH) - today.get(Calendar.DAY_OF_MONTH)) > 0 && (seventhDay.get(Calendar.DAY_OF_MONTH) - birthDate.get(Calendar.DAY_OF_MONTH)) <= 7)
                    chores.add(new Chore(clinicRepository.findByClinicId(clinicId), "It's almost someone's special day!",
                            Task.PATIENT_BIRTHDAY,
                            "In " + (birthDate.get(Calendar.DAY_OF_MONTH) - today.get(Calendar.DAY_OF_MONTH)) + " day(s) it will be " + p.getPatientFullName() + "'s special day! Be Ready!"));
            } else if ((birthDate.get(Calendar.MONTH) - today.get(Calendar.MONTH)) == 1 && Math.abs(today.get(Calendar.DAY_OF_MONTH) - birthDate.get(Calendar.DAY_OF_MONTH)) >= 23) // In there is a change of month during the week
                chores.add(new Chore(clinicRepository.findByClinicId(clinicId), "It's almost someone's special day!",
                        Task.PATIENT_BIRTHDAY,
                        "It will soon be " + p.getPatientFullName() + "'s special day! Be Ready for " + birthDate.get(Calendar.DAY_OF_MONTH) + " of " + getMonthName(birthDate.get(Calendar.MONTH)) + "!"));
        }

        return chores;
    }

    private List<Chore> findAllPatientWhoCompletedAnotherYear(String clinicId){
        Calendar today = Calendar.getInstance();
        Calendar registered = Calendar.getInstance();

        List<Chore> chores = new ArrayList<>();

        java.util.Date utilDate = new java.util.Date();

        today.setTime(new Date(utilDate.getTime()));

        for (Patient p:
             patientRepository.findByClinicId(clinicId)) {

            registered.setTime(p.getPatientRegisteredDate());

            if ((today.get(Calendar.MONTH) - registered.get(Calendar.MONTH)) == 0 && (today.get(Calendar.DAY_OF_MONTH) - registered.get(Calendar.DAY_OF_MONTH)) == 0)
                if ((today.get(Calendar.YEAR) - registered.get(Calendar.YEAR)) == 1)
                    chores.add(new Chore(clinicRepository.findByClinicId(clinicId), p.getPatientFullName() + " Met Us One Year Ago!",
                            Task.REGISTRATIONDATE,
                            "Has it been ONE year already? " + p.getPatientFullName() + " has completed their first year with us! Congratulations faithful patient!"));
                else if ((today.get(Calendar.YEAR) - registered.get(Calendar.YEAR)) > 1)
                    chores.add(new Chore(clinicRepository.findByClinicId(clinicId), "We Are Happy To Be Celebrating Another Year With " + p.getPatientFullName(),
                            Task.REGISTRATIONDATE,
                            (today.get(Calendar.YEAR) - registered.get(Calendar.YEAR)) + " years together! How time flies?! " + p.getPatientFullName() + " has completed their first year with us! Congratulations faithful patient!"));
        }

        return chores;
    }
/*
    private List<Chore> findAllStaffBirthdayForNextWeek(){
        Calendar today = Calendar.getInstance();
        Calendar seventhDay = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();

        List<Chore> chores = new ArrayList<>();

        java.util.Date utilDate = new java.util.Date();

        today.setTime(new Date(utilDate.getTime()));
        seventhDay.setTime(today.getTime());
        seventhDay.add(Calendar.DATE, 7);

        for (Staff s:
             staffRepository.findAll()) {

            birthDate.setTime(s.getStaffBirthDate());
            if (!s.getJascId().equals("JASC-STAFF-ADMIN")) {
                if ((today.get(Calendar.MONTH) - birthDate.get(Calendar.MONTH)) == 0) {
                    if ((today.get(Calendar.DAY_OF_MONTH) - birthDate.get(Calendar.DAY_OF_MONTH)) == 0)
                        chores.add(new Chore("Happy Birthday " + s.getStaffFullName() + "!",
                                Task.STAFF_BIRTHDAY,
                                "Celebrate " + s.getStaffFullName() + "'s special day! We thank you for being part of the team!"));
                    else if ((birthDate.get(Calendar.DAY_OF_MONTH) - today.get(Calendar.DAY_OF_MONTH)) > 0 && (seventhDay.get(Calendar.DAY_OF_MONTH) - birthDate.get(Calendar.DAY_OF_MONTH)) <= 7)
                        chores.add(new Chore("It's almost someone's special day!",
                                Task.STAFF_BIRTHDAY,
                                "In" + (birthDate.get(Calendar.DAY_OF_MONTH) - today.get(Calendar.DAY_OF_MONTH)) + "day(s) it will be " + s.getStaffFullName() + "'s special day! Don't forget to celebrate their contribution as a valued member of the JASC Team!"));
                } else if ((birthDate.get(Calendar.MONTH) - today.get(Calendar.MONTH)) == 1 && Math.abs(today.get(Calendar.DAY_OF_MONTH) - birthDate.get(Calendar.DAY_OF_MONTH)) >= 23) // In there is a change of month during the week
                    chores.add(new Chore("It's almost someone's special day!",
                            Task.PATIENT_BIRTHDAY,
                            "It will soon be " + s.getStaffFullName() + "'s special day! Be Ready for " + birthDate.get(Calendar.DAY_OF_MONTH) + " of " + getMonthName(birthDate.get(Calendar.MONTH)) + "!"));
            }
        }

        return chores;
    }
*/
    // Auxiliary Functions
    private String getMonthName(int monthId){
        switch (monthId){
            case 0:
                return "JAN";
            case 1:
                return "FEB";
            case 2:
                return "MAR";
            case 3:
                return "APR";
            case 4:
                return "MAY";
            case 5:
                return "JUN";
            case 6:
                return "JUL";
            case 7:
                return "AUG";
            case 8:
                return "SEP";
            case 9:
                return "OCT";
            case 10:
                return "NOV";
            case 11:
                return "DEC";
            default:
                return "Incorrect Value!";
        }
    }
}