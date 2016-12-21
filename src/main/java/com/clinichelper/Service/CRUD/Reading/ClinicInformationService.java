/**
 * Created by Djidjelly Siclait on 12/14/2016.
 */
package com.clinichelper.Service.CRUD.Reading;

import com.clinichelper.Entity.Chore;
import com.clinichelper.Entity.User;
import com.clinichelper.Repository.ChoreRepository;
import com.clinichelper.Repository.UserRepository;
import com.clinichelper.Service.Security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicInformationService {

    // Repositories
    @Autowired
    private ChoreRepository choreRepository;
    @Autowired
    private UserRepository userRepository;

    // Services
    @Autowired
    private EncryptionService encryptionService;

    // Chores
    public Chore findRegisteredCustomTask(String choreId){ return choreRepository.findByChoreId(choreId); }

    public List<Chore> findAllRegisteredCustomTasksForClinic(String clinicId){ return choreRepository.findByClinicId(clinicId); }

    // Users
    public User findUserInformation(String userId) { return userRepository.findByUserId(userId); }

    public User findRegisteredUserAccount(String email, String password){ return userRepository.findUserAccountWithUsernameAndPassword(email, encryptionService.encryptPassword(password)); }

    public List<User> findAllAllRegisteredUsersForClinic(String clinicId) { return userRepository.findByClinicId(clinicId); }

    public boolean validateUserAccountCredentials(String username, String password) {

        User user = userRepository.findUserAccountWithUsernameAndClinicIdAndPassword(username.toLowerCase(), encryptionService.encryptPassword(password));

        return (user != null);
    }
}
