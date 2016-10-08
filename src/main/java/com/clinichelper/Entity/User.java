/**
 * Created by Djidjelly Siclait on 9/25/2016.
 */
package com.clinichelper.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable{
    // Attributes
    @Id
    private String username;
    @NotNull
    private Staff staff;
    @NotNull
    private String password;
    @NotNull
    private String role;

    // Constructors
    public User(){

    }

    public User(String username, Staff staff, String password, String role){
        this.setUsername(username);
        this.setStaff(staff);
        this.setPassword(password);
        this.setRole(role);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
