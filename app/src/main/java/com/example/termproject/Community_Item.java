package com.example.termproject;

import java.io.Serializable;

public class Community_Item implements Serializable {

    private String index;
    private String NICKNAME;
    private String Title;
    private String Contents;
    private String Like;
    private String Unlike;
    private String Interaction;  //좋아요, 싫어요 기록

    public Community_Item(){};
    public Community_Item(String index, String NICKNAME, String title, String contents, String like, String unlike, String interaction) {
        this.index = index;
        this.NICKNAME = NICKNAME;
        this.Title = title;
        this.Contents = contents;
        this.Like = like;
        this.Unlike = unlike;
        this.Interaction =interaction;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getNICKNAME() {
        return NICKNAME;
    }

    public void setNICKNAME(String NICKNAME) {
        this.NICKNAME = NICKNAME;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        Contents = contents;
    }

    public String getLike() { return Like; }

    public void setLike(String like) {
        Like = like;
    }

    public String getUnlike() {
        return Unlike;
    }

    public void setUnlike(String unlike) {
        Unlike = unlike;
    }

    public String getInteraction() { return Interaction; }

    public void setInteraction(String interaction) { Interaction = interaction; }
}

