/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package com.clinichelper.Controller;

import com.clinichelper.Entity.Patient;
import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import com.clinichelper.Service.ToolKitService;
import com.clinichelper.Tools.Enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Controller
public class PatientController {
    // Repositories
    @Autowired
    private DataEntryAndManagementService DEAMS;
    @Autowired
    private DataQueryService DQS;
    @Autowired
    private ToolKitService TKS;

    // Gets
    @GetMapping("/patients")
    public ModelAndView fetchAllPatientsView(Model model){
        if (!DQS.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        String clinicId = DQS.getCurrentLoggedUser().getClinic().getClinicId();

        model.addAttribute("todoList", TKS.InitializeTodoList(clinicId));
        model.addAttribute("patientList", DQS.findAllRegisteredPatientsForClinic(clinicId));
        model.addAttribute("amount", DQS.findAllRegisteredPatientsForClinic(clinicId).size());

        return new ModelAndView("patients/allPatients");
    }

    @GetMapping("/patient/{id}")
    public ModelAndView fetchPatientview(Model model,@PathVariable(value="id") String patientId){
        if (!DQS.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        model.addAttribute("todoList", TKS.InitializeTodoList(DQS.getCurrentLoggedUser().getClinic().getClinicId()));
        model.addAttribute("patient", DQS.findRegisteredPatient(patientId));
        model.addAttribute("appointments", DQS.findPatientsRegisteredAppointments(patientId));
        return new ModelAndView("patients/patientsProfile");
    }

    // Posts
    @PostMapping("/newPatient")
    public String registerNewPatient(
           /* @RequestParam("clinic") String clinicId,*/
            @RequestParam("firstName") String firstName,
           @RequestParam("lastName") String lastName,
           @RequestParam("idCard") String idCard,
           @RequestParam("email") String mail,
           @RequestParam("telephoneNumber") String telephoneNumber,
           @RequestParam("workphone") String patientWorkphone,
           @RequestParam("cellphone") String patientCellphone,
           @RequestParam("contactName") String patientContactName,
           @RequestParam("contactLastName") String patientContactLastName,
           @RequestParam("contactAddress") String patientContactAddress,
           @RequestParam("contactCellphone") String patientContactCellphone,
           @RequestParam("contactTelephoneNumber") String contactTelephoneNumber,
           @RequestParam("address") String address,
           @RequestParam("occupation") String occupation,
           @RequestParam("dateOfBirth")String dateOfBirth,
           @RequestParam("gender") String gender,
           @RequestParam("nationality") String nationality,
           @RequestParam("country") String countries,
           @RequestParam("city") String cities,
           @RequestParam("allergies")ArrayList<String> patientAllergies,
           @RequestParam("religion") String patientReligion,
           @RequestParam("height") String patientHeight,
           @RequestParam("weight") String patientWeight,
           @RequestParam("bloodType") String patientBloodType,
           @RequestParam("conditions")ArrayList<String> patientConditions
           ){

        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");

            DEAMS.createNewPatient("CH-PLATINUM-JASC", firstName, lastName, idCard, telephoneNumber, patientWorkphone, patientCellphone, patientContactName, patientContactLastName,
                    patientContactAddress, patientContactCellphone, contactTelephoneNumber, occupation, gender.toUpperCase().equals("F") ? Gender.F : Gender.M, mail,
                    new Date(sdf1.parse(dateOfBirth).getTime()), nationality, address, cities, countries, patientAllergies, patientReligion,
                    patientHeight, patientWeight, patientBloodType,
                    patientConditions

            );
            return "redirect:/patients";
        } catch (PersistenceException | IllegalArgumentException | NullPointerException exp){
            System.out.println("ERROR EN CREAR PACIENTE");
        } catch (Exception exp){
            System.out.println("ERROR EN CREAR PACIENTE");
        }

        return "redirect:/patients"; // TODO: implement exception handeling
    }

    @PostMapping("/editPatient")
    public String editPatient(@RequestParam("id") String patientId, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("idCard") String idCard, @RequestParam("mail") String mail, @RequestParam("telephoneNumber") String telephoneNumber, @RequestParam("contactTelephoneNumber") String contactTelephoneNumber, @RequestParam("address") String address, @RequestParam("occupation") String occupation, @RequestParam("dateOfBirth")Date dateOfBirth, @RequestParam("gender") String gender, @RequestParam("nationality") String nationality, @RequestParam("countries") String countries, @RequestParam("cities") String cities){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        try {
            Patient patient = DQS.findRegisteredPatient(patientId);

            patient.setPatientFirstName(firstName.toLowerCase());
            patient.setPatientLastName(lastName.toUpperCase());
            patient.setPatientIdCard(idCard);
            patient.setPatientBirthDate(dateOfBirth);
            patient.setPatientEmail(mail.toLowerCase());
            patient.setPatientTelephoneNumber(telephoneNumber);
            patient.setPatientContactTelephoneNumber(contactTelephoneNumber);
            patient.setPatientAddress(address.toUpperCase());
            patient.setOccupation(occupation.toUpperCase());
            patient.setPatientGender(gender.toUpperCase().equals("F") ? Gender.F : Gender.M);
            patient.setPatientNationality(nationality.toUpperCase());
            patient.setPatientCountry(countries.toUpperCase());
            patient.setPatientCity(cities.toUpperCase());
            DEAMS.editPatient(patient);
            return "redirect:/patient" + patient.getPatientId();
        } catch (PersistenceException | IllegalArgumentException | NullPointerException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/patient/" + patientId; // TODO: Implement Error exceptions for edit patient
    }

    @PostMapping("/uploadPhoto")
    public String uploadPatientPhoto(@RequestParam("id") String patientId ,@RequestParam("photo")MultipartFile file){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";
        Patient patient = DQS.findRegisteredPatient(patientId);

        try {
            patient.setPatientPhoto(processImageFile(file.getBytes()));

            DEAMS.editPatient(patient);
            return "redirect:/patient/" + patient.getPatientId();
        } catch (PersistenceException | IllegalArgumentException | NullPointerException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/patient/" + patientId;// TODO: Implement Error exceptions for upload patient photo
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
