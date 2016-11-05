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
    @RequestMapping("/inventory")
    public ModelAndView FetchInventoryView(Model model/*, @RequestParam("clinic") String clinicId*/){

        Map<String, List> inventory = TKS.FetchClinicInventory("CH-PLATINUM-JASC");
        model.addAttribute("todoList", TKS.InitializeTodoList("CH-PLATINUM-JASC"));
        model.addAttribute("equipmentList", inventory.get("equipments"));
        model.addAttribute("productList", inventory.get("products"));
        model.addAttribute("medication", inventory.get("medication"));

        return new ModelAndView("");
    }


    // Posts
    @PostMapping("/newEquipment")
    public String registerNewEquipment(/*@RequestParam("clinic") String clinicId,*/ @RequestParam("name") String equipmentName, @RequestParam("use") String equipmentUse, @RequestParam("description") String equipmentDescription, @RequestParam("quantity") Integer stock){

        try {
            DEAMS.createNewEquipment("CH-PLATINUM-JASC", equipmentName, equipmentUse, equipmentDescription, stock);
            return "redirect:/inventory";
        } catch (PersistenceException | NullPointerException | IllegalArgumentException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/inventory"; // TODO: implement error exception
    }

    @PostMapping("/newProduct")
    public String registerNewProduct(/*@RequestParam("clinic") String clinicId,*/ @RequestParam("name") String productName, @RequestParam("description") String productDescription, @RequestParam("price") Float productPrice, @RequestParam("quantity") Integer stock){

        try {
            DEAMS.createNewProduct("CH-PLATINUM-JASC", productName, productDescription, productPrice, stock);
        } catch (PersistenceException | NullPointerException | IllegalArgumentException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/inventory"; // TODO: implement error exception
    }

    @PostMapping("/newMedication")
    public String registerNewMedication(/*@RequestParam("clinic") String clinicId,*/@RequestParam("name")  String medicationName, @RequestParam("supplier") String supplier, @RequestParam("description") String medicationDescription, @RequestParam("price") Float medicationPrice, @RequestParam("quantity") Integer stock){

        try {
            DEAMS.createNewMedication("CH-PLATINUM-JASC", medicationName, supplier, medicationDescription, medicationPrice, stock);
        } catch (PersistenceException | NullPointerException | IllegalArgumentException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/inventory"; // TODO: implement error exception
    }

    // TODO: ADD EDIT AND DELETE POSTS
}
