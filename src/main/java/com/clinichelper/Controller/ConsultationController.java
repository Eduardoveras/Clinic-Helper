package com.clinichelper.Controller;

import com.clinichelper.Entity.Consultation;
import com.clinichelper.Entity.History;
import com.clinichelper.Entity.Patient;
import com.clinichelper.Entity.Record;
import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by eva_c on 11/6/2016.
 */
@Controller
public class ConsultationController {
    // Services
    @Autowired
    private DataEntryAndManagementService DEAMS;
    @Autowired
    private DataQueryService DQS;

    // Gets
    @GetMapping("/consultations")
    public ModelAndView fetchConsultationView(Model model) throws Exception{

        if (!DQS.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        //if (DQS.getCurrentLoggedUser().getRole() != Permission.MEDIC)
           // return new ModelAndView("redirect:/");

        String clinicId = DQS.getCurrentLoggedUser().getClinic().getClinicId();

        model.addAttribute("consultationList", DQS.findAllRegisteredConsultationsForClinic(clinicId));

        return new ModelAndView("consultations/allConsultations");
    }

    // Posts
    @PostMapping("/complete_consultation_process/{consultationId}")
    public String completeConsultationProcess(
            @PathVariable("consultationId") String consultationId, // This should be a hidden input
            @RequestParam("patient") String patientId,
            @RequestParam("visitobjective") String visitObjective,
            @RequestParam("observations") String observations,
            @RequestParam("specialconditions") String specialConditions,
            @RequestParam("photos") ArrayList<byte[]> photos,
            @RequestParam("surgerytype") String surgeryType,
            @RequestParam("medicaldata")  ArrayList<String> medicaData){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        //if (DQS.getCurrentLoggedUser().getRole() != Permission.MEDIC)
          //  return "redirect:/";

        try {
            Patient patient = DQS.findRegisteredPatientByIdCard(DQS.getCurrentLoggedUser().getClinic().getClinicId(), patientId);
            // Creating the history
            History history = DEAMS.createNewHistory(patient, visitObjective, observations, specialConditions, photos, surgeryType, medicaData, consultationId);

            // Adding it to the Record
            Record record = DQS.findPatientsRegisteredRecord(patient.getPatientId());

            // Fetching the list of history
            Set<History> medicalHistory = record.getHistory();
            // Adding the new one
            medicalHistory.add(history);
            // Modifying the old list
            record.setHistory(medicalHistory);

            // Also saving the consultation
            Set<Consultation> consultationsHistory = record.getConsultations();
            consultationsHistory.add(DQS.findRegisteredConsultation(consultationId));
            record.setConsultations(consultationsHistory);

            // Edit patient medical record
            DEAMS.editRecord(record);

            return "redirect:/"; // todavia no hay vista
        } catch (PersistenceException | IllegalArgumentException | NullPointerException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/"; // TODO: add error message handling
    }
}
