/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package com.clinichelper.Controller;

import com.clinichelper.Entity.Patient;
import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import java.sql.Date;

@Controller
public class PatientController {
    @Autowired
    private DataEntryAndManagementService DEAMS;
    @Autowired
    private DataQueryService DQS;
    @RequestMapping("/solicit")
    public ModelAndView solicit(Model model){

        return new ModelAndView("newpatient");
    }

    @GetMapping("/patient")
    public ModelAndView fetchPatientView(Model model){

        model.addAttribute("patientList", DQS.findAllRegisteredPatients());

        return new ModelAndView("newpatient");
    }
    @PostMapping("/editPatient")
    public String deletePatient(@RequestParam("jascId") String jascId, @RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("idCard") String idCard,
                                @RequestParam("mail") String mail,
                                @RequestParam("telephoneNumer") String telephoneNumber,
                                @RequestParam("contactTelephoneNumber") String contactTelephoneNumber,
                                @RequestParam("address") String address,
                                @RequestParam("occupation") String occupation,
                                @RequestParam("dateOfBirth")Date dateOfBirth,
                                @RequestParam("gender") String gender,
                                @RequestParam("nationality") String nationality,
                                @RequestParam("countries") String countries,
                                @RequestParam("cities") String cities){
        Patient patient = DQS.findRegisteredPatient(jascId);
        patient.setPatientFirstName(firstName);
        patient.setPatientLastName(lastName);
        patient.setPatientIdCard(idCard);
        patient.setPatientBirthDate(dateOfBirth);
        patient.setPatientEmail(mail);
        patient.setPatientTelephoneNumber(telephoneNumber);
        patient.setPatientContactTelephoneNumber(contactTelephoneNumber);
        patient.setPatientAddress(address);
        patient.setOccupation(occupation);
        patient.setPatientGender(gender);
        patient.setPatientNationality(nationality);
        patient.setPatientCountry(countries);
        patient.setPatientCity(cities);
        try {
            DEAMS.editPatient(patient);
        }
        catch (PersistenceException exp){
            //
        } catch (NullPointerException exp) {
            //
        } catch (Exception exp){
            //
        }
        return "redirect:/patient";
    }
    @PostMapping("/newPatient")
    public String registerNewPatient(@RequestParam("firstName") String firstName,
                                         @RequestParam("lastName") String lastName,
                                         @RequestParam("idCard") String idCard,
                                         @RequestParam("mail") String mail,
                                         @RequestParam("telephoneNumer") String telephoneNumber,
                                         @RequestParam("contactTelephoneNumber") String contactTelephoneNumber,
                                         @RequestParam("address") String address,
                                         @RequestParam("occupation") String occupation,
                                         @RequestParam("dateOfBirth")Date dateOfBirth,
                                         @RequestParam("gender") String gender,
                                         @RequestParam("nationality") String nationality,
                                         @RequestParam("countries") String countries,
                                         @RequestParam("cities") String cities
                                        ){
        try{
        DEAMS.createNewPatient(firstName, lastName, idCard, telephoneNumber,
                contactTelephoneNumber, occupation, gender, mail, dateOfBirth, nationality, address, cities, countries);
    }
         catch (PersistenceException exp){
             //
        } catch (NullPointerException exp) {
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/";
    }


}
