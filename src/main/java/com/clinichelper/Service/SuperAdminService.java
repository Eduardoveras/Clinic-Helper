/**
 * Created by Djidjelly Siclait on 10/30/2016.
 */
package com.clinichelper.Service;

import com.clinichelper.Entity.User;
import com.clinichelper.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperAdminService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllRegisteredUserAccounts(){ return userRepository.findAll(); }
}
