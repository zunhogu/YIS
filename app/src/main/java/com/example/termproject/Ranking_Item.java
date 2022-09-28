package com.example.termproject;

import java.io.Serializable;

public class Ranking_Item implements Serializable {
    private int rNum,rLp;
    private String rName;
    /*
        public Ranking_Item(int rnum, int rlp, String rName) {
            this.rNum = rnum;
            this.rLp = rlp;
            this.rName = rName;
        }
    */
    public int getrNum() {
        return rNum;
    }

    public void setrNum(int rnum) {
        this.rNum = rnum;
    }

    public int getrLp() {
        return rLp;
    }

    public void setrLp(int rlp) {
        this.rLp = rlp;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    //String name;
    public int getImage(String s){
        switch (s) {
            case "IRON":
                return R.drawable.iron;
            case "BRONZE":
                return R.drawable.bronze;
            case "SILVER":
                return R.drawable.silver;
            case "GOLD":
                return R.drawable.gold;
            case "PLATINUM":
                return R.drawable.platinum;
            case "DIAMOND":
                return R.drawable.diamond;
            case "MASTER":
                return R.drawable.master;
            case "GRANDMASTER":
                return R.drawable.grandmaster;
            case "CHALLENGER":
                return R.drawable.challenger;
        }
        return R.drawable.except;
    }



}
