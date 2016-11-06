/**
 * Created by Djidjelly Siclait on 11/1/2016.
 */
package com.clinichelper.Controller;

import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import com.clinichelper.Service.ToolKitService;
import freemarker.template.utility.NullArgumentException;
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
    public ModelAndView FetchInventoryView(Model model/*, @RequestParam("clinic") String clinicId*/){
        if (!DQS.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        String clinicId = DQS.getCurrentLoggedUser().getClinic().getClinicId();

        Map<String, List> inventory = TKS.FetchClinicInventory(clinicId);
        model.addAttribute("todoList", TKS.InitializeTodoList(clinicId));
        model.addAttribute("equipmentList", inventory.get("equipments"));
        model.addAttribute("productList", inventory.get("products"));
        model.addAttribute("medicationList", inventory.get("medication"));

        return new ModelAndView("inventory/viewInventory");
    }


    // Posts
    @PostMapping("/newEquipment")
    public String registerNewEquipment(@RequestParam("name") String equipmentName, @RequestParam("use") String equipmentUse, @RequestParam("description") String equipmentDescription, @RequestParam("quantity") Integer stock){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        try {
            DEAMS.createNewEquipment(DQS.getCurrentLoggedUser().getClinic().getClinicId(), equipmentName, equipmentUse, equipmentDescription, stock);
            return "redirect:/inventory";
        } catch (PersistenceException | NullPointerException | IllegalArgumentException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/inventory"; // TODO: implement error exception
    }

    @PostMapping("/newProduct")
    public String registerNewProduct(@RequestParam("name") String productName, @RequestParam("description") String productDescription, @RequestParam("price") Float productPrice, @RequestParam("quantity") Integer stock){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        try {
            DEAMS.createNewProduct(DQS.getCurrentLoggedUser().getClinic().getClinicId(), productName, productDescription, productPrice, stock);
        } catch (PersistenceException | NullPointerException | IllegalArgumentException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/inventory"; // TODO: implement error exception
    }

    @PostMapping("/newMedication")
    public String registerNewMedication(@RequestParam("name")  String medicationName, @RequestParam("supplier") String supplier, @RequestParam("description") String medicationDescription, @RequestParam("price") Float medicationPrice, @RequestParam("quantity") Integer stock){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        try {
            DEAMS.createNewMedication(DQS.getCurrentLoggedUser().getClinic().getClinicId(), medicationName, supplier, medicationDescription, medicationPrice, stock);
        } catch (PersistenceException | NullPointerException | IllegalArgumentException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/inventory"; // TODO: implement error exception
    }

    @PostMapping("/deleteEquipment")
    public String deleteEquipment(@RequestParam("id") String equipmentId){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        try{
            DEAMS.deleteRegisteredEquipment(equipmentId);
        } catch (PersistenceException | NullPointerException | IllegalArgumentException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/inventory"; // TODO: implement error exception
    }
}
