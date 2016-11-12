package com.clinichelper.Service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Eduardo veras on 11-Nov-16.
 */
@Service
public class EncriptationService {


    private static MessageDigest md;

    public static String getEncriptedPassword(String pass){
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for (byte aDigested : digested) {
                sb.append(Integer.toHexString(0xff & aDigested));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncriptationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }


    public boolean isPasswordCorrect(String formPassword,String databasePassword)
    {
        return Objects.equals(databasePassword, getEncriptedPassword(formPassword));
    }




}
