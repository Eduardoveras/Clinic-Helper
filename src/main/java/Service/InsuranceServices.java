/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package Service;

import Entity.Insurance;
import Repository.InsuranceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InsuranceServices {

    // Implementing Repository
    private InsuranceRepository insuranceRepository;

    // Core Functions
    @Transactional
    public void registerNewInsurance(Insurance insurance){
        insuranceRepository.save(insurance);
    }

    @Transactional
    public void deleteInsurance(Insurance insurance){
        insuranceRepository.delete(insurance);
    }

    public Insurance findRegisteredInsurance(String jascId){
        return insuranceRepository.findByJascId(jascId);
    }

    public Insurance findPatientInsuranceInformation(String ownerJascID){
        return insuranceRepository.findRegisteredPatientsInsuranceInformation(ownerJascID);
    }
}
