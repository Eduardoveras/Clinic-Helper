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
    private final long MILLISECONDS_IN_A_DAY = 24 * 60 * 60 * 1000;
    private List<Chore> todoList = new ArrayList<>();

    // Repositories
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ChoreRepository choreRepository;
    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private StaffRepository staffRepository;


    public void InitializeTodoList(){

        // Adding Custom created tasks
        FetchCustomTasks();

        // Adding Birthday reminder tasks
        FetchBirthAndRegistrationDatesReminders();

        // Adding all of today's appointments
        FetchAppointments();

        // Adding all of today's meeting;
        FetchMeetings();
    }

    // TodoList Functions
    private void FetchCustomTasks(){

        todoList.addAll(choreRepository.findAll());
    }

    private void FetchBirthAndRegistrationDatesReminders(){

        todoList.addAll(findAllPatientBirthdayForNextWeek());

        todoList.addAll(findAllStaffBirthdayForNextWeek());

        todoList.addAll(findAllPatientWhoCompletedAnotherYear());
    }

    private void FetchAppointments(){
        todoList.addAll(findAllOfTodaysAppointments());
    }

    private void FetchMeetings(){
        todoList.addAll(findAllOfTodaysMeetings());
    }

    // Auxiliary Function
    private List<Chore> findAllOfTodaysAppointments(){

        List<Chore> chores = new ArrayList<>();

        java.util.Date utilDate = new java.util.Date();

        for (Appointment a:
             appointmentRepository.findByDate(new Date(utilDate.getTime()))) {
            chores.add(new Chore("Appointment with: " + a.getPatient().getPatientLastName() + " " + a.getPatient().getPatientLastName(),
                    Task.APPOINTMENT,
                    "Time: " + a.getAppointmentTime().toString().substring(13) + "\nObjective: " + a.getAppointmentDescription()));
        }

        return chores;
    }

    private List<Chore> findAllOfTodaysMeetings(){

        List<Chore> chores = new ArrayList<>();

        java.util.Date utilDate = new java.util.Date();

        for (Meeting m:
             meetingRepository.findByMeetingDate(new Date(utilDate.getTime()))) {

            String staff = "**";

            for (Staff s:
                 m.getAttendees()) {
                staff += s.getStaffFirstName() + " " + s.getStaffLastName() + "** ";
            }

            chores.add(new Chore("Meeting Today: " + m.getMeetingTitle() + " At " + m.getMeetingTime(),
                    Task.MEETING,
                    "Place" + m.getMeetingPlace() + "\nAttendees: " + staff + "\nObjective: " + m.getMeetingObjective()));
        }

        return chores;
    }

    private List<Chore> findAllPatientBirthdayForNextWeek(){
        Calendar today = Calendar.getInstance();
        Calendar seventhDay = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();

        List<Chore> chores = new ArrayList<>();

        java.util.Date utilDate = new java.util.Date();

        today.setTime(new Date(utilDate.getTime()));
        seventhDay.setTime(today.getTime());
        seventhDay.add(Calendar.DATE, 7);

        for (Patient p:
                patientRepository.findAll()) {

            birthDate.setTime(p.getPatientBirthDate());

            if ((today.get(Calendar.MONTH) - birthDate.get(Calendar.MONTH)) == 0) {
                if ((today.get(Calendar.DAY_OF_MONTH) - birthDate.get(Calendar.DAY_OF_MONTH)) == 0)
                    chores.add(new Chore("Happy Birthday " + p.getPatientFirstName() + " " + p.getPatientLastName() + "!",
                            Task.PATIENT_BIRTHDAY,
                            "Celebrate " + p.getPatientFirstName() + " " + p.getPatientLastName() + "'s special day! We have such awesome patients!"));
                else if ((birthDate.get(Calendar.DAY_OF_MONTH) - today.get(Calendar.DAY_OF_MONTH)) > 0 && (seventhDay.get(Calendar.DAY_OF_MONTH) - birthDate.get(Calendar.DAY_OF_MONTH)) < 7)
                    chores.add(new Chore("It's almost someone's special day!",
                            Task.PATIENT_BIRTHDAY,
                            "In" + (birthDate.get(Calendar.DAY_OF_MONTH) - today.get(Calendar.DAY_OF_MONTH)) + "day(s) it will be " + p.getPatientFirstName() + " " + p.getPatientLastName() + "'s special day! Be Ready!"));
            }
        }

        return chores;
    }

    private List<Chore> findAllPatientWhoCompletedAnotherYear(){
        Calendar today = Calendar.getInstance();
        Calendar registered = Calendar.getInstance();

        List<Chore> chores = new ArrayList<>();

        java.util.Date utilDate = new java.util.Date();

        today.setTime(new Date(utilDate.getTime()));

        for (Patient p:
             patientRepository.findAll()) {

            registered.setTime(p.getPatientRegisteredDate());

            if ((today.get(Calendar.MONTH) - registered.get(Calendar.MONTH)) == 0 && (today.get(Calendar.DAY_OF_MONTH) - registered.get(Calendar.DAY_OF_MONTH)) == 0)
                if ((today.get(Calendar.YEAR) - registered.get(Calendar.YEAR)) == 1)
                    chores.add(new Chore(p.getPatientFirstName().toUpperCase() + " " + p.getPatientLastName() + " Met Us One Year Ago!",
                            Task.REGISTRATIONDATE,
                            "Has it been ONE year already? " + p.getPatientFirstName().toUpperCase() + " " + p.getPatientLastName() + " has completed their first year with us! Congratulations faithful patient!"));
                else if ((today.get(Calendar.YEAR) - registered.get(Calendar.YEAR)) > 1)
                    chores.add(new Chore( "We Are Happy To Be Celebrating Another Year With " + p.getPatientFirstName().toUpperCase() + " " + p.getPatientLastName(),
                            Task.REGISTRATIONDATE,
                            (today.get(Calendar.YEAR) - registered.get(Calendar.YEAR)) + " years together! How time flies?! " + p.getPatientFirstName().toUpperCase() + " " + p.getPatientLastName() + " has completed their first year with us! Congratulations faithful patient!"));
        }

        return chores;
    }

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

            if ((today.get(Calendar.MONTH) - birthDate.get(Calendar.MONTH)) == 0) {
                if ((today.get(Calendar.DAY_OF_MONTH) - birthDate.get(Calendar.DAY_OF_MONTH)) == 0)
                    chores.add(new Chore("Happy Birthday " + s.getStaffFirstName() + " " + s.getStaffLastName() + "!",
                            Task.STAFF_BIRTHDAY,
                            "Celebrate " + s.getStaffFirstName() + " " + s.getStaffLastName() + "'s special day! We thank you for being part of the team!"));
                else if ((birthDate.get(Calendar.DAY_OF_MONTH) - today.get(Calendar.DAY_OF_MONTH)) > 0 && (seventhDay.get(Calendar.DAY_OF_MONTH) - birthDate.get(Calendar.DAY_OF_MONTH)) < 7)
                    chores.add(new Chore("It's almost someone's special day!",
                            Task.STAFF_BIRTHDAY,
                            "In" + (birthDate.get(Calendar.DAY_OF_MONTH) - today.get(Calendar.DAY_OF_MONTH)) + "day(s) it will be " + s.getStaffFirstName() + " " + s.getStaffLastName() + "'s special day! Don't forget to celebrate their contribution as a valued member of the JASC Team!"));
            }
        }

        return chores;
    }
}
