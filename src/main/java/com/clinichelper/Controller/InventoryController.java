/**
 * Created by Djidjelly Siclait on 11/1/2016.
 */
package com.clinichelper.Controller;

import com.clinichelper.Entity.Equipment;
import com.clinichelper.Entity.Medication;
import com.clinichelper.Entity.Product;
import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import com.clinichelper.Service.ToolKitService;
import com.clinichelper.Tools.Enums.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Map;

@Controller
public class InventoryController {
    // Repositories
    @Autowired
    private DataEntryAndManagementService DEAMS;
    @Autowired
    private DataQueryService DQS;
    @Autowired
    private ToolKitService TKS;

    // Gets
    @RequestMapping("/Inventory")
    public ModelAndView FetchInventoryView(Model model){
        if (!DQS.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        if (DQS.getCurrentLoggedUser().getRole() == Permission.MEDIC)
            return new ModelAndView("redirect:/");

        String clinicId = DQS.getCurrentLoggedUser().getClinic().getClinicId();

        Map<String, List> inventory = TKS.FetchClinicInventory(clinicId);
        model.addAttribute("todoList", TKS.InitializeTodoList(DQS.getCurrentLoggedUser().getUserId()));
        model.addAttribute("equipmentList", inventory.get("equipments"));
        model.addAttribute("eSize", inventory.get("equipments").size());
        model.addAttribute("productList", inventory.get("products"));
        model.addAttribute("pSize", inventory.get("products").size());
        model.addAttribute("medicationList", inventory.get("medication"));
        model.addAttribute("mSize", inventory.get("medication").size());

        if (DQS.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            model.addAttribute("isAdmin", false);
        else
            model.addAttribute("isAdmin", true);

        return new ModelAndView("inventory/viewInventory");
    }

    // Posts
    // Creates
    @PostMapping("/newEquipment")
    public String registerNewEquipment(@RequestParam("name") String equipmentName, @RequestParam("use") String equipmentUse, @RequestParam("description") String equipmentDescription, @RequestParam("quantity") Integer stock){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        if (DQS.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            return "redirect:/";

        try {
            DEAMS.createNewEquipment(DQS.getCurrentLoggedUser().getClinic().getClinicId(), equipmentName, equipmentUse, equipmentDescription, stock);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }
        return "redirect:/Inventory"; // TODO: implement error exception
    }

    @PostMapping("/newProduct")
    public String registerNewProduct(@RequestParam("name") String productName, @RequestParam("supplier") String supplier, @RequestParam("description") String productDescription, @RequestParam("price") Float productPrice, @RequestParam("quantity") Integer stock){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        if (DQS.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            return "redirect:/";

        try {
            DEAMS.createNewProduct(DQS.getCurrentLoggedUser().getClinic().getClinicId(), productName, supplier, productDescription, productPrice, stock);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    @PostMapping("/newMedication")
    public String registerNewMedication(@RequestParam("name")  String medicationName, @RequestParam("supplier") String supplier, @RequestParam("description") String medicationDescription, @RequestParam("price") Float medicationPrice, @RequestParam("quantity") Integer stock){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        if (DQS.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            return "redirect:/";

        try {
            DEAMS.createNewMedication(DQS.getCurrentLoggedUser().getClinic().getClinicId(), medicationName, supplier, medicationDescription, medicationPrice, stock);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    // Deletes
    @PostMapping("/deleteEquipment")
    public String deleteEquipment(@RequestParam("id") String equipmentId){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        try{
            DEAMS.deleteRegisteredEquipment(equipmentId);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    @PostMapping("/deleteMedication")
    public String deleteMedication(@RequestParam("id") String medicationId){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";


        try {
            DEAMS.deleteRegisteredMedication(medicationId);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("id") String productId){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";


        try{
            DEAMS.deleteRegisteredProduct(productId);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    // Restock
    @PostMapping("/restockEquipment")
    public String restockEquipment(@RequestParam("id") String equipmentId, @RequestParam("quantity") Integer stock){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";


        try {
            Equipment equipment = DQS.findRegisteredEquipment(equipmentId);
            equipment.setEquipmentInStock(stock);
            DEAMS.editEquipment(equipment);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    @PostMapping("/restockMedication")
    public String restockMedication(@RequestParam("id") String medicationId, @RequestParam("quantity") Integer stock){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";


        try {
            Medication medication = DQS.findRegisteredMedication(medicationId);
            medication.setMedicationInStock(stock);
            DEAMS.editMedication(medication);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    @PostMapping("/restockProduct")
    public String restockProduct(@RequestParam("id") String productId, @RequestParam("quantity") Integer stock){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";


        try {
            Product product = DQS.findRegisteredProduct(productId);
            product.setProductInStock(stock);
            DEAMS.editProduct(product);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    @PostMapping("/editEquipmentInformation")
    public String editEquipmentInformation(@RequestParam("id") String equipmentId, @RequestParam("name") String name, @RequestParam("use") String use, @RequestParam("description") String description){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";


        try {
            Equipment equipment = DQS.findRegisteredEquipment(equipmentId);
            equipment.setEquipmentName(name);
            equipment.setEquipmentUse(use);
            equipment.setEquipmentDescription(description);
            DEAMS.editEquipment(equipment);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    @PostMapping("/editMedicationInformation")
    public String editMedicationInformation(@RequestParam("id") String medicationId, @RequestParam("name") String name, @RequestParam("supplier") String supplier, @RequestParam("description") String description, @RequestParam("price") Float price){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";


        try {
            Medication medication = DQS.findRegisteredMedication(medicationId);
            medication.setMedicationName(name);
            medication.setSupplier(supplier);
            medication.setMedicationDescription(description);
            medication.setMedicationPrice(price);
            DEAMS.editMedication(medication);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    @PostMapping("/editProductInformation")
    public String editProductInformation(@RequestParam("id") String productId, @RequestParam("name") String name, @RequestParam("supplier") String supplier, @RequestParam("description") String description, @RequestParam("price") Float price){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        try {
            Product product = DQS.findRegisteredProduct(productId);
            product.setProductName(name);
            product.setProductDescription(description);
            product.setSupplier(supplier);
            product.setProductPrice(price);
            DEAMS.editProduct(product);
            return "redirect:/Inventory";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }
}
