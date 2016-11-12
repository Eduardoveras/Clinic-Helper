/**
 * Created by Djidjelly Siclait on 10/11/2016.
 */
package com.clinichelper.Service;

import com.clinichelper.Entity.*;
import com.clinichelper.Repository.*;
import com.clinichelper.Tools.Classes.CalendarEvent;
import com.clinichelper.Tools.Enums.AppointmentStatus;
import com.clinichelper.Tools.Enums.EventStatus;
import com.clinichelper.Tools.Enums.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ToolKitService {

    // Attributes
    private static final long MILLISECONDS_IN_A_DAY = 24 * 60 * 60 * 1000;
    private List<Chore> todoList;

    // Repositories
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private ChoreRepository choreRepository;
    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ContactRepository contactRepository;

    // Services
    public List<Chore> InitializeTodoList(String clinicId){

        todoList = new ArrayList<>();

        // Adding Custom created tasks
        todoList.addAll(choreRepository.findByClinicId(clinicId));

        // Adding Birthday reminder tasks
        todoList.addAll(findAllPatientBirthdayForNextWeek(clinicId));

        todoList.addAll(findAllStaffBirthdayForNextWeek(clinicId));

        todoList.addAll(findAllPatientWhoCompletedAnotherYear(clinicId));

        // Adding all of today's meeting;
        todoList.addAll(findAllMeetingsForToday(clinicId));

        // TODO: Add surgery mechanic

        return todoList;
    }

    public List<CalendarEvent> InitializeClinicCalendar(String clinicId){
        List<CalendarEvent> events = new ArrayList<>();

        // Adding all Appointments
        for (Appointment a:
             appointmentRepository.findByClinicId(clinicId)) {
            events.add(new CalendarEvent("Appointment With " + a.getPatient().getPatientFullName(), a.getAppointmentTime(), a.getAppointmentDescription(), a.getAppointmentStatus() == AppointmentStatus.PENDING ? EventStatus.PENDING : a.getAppointmentStatus() == AppointmentStatus.COMPLETED ? EventStatus.COMPLETED : EventStatus.EXPIRED));
        }

        // Adding all Meetings
        for (Meeting m:
                meetingRepository.findByClinicId(clinicId)) {
            events.add(new CalendarEvent("MEETING: " + m.getMeetingTitle(), m.getMeetingTime(), m.getMeetingObjective(), differenceInDays(new Date(Calendar.getInstance().getTime().getTime()), new Date(m.getMeetingTime().getTime())) <= 0 ? EventStatus.COMPLETED : EventStatus.PENDING));
        }

        return events;
    }

    public Map<String, List> FetchClinicInventory(String clinicId){
        Map<String, List> inventory = new HashMap<>();

        // Adding Equipments
        inventory.put("equipments", StockEquipmentShelf(clinicId));

        // Adding Products
        inventory.put("products", StockProductSelf(clinicId));

        // Adding Medications
        inventory.put("medication", StockMedicationSelf(clinicId));

        return inventory;
    }

    // Inventory Functions
    private List<Equipment> StockEquipmentShelf(String clinicId) { return equipmentRepository.findByClinic(clinicId); }

    private List<Product> StockProductSelf(String clinicId) { return productRepository.findByClinic(clinicId); }

    private List<Medication> StockMedicationSelf(String clinicId){ return medicationRepository.findByClinic(clinicId); }


    // Auxiliary Function
    private List<Chore> findAllMeetingsForToday(String clinicId){
        List<Chore> chores = new ArrayList<>();

        try{
            for (Meeting m:
                    meetingRepository.findByMeetingDate(new Timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(new Date(Calendar.getInstance().getTime().getTime()).toString() + " 00:00:00").getTime()), new Timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(new Date(Calendar.getInstance().getTime().getTime()).toString() + " 23:59:00").getTime()), clinicId)) {

                String staff = "**";

                for (Contact s:
                        m.getAttendees()) {
                    staff += s.getFullName() + "** ";
                }

                chores.add(new Chore(clinicRepository.findByClinicId(clinicId), "Meeting Today: " + m.getMeetingTitle() + " At " + m.getMeetingTime().toString().substring(10),
                        Task.MEETING,
                        "Place: " + m.getMeetingPlace() + "\nAttendees: " + staff + "\nObjective: " + m.getMeetingObjective()));
            }
        } catch (Exception exp) {
            // TODO: add exception handling
        }

        return chores;
    }

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

    private List<Chore> findAllStaffBirthdayForNextWeek(String clinicId){
        Calendar today = Calendar.getInstance();
        Calendar seventhDay = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();

        List<Chore> chores = new ArrayList<>();

        java.util.Date utilDate = new java.util.Date();

        today.setTime(new Date(utilDate.getTime()));
        seventhDay.setTime(today.getTime());
        seventhDay.add(Calendar.DATE, 7);

        for (Contact s:
             contactRepository.findByClinicId(clinicId)) {

            birthDate.setTime(s.getBirthDate());
            if (!s.getContactId().equals("JASC-STAFF-ADMIN")) {
                if ((today.get(Calendar.MONTH) - birthDate.get(Calendar.MONTH)) == 0) {
                    if ((today.get(Calendar.DAY_OF_MONTH) - birthDate.get(Calendar.DAY_OF_MONTH)) == 0)
                        chores.add(new Chore(clinicRepository.findByClinicId(clinicId), "Happy Birthday " + s.getFullName() + "!",
                                Task.STAFF_BIRTHDAY,
                                "Celebrate " + s.getFullName() + "'s special day! We thank you for being part of the team!"));
                    else if ((birthDate.get(Calendar.DAY_OF_MONTH) - today.get(Calendar.DAY_OF_MONTH)) > 0 && (seventhDay.get(Calendar.DAY_OF_MONTH) - birthDate.get(Calendar.DAY_OF_MONTH)) <= 7)
                        chores.add(new Chore(clinicRepository.findByClinicId(clinicId), "It's almost someone's special day!",
                                Task.STAFF_BIRTHDAY,
                                "In" + (birthDate.get(Calendar.DAY_OF_MONTH) - today.get(Calendar.DAY_OF_MONTH)) + "day(s) it will be " + s.getFullName() + "'s special day! Don't forget to celebrate their contribution as a valued member of the JASC Team!"));
                } else if ((birthDate.get(Calendar.MONTH) - today.get(Calendar.MONTH)) == 1 && Math.abs(today.get(Calendar.DAY_OF_MONTH) - birthDate.get(Calendar.DAY_OF_MONTH)) >= 23) // In there is a change of month during the week
                    chores.add(new Chore(clinicRepository.findByClinicId(clinicId), "It's almost someone's special day!",
                            Task.STAFF_BIRTHDAY,
                            "It will soon be " + s.getFullName() + "'s special day! Be Ready for " + birthDate.get(Calendar.DAY_OF_MONTH) + " of " + getMonthName(birthDate.get(Calendar.MONTH)) + "!"));
            }
        }

        return chores;
    }

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

    private int differenceInDays(Date start, Date end){
        return (int)((end.getTime() - start.getTime()) / MILLISECONDS_IN_A_DAY);
    }
}