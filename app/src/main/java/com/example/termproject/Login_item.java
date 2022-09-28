package com.example.termproject;

public class Login_item {
    private String ID;
    private String PWD;
    private String NICKNAME;
    private int POWER; // 1 : 유저  ,  2 : 관리자


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPWD() {
        return PWD;
    }

    public void setPWD(String PWD) {
        this.PWD = PWD;
    }

    public String getNICKNAME() {
        return NICKNAME;
    }

    public void setNICKNAME(String NICKNAME) {
        this.NICKNAME = NICKNAME;
    }

    public int getPOWER() {
        return POWER;
    }

    public void setPOWER(int POWER) {
        this.POWER = POWER;
    }
}
