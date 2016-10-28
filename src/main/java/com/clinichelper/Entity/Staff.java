package com.clinichelper.Entity;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

/**
 * Created by eva_c on 9/25/2016.
 */
@Entity
@Table(name="staff")
public class Staff implements Serializable{
    @Id
    private String jascId;
    @NotNull
    private String staffFirstName;
    @NotNull
    private String staffLastName;
    @Column(length = 5000000)
    private Byte[] photo;
    @NotNull
    private Date staffBirthDate;
    @Column(unique = true)
    @NotNull
    private String staffEmail;
    @Column(unique = true)
    @NotNull
    private String staffClinicId;

    public Staff(){

    }

    public Staff(String staffFirstName,String staffLastName, Date staffBirthDate, String staffEmail, String staffClinicId) {
        this.setJascId("JASC-STAFF-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setStaffFirstName(staffFirstName.toLowerCase());
        this.setStaffLastName(staffLastName.toUpperCase());
        this.setStaffBirthDate(staffBirthDate);
        this.setStaffEmail(staffEmail.toLowerCase());
        this.setStaffClinicId(staffClinicId);
    }

    public String getJascId() {
        return jascId;
    }

    public void setJascId(String jascId) {
        this.jascId = jascId;
    }

    public String getStaffFirstName() {
        return staffFirstName;
    }

    public void setStaffFirstName(String staffFirstName) {
        this.staffFirstName = staffFirstName;
    }

    public String getStaffClinicId() {
        return staffClinicId;
    }

    public void setStaffClinicId(String staffClinicId) {
        this.staffClinicId = staffClinicId;
    }

    public String getStaffLastName() {
        return staffLastName;
    }

    public void setStaffLastName(String staffLastName) {
        this.staffLastName = staffLastName;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public Date getStaffBirthDate() {
        return staffBirthDate;
    }

    public void setStaffBirthDate(Date staffBirthDate) {
        this.staffBirthDate = staffBirthDate;
    }

    public Byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(Byte[] photo) {
        this.photo = photo;
    }

    public String displayPhoto(){
        if(this.photo == null)
            return null;

        byte[] imgBytesAsBase64 = Base64.encodeBase64(toPrimitives(this.photo));
        return new String(imgBytesAsBase64);
    }

    // Auxiliary Function
    private byte[] toPrimitives(Byte[] buffer) {

        byte[] bytes = new byte[buffer.length];
        for(int i = 0; i < buffer.length; i++){
            bytes[i] = buffer[i];
        }
        return bytes;
    }
}
