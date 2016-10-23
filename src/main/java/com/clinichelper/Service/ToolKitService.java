/**
 * Created by Djidjelly Siclait on 10/11/2016.
 */
package com.clinichelper.Service;

import com.clinichelper.Entity.Chore;
import com.clinichelper.Entity.Patient;
import com.clinichelper.Entity.Staff;
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
    }

    // TodoList Functions
    private void FetchCustomTasks(){

        for (Chore c:
             choreRepository.findAll()) {
            todoList.add(c);
        }
    }

    private void FetchBirthDateReminders(){

        for (Chore c:
             findAllPatientBirthdayForNextWeek()) {
            todoList.add(c);
        }

        for (Chore c:
             findAllStaffBirthdayForNextWeek()) {
            todoList.add(c);
        }
    }

    // Auxiliary Function
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
                        Task.PATIENT_BIRTHDAY,
                        "Celebrate " + s.getStaffFirstName() + " " + s.getStaffLastName() + "'s special day! We thank you for being part of the team!"));
            else
                chores.add(new Chore("It's almost someone's special day!",
                        Task.PATIENT_BIRTHDAY,
                        "In" + differenceInDays(new Date(utilDate.getTime()), s.getStaffBirthDate()) + "day(s) it will be " + s.getStaffFirstName() + " " + s.getStaffLastName() + "'s special day! Don't forget to celebrate their contribution as a valued member of the JASC Team!"));
        }

        return chores;
    }

    private int differenceInDays(Date start, Date end){
        return (int)((end.getTime() - start.getTime()) / MILLISECONDS_IN_A_DAY);
    }
}
