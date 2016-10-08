/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package com.clinichelper.Service;

import com.clinichelper.Entity.Staff;
import com.clinichelper.Entity.User;
import com.clinichelper.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserServices {

    // Implementing Repository
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init(){
        if(userRepository.count() == 0)
            // TODO: Create admin staff
            userRepository.save(new User("ADMIN", new Staff(), "admin", "admin"));
    }

    // Core Functions
    @Transactional
    public void createNewUser(User user){
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public User findRegisteredUser(String username){
        return userRepository.findByUsername(username);
    }

    @Transactional
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
/*
    public User findRegisteredUser(String username, String password){
        return userRepository.findUserAccountWithUsernameAndPassword(username, password);
    }
*/
}
