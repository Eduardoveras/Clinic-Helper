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
        FetchBirthDateReminders();

        // Adding all of today's appointments
        FetchAppointments();
    }

    // TodoList Functions
    private void FetchCustomTasks(){

        todoList.addAll(choreRepository.findAll());
    }

    private void FetchBirthDateReminders(){

        todoList.addAll(findAllPatientBirthdayForNextWeek());

        todoList.addAll(findAllStaffBirthdayForNextWeek());
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
