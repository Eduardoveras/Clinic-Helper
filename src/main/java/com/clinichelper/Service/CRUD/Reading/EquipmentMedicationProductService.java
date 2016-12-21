/**
 * Created by Djidjelly Siclait on 12/14/2016.
 */
package com.clinichelper.Service.CRUD.Reading;

import com.clinichelper.Entity.Equipment;
import com.clinichelper.Entity.Medication;
import com.clinichelper.Entity.Product;
import com.clinichelper.Repository.EquipmentRepository;
import com.clinichelper.Repository.MedicationRepository;
import com.clinichelper.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentMedicationProductService {

    // Repositories
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private ProductRepository productRepository;

    // Equipments
    public Equipment findRegisteredEquipment(String equipmentId) { return equipmentRepository.findByEquipmentId(equipmentId); }

    public List<Equipment> findAllRegisteredEquipmentsForClinic(String clinicId){ return equipmentRepository.findByClinic(clinicId); }

    public List<Equipment> findRegisteredEquipmentByName(String clinicId, String searchName){ return equipmentRepository.findByEquipmentName(searchName, clinicId); }

    // Medication
    public Medication findRegisteredMedication(String medicationId) { return medicationRepository.findByMedicationId(medicationId); }

    public List<Medication> findRegisteredMedicationByClinic(String clinicId) { return medicationRepository.findByClinic(clinicId); }

    public List<Medication> findRegisteredMedicationByName(String clinicId, String medicationName) { return medicationRepository.findByMedicationName(medicationName, clinicId); }

    public List<Medication> findRegisteredMedicationBySupplier(String clinicId, String supplier) { return medicationRepository.findBySupplier(supplier, clinicId); }

    // Products
    public Product findRegisteredProduct(String productId) { return productRepository.findByProductId(productId); }

    public List<Product> findRegisteredProductForClinic(String clinicId) { return productRepository.findByClinic(clinicId); }

    public List<Product> findRegisteredProductByName(String clinicId, String productName) { return productRepository.findByProductName(productName, clinicId); }
}
