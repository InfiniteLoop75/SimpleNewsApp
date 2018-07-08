package com.infiniteloop.newsmobile.db;


public class Constants {

    public final static String DB_NAME = "NEWS_DB";
    public final static int DB_VERSION = 21;
    public final static String TABLE_NEWS = "NEWS";
    public final static String NEWS_ID = "ID";
    public final static String NEWS_TITLE = "TITLE";
    public final static String NEWS_IMGURL = "IMGURL";
    public final static String NEWS_CONTENT = "PARAGRAPH";
    public final static String NEWS_DATE = "DateCreated";
    public final static String NEWS_CATEGORY_ID = "CategoryID";

    public final static String TABLE_CATEGORY = "CATEGORY";
    public final static String CATEGORY_ID = "ID";
    public final static String CATEGORY_NAME = "NAME";
    public final static String CATEGORY_DESCRIPTION = "DESCRIPTION";

    public final static String NEWS_CREATE = "CREATE TABLE IF NOT EXISTS "+ TABLE_NEWS+ "("
            + NEWS_ID + " INTEGER NOT NULL PRIMARY KEY," +
            NEWS_TITLE + " VARCHAR(255) NOT NULL,\n" +
            NEWS_CONTENT + " TEXT," +
            NEWS_IMGURL + " TEXT," +
            NEWS_DATE + " TEXT," +
            NEWS_CATEGORY_ID + " INTEGER," +
            "FOREIGN KEY(" + NEWS_CATEGORY_ID + ") REFERENCES "+ TABLE_CATEGORY + "("+ CATEGORY_ID + ")" +
            ");";
    public final static String CATEGORY_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_CATEGORY + " (\n" +
            CATEGORY_ID + " INTEGER NOT NULL PRIMARY KEY,\n" +
            CATEGORY_NAME + " VARCHAR(255) NOT NULL,\n" +
            CATEGORY_DESCRIPTION + " TEXT\n" +
            ");";
    public final static String DROP_NEWS = "DROP TABLE " + TABLE_NEWS;
    public final static String DROP_CATEGORY = "DROP TABLE " + TABLE_CATEGORY;
}
