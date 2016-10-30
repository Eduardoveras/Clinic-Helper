/**
 * Created by Djidjelly Siclait on 10/30/2016.
 */
package com.clinichelper.Tools;

public enum Gender {
    F(true),
    M(false);

    private boolean value;

    Gender(boolean value){
        this.value = value;
    }

    public String getGender(){
        return value ? "F" : "M";
    }

}
