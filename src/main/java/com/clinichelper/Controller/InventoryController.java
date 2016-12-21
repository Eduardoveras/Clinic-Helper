/**
 * Created by Djidjelly Siclait on 11/1/2016.
 */
package com.clinichelper.Controller;

import com.clinichelper.Entity.Equipment;
import com.clinichelper.Entity.Medication;
import com.clinichelper.Entity.Product;
import com.clinichelper.Service.CRUD.DataCreationService;
import com.clinichelper.Service.CRUD.DataDeleteService;
import com.clinichelper.Service.CRUD.DataUpdateService;
import com.clinichelper.Service.CRUD.Reading.EquipmentMedicationProductService;
import com.clinichelper.Service.Security.SessionService;
import com.clinichelper.Service.Native.ToolKitService;
import com.clinichelper.Tools.Enums.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class InventoryController {

    // Services
    // CRUD
    @Autowired
    private DataCreationService DCS;
    @Autowired
    private DataUpdateService DUS;
    @Autowired
    private DataDeleteService DDS;
    @Autowired
    private EquipmentMedicationProductService EMPS;
    // Security
    @Autowired
    private SessionService sessionService;
    //
    @Autowired
    private ToolKitService TKS;

    // Gets
    @RequestMapping("/Inventory")
    public ModelAndView FetchInventoryView(Model model){
        if (!sessionService.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        if (sessionService.getCurrentLoggedUser().getRole() == Permission.MEDIC)
            return new ModelAndView("redirect:/");

        String clinicId = sessionService.getCurrentLoggedUser().getClinic().getClinicId();

        Map<String, List> inventory = TKS.FetchClinicInventory(clinicId);
        model.addAttribute("todoList", TKS.InitializeTodoList(sessionService.getCurrentLoggedUser().getUserId()));
        model.addAttribute("equipmentList", inventory.get("equipments"));
        model.addAttribute("eSize", inventory.get("equipments").size());
        model.addAttribute("productList", inventory.get("products"));
        model.addAttribute("pSize", inventory.get("products").size());
        model.addAttribute("medicationList", inventory.get("medication"));
        model.addAttribute("mSize", inventory.get("medication").size());

        if (sessionService.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            model.addAttribute("isAdmin", false);
        else
            model.addAttribute("isAdmin", true);

        return new ModelAndView("inventory/viewInventory");
    }

    // Posts
    // Creates
    @PostMapping("/newEquipment")
    public String registerNewEquipment(@RequestParam("name") String equipmentName, @RequestParam("use") String equipmentUse, @RequestParam("description") String equipmentDescription, @RequestParam("quantity") Integer stock){

        if (!sessionService.isUserLoggedIn())
            return "redirect:/login";

        if (sessionService.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            return "redirect:/";

        try {
            DCS.createNewEquipment(sessionService.getCurrentLoggedUser().getClinic().getClinicId(), equipmentName, equipmentUse, equipmentDescription, stock);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }
        return "redirect:/Inventory"; // TODO: implement error exception
    }

    @PostMapping("/newProduct")
    public String registerNewProduct(@RequestParam("name") String productName, @RequestParam("supplier") String supplier, @RequestParam("description") String productDescription, @RequestParam("price") Float productPrice, @RequestParam("quantity") Integer stock){

        if (!sessionService.isUserLoggedIn())
            return "redirect:/login";

        if (sessionService.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            return "redirect:/";

        try {
            DCS.createNewProduct(sessionService.getCurrentLoggedUser().getClinic().getClinicId(), productName, supplier, productDescription, productPrice, stock);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    @PostMapping("/newMedication")
    public String registerNewMedication(@RequestParam("name")  String medicationName, @RequestParam("supplier") String supplier, @RequestParam("description") String medicationDescription, @RequestParam("price") Float medicationPrice, @RequestParam("quantity") Integer stock){

        if (!sessionService.isUserLoggedIn())
            return "redirect:/login";

        if (sessionService.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            return "redirect:/";

        try {
            DCS.createNewMedication(sessionService.getCurrentLoggedUser().getClinic().getClinicId(), medicationName, supplier, medicationDescription, medicationPrice, stock);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    // Deletes
    @PostMapping("/deleteEquipment")
    public String deleteEquipment(@RequestParam("id") String equipmentId){

        if (!sessionService.isUserLoggedIn())
            return "redirect:/login";

        try{
            DDS.deleteRegisteredEquipment(equipmentId);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    @PostMapping("/deleteMedication")
    public String deleteMedication(@RequestParam("id") String medicationId){
        if (!sessionService.isUserLoggedIn())
            return "redirect:/login";


        try {
            DDS.deleteRegisteredMedication(medicationId);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("id") String productId){

        if (!sessionService.isUserLoggedIn())
            return "redirect:/login";

        try{
            DDS.deleteRegisteredProduct(productId);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    // Restock
    @PostMapping("/restockEquipment")
    public String restockEquipment(@RequestParam("id") String equipmentId, @RequestParam("quantity") Integer stock){

        if (!sessionService.isUserLoggedIn())
            return "redirect:/login";

        try {
            Equipment equipment = EMPS.findRegisteredEquipment(equipmentId);
            equipment.setEquipmentInStock(stock);
            DUS.editEquipment(equipment);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    @PostMapping("/restockMedication")
    public String restockMedication(@RequestParam("id") String medicationId, @RequestParam("quantity") Integer stock){
        if (!sessionService.isUserLoggedIn())
            return "redirect:/login";


        try {
            Medication medication = EMPS.findRegisteredMedication(medicationId);
            medication.setMedicationInStock(stock);
            DUS.editMedication(medication);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    @PostMapping("/restockProduct")
    public String restockProduct(@RequestParam("id") String productId, @RequestParam("quantity") Integer stock){
        if (!sessionService.isUserLoggedIn())
            return "redirect:/login";


        try {
            Product product = EMPS.findRegisteredProduct(productId);
            product.setProductInStock(stock);
            DUS.editProduct(product);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    @PostMapping("/editEquipmentInformation")
    public String editEquipmentInformation(@RequestParam("id") String equipmentId, @RequestParam("name") String name, @RequestParam("use") String use, @RequestParam("description") String description){
        if (!sessionService.isUserLoggedIn())
            return "redirect:/login";


        try {
            Equipment equipment = EMPS.findRegisteredEquipment(equipmentId);
            equipment.setEquipmentName(name);
            equipment.setEquipmentUse(use);
            equipment.setEquipmentDescription(description);
            DUS.editEquipment(equipment);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    @PostMapping("/editMedicationInformation")
    public String editMedicationInformation(@RequestParam("id") String medicationId, @RequestParam("name") String name, @RequestParam("supplier") String supplier, @RequestParam("description") String description, @RequestParam("price") Float price){
        if (!sessionService.isUserLoggedIn())
            return "redirect:/login";


        try {
            Medication medication = EMPS.findRegisteredMedication(medicationId);
            medication.setMedicationName(name);
            medication.setSupplier(supplier);
            medication.setMedicationDescription(description);
            medication.setMedicationPrice(price);
            DUS.editMedication(medication);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    @PostMapping("/editProductInformation")
    public String editProductInformation(@RequestParam("id") String productId, @RequestParam("name") String name, @RequestParam("supplier") String supplier, @RequestParam("description") String description, @RequestParam("price") Float price){
        if (!sessionService.isUserLoggedIn())
            return "redirect:/login";

        try {
            Product product = EMPS.findRegisteredProduct(productId);
            product.setProductName(name);
            product.setProductDescription(description);
            product.setSupplier(supplier);
            product.setProductPrice(price);
            DUS.editProduct(product);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }
}
