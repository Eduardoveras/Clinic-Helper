package com.clinichelper.Controller;

import com.clinichelper.Entity.Appointment;
import com.clinichelper.Entity.Consultation;
import com.clinichelper.Entity.History;
import com.clinichelper.Entity.Patient;
import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import com.clinichelper.Tools.Enums.AppointmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by eva_c on 11/6/2016.
 */
public class ConsultationController {
    @Autowired
    private DataEntryAndManagementService DEAMS;
    @Autowired
    private DataQueryService DQS;

    // Gets
    @GetMapping("/consultation")
    public ModelAndView fetchConsultationView(Model model) throws Exception{
        if (!DQS.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        String clinicId = DQS.getCurrentLoggedUser().getClinic().getClinicId();

        model.addAttribute("consultationList", DQS.findAllRegisteredConsultationsForClinic(clinicId));

        return new ModelAndView("t");
    }

    // Posts
    @PostMapping("/newConsultation")
    public String createNewconsultation(

                                        @RequestParam("consultationDate") String consultationDate,
                                        @RequestParam("consultationTime") String consultationTime,
                                        @RequestParam("consultationdetail") String consultationDetail,
                                        @RequestParam("consultationAppointment") String consultationAppointment

                                        ){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        try {
            String clinicId = DQS.getCurrentLoggedUser().getClinic().getClinicId();
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy hh:mm a");


            DEAMS.createNewConsultation(new SimpleDateFormat(new Timestamp(sdf1.parse(consultationDate + " 23:59:00").getTime()),new Timestamp(sdf1.parse(consultationTime).getTime()),consultationDetail,consultationAppointment);


            return "redirect:/"; // todavia no hay vista
        } catch (PersistenceException | IllegalArgumentException | NullPointerException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/"; // TODO: add error message handling
    }

    @PostMapping("/newhistory")
    public String createNewHistory(

            @RequestParam("patient") String patientId,
            @RequestParam("visitobjective") String visitObjective,
            @RequestParam("observations") String observations,
            @RequestParam("specialconditions") String specialConditions,
            @RequestParam("photos") ArrayList<byte[]> photos,
            @RequestParam("surgerytype") String surgeryType,
            @RequestParam("medicaldata")  ArrayList<String> medicaData

    ){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        try {

            String clinicId = DQS.getCurrentLoggedUser().getClinic().getClinicId();
            Patient patient = DQS.findRegisteredPatientByIdCard(clinicId, patientId);
            DEAMS.createNewHistory(patient,visitObjective,observations,specialConditions,photos,surgeryType,medicaData);

            return "redirect:/"; // todavia no hay vista
        } catch (PersistenceException | IllegalArgumentException | NullPointerException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/"; // TODO: add error message handling
    }
}
