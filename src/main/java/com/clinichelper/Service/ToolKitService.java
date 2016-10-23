/**
 * Created by Djidjelly Siclait on 10/11/2016.
 */
package com.clinichelper.Service;

import com.clinichelper.Entity.Appointment;
import com.clinichelper.Entity.Chore;
import com.clinichelper.Entity.Patient;
import com.clinichelper.Entity.Staff;
import com.clinichelper.Repository.AppointmentRepository;
import com.clinichelper.Repository.ChoreRepository;
import com.clinichelper.Repository.PatientRepository;
import com.clinichelper.Repository.StaffRepository;
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

    private List<Chore> findAllPatientBirthdayForNextWeek(){

        List<Chore> chores = new ArrayList<>();

        java.util.Date utilDate = new java.util.Date();
        Calendar c = Calendar.getInstance();

        c.setTime(new Date(utilDate.getTime())); // Now use today date.
        c.add(Calendar.DATE, 7);

        for (Patient p:
                patientRepository.findByPatientBirthDateRange(new Date(utilDate.getTime()), new Date(c.getTime().getTime()))) {
            if (differenceInDays(new Date(utilDate.getTime()), p.getPatientBirthDate()) <= 0)
                chores.add(new Chore("Happy Birthday " + p.getPatientFirstName() + " " + p.getPatientLastName() + "!",
                        Task.PATIENT_BIRTHDAY,
                        "Celebrate " + p.getPatientFirstName() + " " + p.getPatientLastName() + "'s special day! We have such awesome patients!"));
            else
                chores.add(new Chore("It's almost someone's special day!",
                        Task.PATIENT_BIRTHDAY,
                        "In" + differenceInDays(new Date(utilDate.getTime()), p.getPatientBirthDate()) + "day(s) it will be " + p.getPatientFirstName() + " " + p.getPatientLastName() + "'s special day! Be Ready!"));
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
        List<Chore> chores = new ArrayList<>();

        java.util.Date utilDate = new java.util.Date();
        Calendar c = Calendar.getInstance();

        c.setTime(new Date(utilDate.getTime())); // Now use today date.
        c.add(Calendar.DATE, 7);

        for (Staff s:
             staffRepository.findByStaffBirthDateRange(new Date(utilDate.getTime()), new Date(c.getTime().getTime()))) {
            if (differenceInDays(new Date(utilDate.getTime()), s.getStaffBirthDate()) <= 0)
                chores.add(new Chore("Happy Birthday " + s.getStaffFirstName() + " " + s.getStaffLastName() + "!",
                        Task.STAFF_BIRTHDAY,
                        "Celebrate " + s.getStaffFirstName() + " " + s.getStaffLastName() + "'s special day! We thank you for being part of the team!"));
            else
                chores.add(new Chore("It's almost someone's special day!",
                        Task.STAFF_BIRTHDAY,
                        "In" + differenceInDays(new Date(utilDate.getTime()), s.getStaffBirthDate()) + "day(s) it will be " + s.getStaffFirstName() + " " + s.getStaffLastName() + "'s special day! Don't forget to celebrate their contribution as a valued member of the JASC Team!"));
        }

        return chores;
    }

    private int differenceInDays(Date start, Date end){
        return (int)((end.getTime() - start.getTime()) / MILLISECONDS_IN_A_DAY);
    }
}
