package com.infiniteloop.newsmobile.data_models;

/**
 * Created by hp on 7/5/2018.
 */

public class News {
    private int ID;
    private String TITLE;
    private String CONTENT;
    private String DATE;
    private String IMGURL;
    private int CATEGORY;

    public News(int ID, String TITLE, String CONTENT, String DATE, String IMGURL, int CATEGORY) {
        this.ID = ID;
        this.TITLE = TITLE;
        this.CONTENT = CONTENT;
        this.DATE = DATE;
        this.IMGURL = IMGURL;
        this.CATEGORY = CATEGORY;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getIMGURL() {
        return IMGURL;
    }

    public void setIMGURL(String IMGURL) {
        this.IMGURL = IMGURL;
    }

    public int getCATEGORY() {
        return CATEGORY;
    }

    public void setCATEGORY(int CATEGORY) {
        this.CATEGORY = CATEGORY;
    }
}
