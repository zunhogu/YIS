package com.example.termproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.Serializable;

public class ChampInfo_Item implements Serializable {
    private String cName;
    private Bitmap cImage;
    private String cLine;
    private String cDspell;
    private String cFspell;
    private Bitmap cPassive;
    private Bitmap cQskill;
    private Bitmap cWskill;
    private Bitmap cEskill;
    private Bitmap cRskill;
    private String cSkillTree;
    private String cMainRune;
    private String cSubRune;
    private String cItem_1;
    private String cItem_2;
    private String cItem_3;
    private String cHardChampion;
    private String cEasyChampion;
    private String cWinRate;
    private String cPickRate;

    public  ChampInfo_Item(){};
    public ChampInfo_Item(String cName, Bitmap cImage, String cLine, String cDspell, String cFspell, Bitmap cPassive, Bitmap cQskill, Bitmap cWskill, Bitmap cEskill, Bitmap cRskill, String cSkillTree, String cMainRune, String cSubRune, String cItem_1, String cItem_2, String cItem_3, String cHardChampion, String cEasyChampion, String cWinRate, String cPickRate) {
        this.cName = cName;
        this.cImage = cImage;
        this.cLine = cLine;
        this.cDspell = cDspell;
        this.cFspell = cFspell;
        this.cPassive = cPassive;
        this.cQskill = cQskill;
        this.cWskill = cWskill;
        this.cEskill = cEskill;
        this.cRskill = cRskill;
        this.cSkillTree = cSkillTree;
        this.cMainRune = cMainRune;
        this.cSubRune = cSubRune;
        this.cItem_1 = cItem_1;
        this.cItem_2 = cItem_2;
        this.cItem_3 = cItem_3;
        this.cHardChampion = cHardChampion;
        this.cEasyChampion = cEasyChampion;
        this.cWinRate = cWinRate;
        this.cPickRate = cPickRate;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Bitmap getcImage() {
        return cImage;
    }

    public void setcImage(byte[] cImage) {
        Bitmap bitmap = BitmapFactory.decodeByteArray( cImage, 0, cImage.length ) ;
        this.cImage=bitmap;
    }

    public String getcLine() {
        return cLine;
    }

    public void setcLine(String cLine) {
        this.cLine = cLine;
    }

    public String getcDspell() {
        return cDspell;
    }

    public void setcDspell(String cDspell) {
        this.cDspell = cDspell;
    }

    public String getcFspell() {
        return cFspell;
    }

    public void setcFspell(String cFspell) {
        this.cFspell = cFspell;
    }

    public Bitmap getcPassive() {
        return cPassive;
    }

    public void setcPassive(byte[] cPassive) {
        Bitmap bitmap = BitmapFactory.decodeByteArray( cPassive, 0, cPassive.length ) ;
        this.cPassive=bitmap;
    }

    public Bitmap getcQskill() {
        return cQskill;
    }

    public void setcQskill(byte[] cQskill) {
        Bitmap bitmap = BitmapFactory.decodeByteArray( cQskill, 0, cQskill.length ) ;
        this.cQskill = bitmap;
    }

    public Bitmap getcWskill() {
        return cWskill;
    }

    public void setcWskill(byte[] cWskill) {
        Bitmap bitmap = BitmapFactory.decodeByteArray( cWskill, 0, cWskill.length ) ;
        this.cWskill = bitmap;
    }

    public Bitmap getcEskill() {
        return cEskill;
    }

    public void setcEskill(byte[] cEskill) {
        Bitmap bitmap = BitmapFactory.decodeByteArray( cEskill, 0, cEskill.length ) ;
        this.cEskill = bitmap;
    }

    public Bitmap getcRskill() {
        return cRskill;
    }

    public void setcRskill(byte[] cRskill) {
        Bitmap bitmap = BitmapFactory.decodeByteArray( cRskill, 0, cRskill.length ) ;
        this.cRskill = bitmap;
    }

    public String getcSkillTree() {
        return cSkillTree;
    }

    public void setcSkillTree(String cSkillTree) {
        this.cSkillTree = cSkillTree;
    }

    public String getcMainRune() {
        return cMainRune;
    }

    public void setcMainRune(String cMainRune) {
        this.cMainRune = cMainRune;
    }

    public String getcSubRune() {
        return cSubRune;
    }

    public void setcSubRune(String cSubRune) {
        this.cSubRune = cSubRune;
    }

    public String getcItem_1() {
        return cItem_1;
    }

    public void setcItem_1(String cItem_1) {
        this.cItem_1 = cItem_1;
    }

    public String getcItem_2() {
        return cItem_2;
    }

    public void setcItem_2(String cItem_2) {
        this.cItem_2 = cItem_2;
    }

    public String getcItem_3() {
        return cItem_3;
    }

    public void setcItem_3(String cItem_3) {
        this.cItem_3 = cItem_3;
    }

    public String getcHardChampion() {
        return cHardChampion;
    }

    public void setcHardChampion(String cHardChampion) {
        this.cHardChampion = cHardChampion;
    }

    public String getcEasyChampion() {
        return cEasyChampion;
    }

    public void setcEasyChampion(String cEasyChampion) {
        this.cEasyChampion = cEasyChampion;
    }

    public String getcWinRate() {
        return cWinRate;
    }

    public void setcWinRate(String cWinRate) {
        this.cWinRate = cWinRate;
    }

    public String getcPickRate() {
        return cPickRate;
    }

    public void setcPickRate(String cPickRate) {
        this.cPickRate = cPickRate;
    }
    //    @Override
//    public int compareTo(ChampInfo_Item s) {
//        if (Integer.parseInt(this.cWinRate) > Integer.parseInt(s.getcWinRate())) {
//            return -1;
//        } else if (Integer.parseInt(this.cWinRate) > Integer.parseInt(s.getcWinRate())) {
//            return 1;
//        }
//        return 0;
//    }
}