/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package com.clinichelper.Controller;

import com.clinichelper.Entity.Patient;
import com.clinichelper.Service.Remote.AmazonService;
import com.clinichelper.Service.CRUD.DataCreationService;
import com.clinichelper.Service.CRUD.DataUpdateService;
import com.clinichelper.Service.CRUD.Reading.AppointmentConsultationSurgeryService;
import com.clinichelper.Service.CRUD.Reading.PatientInformationService;
import com.clinichelper.Service.Security.SessionService;
import com.clinichelper.Service.Native.ToolKitService;
import com.clinichelper.Tools.Enums.Gender;
import com.clinichelper.Tools.Enums.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Controller
public class PatientController {

    // Services
    // CRUD
    @Autowired
    private DataCreationService DCS;
    @Autowired
    private DataUpdateService DUS;
    @Autowired
    private AppointmentConsultationSurgeryService ACSS;
    @Autowired
    private PatientInformationService PIS;
    //Security
    @Autowired
    private SessionService sessionService;
    //
    @Autowired
    private ToolKitService TKS;
    @Autowired
    private AmazonService AS;

    // Gets
    @GetMapping("/patients")
    public ModelAndView fetchAllPatientsView(Model model){
        if (!sessionService.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        //if (DQS.getCurrentLoggedUser().getRole() == Permission.ADMIN)
           // return new ModelAndView("redirect:/");

        String clinicId = sessionService.getCurrentLoggedUser().getClinic().getClinicId();

        model.addAttribute("todoList", TKS.InitializeTodoList(sessionService.getCurrentLoggedUser().getUserId()));
        model.addAttribute("patientList", PIS.findAllRegisteredPatientsForClinic(clinicId));
        model.addAttribute("amount", PIS.findAllRegisteredPatientsForClinic(clinicId).size());

        if (sessionService.getCurrentLoggedUser().getRole() != Permission.ASSISTANT)
            model.addAttribute("canUse", false);
        else
            model.addAttribute("canUse", true);

        //model.addAttribute("isAdmin", false);


        if (sessionService.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            model.addAttribute("isAdmin", false);
        else
            model.addAttribute("isAdmin", true);

        if (sessionService.getCurrentLoggedUser().getRole() != Permission.MEDIC)
            return new ModelAndView("patients/allPatients");
        else
            return new ModelAndView(""); // TODO: create a patient view fit for the doctor
    }

    @GetMapping("/new_patient")
    public ModelAndView patientForm(Model model){

        if (!sessionService.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        //if (DQS.getCurrentLoggedUser().getRole() != Permission.ASSISTANT)
            //return new ModelAndView("redirect:/");

        String clinicId = sessionService.getCurrentLoggedUser().getClinic().getClinicId();

        model.addAttribute("todoList", TKS.InitializeTodoList(sessionService.getCurrentLoggedUser().getUserId()));
        model.addAttribute("amount", PIS.findAllRegisteredPatientsForClinic(clinicId).size());
        //model.addAttribute("isAdmin", false);

        if (sessionService.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            model.addAttribute("isAdmin", false);
        else
            model.addAttribute("isAdmin", true);

        return new ModelAndView("patients/patientForm");
    }

    @GetMapping("/patient/{id}")
    public ModelAndView fetchPatientview(Model model, @PathVariable(value="id") String patientId){

        if (!sessionService.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        //if (DQS.getCurrentLoggedUser().getRole() == Permission.ADMIN)
            //return new ModelAndView("redirect:/");

        model.addAttribute("todoList", TKS.InitializeTodoList(sessionService.getCurrentLoggedUser().getUserId()));
        model.addAttribute("patient", PIS.findRegisteredPatient(patientId));
        model.addAttribute("appointments", ACSS.findPatientsRegisteredAppointments(patientId));
        //model.addAttribute("isAdmin", false);

        if (sessionService.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            model.addAttribute("isAdmin", false);
        else
            model.addAttribute("isAdmin", true);

        return new ModelAndView("patients/patientsProfile");
    }

    // Posts
    @PostMapping("/newPatient")
    public String registerNewPatient(
           @RequestParam("firstName") String firstName,
           @RequestParam("lastname") String lastName,
           @RequestParam("idCard") String idCard,
           @RequestParam("dateofbirth")String dateOfBirth,
           @RequestParam("gender") String gender,
           @RequestParam("occupation") String occupation,
           @RequestParam("religion") String patientReligion,
           @RequestParam("country") String countries,
           @RequestParam("state") String cities,
           @RequestParam("address") String address,
           @RequestParam("nationality") String nationality,
           @RequestParam("bloodtype") String patientBloodType,
           @RequestParam("allergies")ArrayList<String> patientAllergies,
           @RequestParam("conditions")ArrayList<String> patientConditions,
           @RequestParam("contactname") String patientContactName,
           @RequestParam("contactlastname") String patientContactLastName,
           @RequestParam("contactAddress") String patientContactAddress,
           @RequestParam("contacttelephonenumber") String contactTelephoneNumber,
           @RequestParam("contactCellphone") String patientContactCellphone,
           @RequestParam("telephonenumber") String telephoneNumber,
           @RequestParam("workphone") String patientWorkphone,
           @RequestParam("cellphone") String patientCellphone,
           @RequestParam("email") String mail,
           @RequestParam("insurancecode") String insuranceCode,
           @RequestParam("supplier") String supplier,
           @RequestParam("plan") String insurancePlan){

        if (!sessionService.isUserLoggedIn())
            return "redirect:/login";

        //if (DQS.getCurrentLoggedUser().getRole() != Permission.ASSISTANT)
          //  return "redirect:/";

        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");

            DCS.createNewPatient(sessionService.getCurrentLoggedUser().getClinic().getClinicId(), firstName, lastName, idCard, telephoneNumber, patientWorkphone, patientCellphone, patientContactName, patientContactLastName,
                    patientContactAddress, patientContactCellphone, contactTelephoneNumber, occupation, gender.toUpperCase().equals("F") ? Gender.F : Gender.M, mail,
                    new Date(sdf1.parse(dateOfBirth).getTime()), nationality, address, cities, countries, patientAllergies, patientReligion,
                    "0", "0", patientBloodType,
                    patientConditions, insuranceCode, supplier, insurancePlan);
            return "redirect:/patients";
        } catch (Exception exp){
            System.out.println("ERROR EN CREAR PACIENTE");
            exp.printStackTrace();
        }

        return "redirect:/patients"; // TODO: implement exception handeling
    }

    @PostMapping("/editPatient")
    public String editPatient(@RequestParam("id") String patientId, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("idCard") String idCard, @RequestParam("mail") String mail, @RequestParam("telephoneNumber") String telephoneNumber, @RequestParam("contactTelephoneNumber") String contactTelephoneNumber, @RequestParam("address") String address, @RequestParam("occupation") String occupation, @RequestParam("dateOfBirth")Date dateOfBirth, @RequestParam("gender") String gender, @RequestParam("nationality") String nationality, @RequestParam("countries") String countries, @RequestParam("cities") String cities){

        if (!sessionService.isUserLoggedIn())
            return "redirect:/login";

        try {
            Patient patient = PIS.findRegisteredPatient(patientId);

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
            DUS.editPatient(patient);
            return "redirect:/patient" + patient.getPatientId();
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/patient/" + patientId; // TODO: Implement Error exceptions for edit patient
    }

    @PostMapping("/uploadPhoto")
    public String uploadPatientPhoto(@RequestParam("id") String patientId ,@RequestParam("photo")MultipartFile file){

        if (!sessionService.isUserLoggedIn())
            return "redirect:/login";

        Patient patient = PIS.findRegisteredPatient(patientId);

        try {
            File newFile= convertToFile(file);
            AS.UploadImageToAWS(patient.getPatientId(), newFile);
            return "redirect:/patient/" + patient.getPatientId();
        } catch (Exception exp){
            exp.printStackTrace();
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

    private File convertToFile(MultipartFile file){
        try{
            File converted = new File(file.getOriginalFilename());

            //converted.createNewFile();
            //FileOutputStream fos = new FileOutputStream(converted);
            //fos.write(file.getBytes());
            //fos.close();

            file.transferTo(converted);

            return converted;
        } catch (Exception exp){
            //
        }

        return null;
    }
}
