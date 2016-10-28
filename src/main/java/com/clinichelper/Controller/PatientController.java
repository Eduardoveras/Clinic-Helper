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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class PatientController {
    // Repositories
    @Autowired
    private DataEntryAndManagementService DEAMS;
    @Autowired
    private DataQueryService DQS;


    @GetMapping("/patients")
    public ModelAndView fetchAllPatientsView(Model model){

        model.addAttribute("patientList", DQS.findAllRegisteredPatients());
        model.addAttribute("amount", DQS.findAllRegisteredPatients().size());

        return new ModelAndView("allPatients");
    }

    @GetMapping("/patient")
    public ModelAndView fetchPatientview(Model model, @RequestParam("jascId") String jascId){

        model.addAttribute("patient", DQS.findRegisteredPatient(jascId));
        model.addAttribute("appointments", DQS.findPatientsRegisteredAppointments(jascId));
        model.addAttribute("today", new Date(Calendar.getInstance().getTime().getTime()));

        return new ModelAndView("");
    }

    // Posts
    @PostMapping("/newPatient")
    public String registerNewPatient(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("idCard") String idCard,
            @RequestParam("email") String mail,
            @RequestParam("telephoneNumber") String telephoneNumber,
            @RequestParam("contactTelephoneNumber") String contactTelephoneNumber,
            @RequestParam("address") String address,
            @RequestParam("occupation") String occupation,
            @RequestParam("dateOfBirth")String dateOfBirth,
            @RequestParam("gender") String gender,
            @RequestParam("nationality") String nationality,
            @RequestParam("countries") String countries,
            @RequestParam("cities") String cities){
        System.out.println("THIS IS A PROOF THAT YOLO IS SWAG");

        try {

            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");

            DEAMS.createNewPatient(firstName, lastName, idCard, telephoneNumber,
                    contactTelephoneNumber, occupation, gender, mail, new Date(sdf1.parse(dateOfBirth).getTime()), nationality, address, cities, countries);
        } catch (PersistenceException | IllegalArgumentException | NullPointerException exp){
            System.out.println("ERROR EN CREAR PACIENTE");
        } catch (Exception exp){
            System.out.println("ERROR EN CREAR PACIENTE");
        }

        return "redirect:/patients";
    }

    @PostMapping("/editPatient")
    public String deletePatient(@RequestParam("jascId") String jascId, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("idCard") String idCard, @RequestParam("mail") String mail, @RequestParam("telephoneNumer") String telephoneNumber, @RequestParam("contactTelephoneNumber") String contactTelephoneNumber, @RequestParam("address") String address, @RequestParam("occupation") String occupation, @RequestParam("dateOfBirth")Date dateOfBirth, @RequestParam("gender") String gender, @RequestParam("nationality") String nationality, @RequestParam("countries") String countries, @RequestParam("cities") String cities){

        try {

            Patient patient = DQS.findRegisteredPatient(jascId);
            patient.setPatientFirstName(firstName.toLowerCase());
            patient.setPatientLastName(lastName.toUpperCase());
            patient.setPatientIdCard(idCard);
            patient.setPatientBirthDate(dateOfBirth);
            patient.setPatientEmail(mail.toLowerCase());
            patient.setPatientTelephoneNumber(telephoneNumber);
            patient.setPatientContactTelephoneNumber(contactTelephoneNumber);
            patient.setPatientAddress(address.toUpperCase());
            patient.setOccupation(occupation.toUpperCase());
            patient.setPatientGender(gender.toUpperCase());
            patient.setPatientNationality(nationality.toUpperCase());
            patient.setPatientCountry(countries.toUpperCase());
            patient.setPatientCity(cities.toUpperCase());
            DEAMS.editPatient(patient);
        } catch (PersistenceException exp){
            //
        } catch(IllegalArgumentException exp){
            //
        } catch (NullPointerException exp) {
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/patient";
    }

    @PostMapping("/uploadPhoto")
    public String uploadPatientPhoto(@RequestParam("jascId") String jascId ,@RequestParam("photo")MultipartFile file){
        Patient patient = DQS.findRegisteredPatient(jascId);

        try {

            patient.setPatientPhoto(processImageFile(file.getBytes()));

            DEAMS.editPatient(patient);

        } catch (PersistenceException exp){
            //
        } catch(IllegalArgumentException exp){
            //
        } catch (NullPointerException exp) {
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/patient";
    }

    //Auxiliary Functions
    private Byte[] processImageFile(byte[] buffer) {
        Byte[] bytes = new Byte[buffer.length];
        int i = 0;

        for (byte b :
                buffer)
            bytes[i++] = b; // Autoboxing

        return bytes;
    }
}
