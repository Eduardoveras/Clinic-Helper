/**
 * Created by Djidjelly Siclait on 11/1/2016.
 */
package com.clinichelper.Controller;

import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import com.clinichelper.Service.ToolKitService;
import com.clinichelper.Tools.Enums.Permission;
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

        return new ModelAndView("inventory/viewInventory");
    }


    // Posts
    @PostMapping("/newEquipment")
    public String registerNewEquipment(@RequestParam("name") String equipmentName, @RequestParam("use") String equipmentUse, @RequestParam("description") String equipmentDescription, @RequestParam("quantity") Integer stock){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        if (DQS.getCurrentLoggedUser().getRole() == Permission.MEDIC)
            return "redirect:/";

        try {
            DEAMS.createNewEquipment(DQS.getCurrentLoggedUser().getClinic().getClinicId(), equipmentName, equipmentUse, equipmentDescription, stock);
            return "redirect:/Inventory";
        } catch (PersistenceException | NullPointerException | IllegalArgumentException exp){
            //
        } catch (Exception exp){
            //
        }
        return "redirect:/Inventory"; // TODO: implement error exception
    }

    @PostMapping("/newProduct")
    public String registerNewProduct(@RequestParam("name") String productName, @RequestParam("description") String productDescription, @RequestParam("price") Float productPrice, @RequestParam("quantity") Integer stock){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        if (DQS.getCurrentLoggedUser().getRole() == Permission.MEDIC)
            return "redirect:/";

        try {
            DEAMS.createNewProduct(DQS.getCurrentLoggedUser().getClinic().getClinicId(), productName, productDescription, productPrice, stock);
            return "redirect:/Inventory";
        } catch (PersistenceException | NullPointerException | IllegalArgumentException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    @PostMapping("/newMedication")
    public String registerNewMedication(@RequestParam("name")  String medicationName, @RequestParam("supplier") String supplier, @RequestParam("description") String medicationDescription, @RequestParam("price") Float medicationPrice, @RequestParam("quantity") Integer stock){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        if (DQS.getCurrentLoggedUser().getRole() == Permission.MEDIC)
            return "redirect:/";

        try {
            DEAMS.createNewMedication(DQS.getCurrentLoggedUser().getClinic().getClinicId(), medicationName, supplier, medicationDescription, medicationPrice, stock);
            return "redirect:/Inventory";
        } catch (PersistenceException | NullPointerException | IllegalArgumentException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/Inventory"; // TODO: implement error exception
    }

    @PostMapping("/deleteEquipment")
    public String deleteEquipment(@RequestParam("id") String equipmentId){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        if (DQS.getCurrentLoggedUser().getRole() == Permission.MEDIC)
            return "redirect:/";

        try{
            DEAMS.deleteRegisteredEquipment(equipmentId);
            return "redirect:/Inventory";
        } catch (PersistenceException | NullPointerException | IllegalArgumentException exp){
            //
        } catch (Exception exp){
            //
        }
        return "redirect:/Inventory"; // TODO: implement error exception
    }
}
