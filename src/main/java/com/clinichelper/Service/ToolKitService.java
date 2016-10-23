/**
 * Created by Djidjelly Siclait on 10/11/2016.
 */
package com.clinichelper.Service;

import com.clinichelper.Entity.Chore;
import com.clinichelper.Repository.ChoreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToolKitService {

    // Attributes
    private List<Chore> todoList = new ArrayList<>();

    // Repositories
    private ChoreRepository choreRepository;

    public void InitializeTodoList(){

        // Adding Custom created tasks
        FetchCustomTasks();
    }

    // Auxiliary Functions
    private void FetchCustomTasks(){

        for (Chore c:
             choreRepository.findAll()) {
            todoList.add(c);
        }
    }

    private void FetchBirthDateReminders(){
        
    }
}
