package com.infiniteloop.newsmobile.data_models;

/**
 * Created by hp on 7/5/2018.
 */

public class Category {
    private int ID;
    private String NAME;
    private String DESCRIPTION;

    public Category(int ID, String NAME, String DESCRIPTION) {
        this.ID = ID;
        this.NAME = NAME;
        this.DESCRIPTION = DESCRIPTION;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }
}
