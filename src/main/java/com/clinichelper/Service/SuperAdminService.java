/**
 * Created by Djidjelly Siclait on 10/30/2016.
 */
package com.clinichelper.Service;

import com.clinichelper.Entity.Clinic;
import com.clinichelper.Entity.User;
import com.clinichelper.Repository.ClinicRepository;
import com.clinichelper.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperAdminService {

    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private UserRepository userRepository;

    // Clinic Queries
    public Clinic findRegisteredClinicByClinicId(String clinicId){ return clinicRepository.findByClinicId(clinicId); }

    public List<Clinic> findAllRegisteredClinics() { return clinicRepository.findAll(); }

    // User Queries
    public List<User> findAllRegisteredUserAccounts(){ return userRepository.findAll(); }

    public User findUserInformation(String userId) { return userRepository.findByUserId(userId); }

    public User findRegisteredUserAccount(String email, String clinicId){ return userRepository.findUserAccountWithUsernameAndClinicID(email, clinicId); }

    public List<User> findAllAllRegisteredUsersForClinic(String clinicId) { return userRepository.findByClinicId(clinicId); }
}
