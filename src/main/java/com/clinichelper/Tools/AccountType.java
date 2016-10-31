/**
 * Created by Djidjelly Siclait on 10/30/2016.
 */
package com.clinichelper.Tools;

public enum AccountType {
    BASIC(5),
    SILVER(10),
    GOLD(20),
    PLATINUM(100),
    SUPERADMIN(-1);

    private int value;

    AccountType(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        switch (this.value){
            case 5:
                return "BASIC";
            case 10:
                return "SILVER";
            case 20:
                return "GOLD";
            case 100:
                return "PLATINUM";
            default:
                return "SUPERADMIN";
        }
    }
}
