/**
 * Created by Djidjelly Siclait on 10/30/2016.
 */
package com.clinichelper.Entity;

import com.clinichelper.Tools.Enums.AccountType;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.UUID;

@Entity
public class Clinic implements Serializable{
    // Attributes
    @Id
    private String clinicId;
    private Date registeredDate;
    @NotNull
    private String clinicName;
    @NotNull
    private String clinicPrefix;
    @Column(length = 5000000)
    private Byte[] clinicLogo;
    private AccountType accountType;

    // Constructor
    public Clinic(){

    }

    public Clinic(String clinicName, String clinicPrefix, AccountType accountType){
        this.setClinicId("CH-" + accountType.name() + "-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setRegisteredDate(new Date(Calendar.getInstance().getTime().getTime()));
        this.setClinicName(clinicName);
        this.setClinicPrefix(clinicPrefix);
        this.setAccountType(accountType);
    }

    // Getters and Setters
    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getClinicPrefix() {
        return clinicPrefix;
    }

    public void setClinicPrefix(String clinicPrefix) {
        this.clinicPrefix = clinicPrefix;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Byte[] getClinicLogo() {
        return clinicLogo;
    }

    public void setClinicLogo(Byte[] clinicLogo) {
        this.clinicLogo = clinicLogo;
    }

    public String displayClinicLogo(){
        if(this.clinicLogo == null)
            return null;

        byte[] imgBytesAsBase64 = Base64.encodeBase64(toPrimitives(this.clinicLogo));
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
