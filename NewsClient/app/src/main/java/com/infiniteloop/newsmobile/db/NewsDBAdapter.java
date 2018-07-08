package com.infiniteloop.newsmobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.infiniteloop.newsmobile.data_models.Category;
import com.infiniteloop.newsmobile.data_models.News;

import static com.infiniteloop.newsmobile.db.Constants.CATEGORY_DESCRIPTION;
import static com.infiniteloop.newsmobile.db.Constants.CATEGORY_ID;
import static com.infiniteloop.newsmobile.db.Constants.CATEGORY_NAME;
import static com.infiniteloop.newsmobile.db.Constants.NEWS_CATEGORY_ID;
import static com.infiniteloop.newsmobile.db.Constants.NEWS_CONTENT;
import static com.infiniteloop.newsmobile.db.Constants.NEWS_DATE;
import static com.infiniteloop.newsmobile.db.Constants.NEWS_ID;
import static com.infiniteloop.newsmobile.db.Constants.NEWS_IMGURL;
import static com.infiniteloop.newsmobile.db.Constants.NEWS_TITLE;
import static com.infiniteloop.newsmobile.db.Constants.TABLE_CATEGORY;
import static com.infiniteloop.newsmobile.db.Constants.TABLE_NEWS;

/**
 * Created by hp on 7/5/2018.
 */

public class NewsDBAdapter {
    private final static String TAG = NewsDBAdapter.class.getSimpleName();
    NewsHelper helper;
    SQLiteDatabase db;
    Context c;

    public NewsDBAdapter(Context c) {
        this.c = c;
        helper = new NewsHelper(c);
        Log.i(TAG, "NewsDBAdapter: " + isNull(helper));
    }
    private boolean isNull(Object o){
        if(o==null){
            return true;
        }
        return false;
    }
    public void openDB(){
        if(db==null){
            db = helper.getReadableDatabase();
        }
    }
    public void openWritable(){
        db = helper.getWritableDatabase();
    }
    public void closeDB(){
        if(db!=null){
            db.close();
        }
    }

    public void manageDB(String op){
//        db=helper.getReadableDatabase();
    }
    public boolean addNews(int ID,String TITLE, String IMGURL, String PARAGRAPH, String dateCreated,
                           int CategoryID){
        ContentValues values = new ContentValues();
        values.put(NEWS_ID, ID);
        values.put(NEWS_TITLE, TITLE);
        values.put(NEWS_IMGURL, IMGURL);
        values.put(NEWS_CONTENT, PARAGRAPH);
        values.put(NEWS_DATE, dateCreated);
        values.put(NEWS_CATEGORY_ID, CategoryID);
        long i = db.insertWithOnConflict(TABLE_NEWS, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        if(i>0){
            return true;
        }
        return false;
    }
    public boolean addCategories(int ID, String NAME, String DESCRIPTION){

        ContentValues values = new ContentValues();
        values.put(CATEGORY_ID, ID);
        values.put(CATEGORY_NAME, NAME);
        values.put(CATEGORY_DESCRIPTION, DESCRIPTION);
        long result = db.insertWithOnConflict(TABLE_CATEGORY, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        if(result>0){
            return true;
        }
        return false;
    }
    public Cursor getNews(){

            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NEWS, null);

            return cursor;

    }
    public int getCategoryCount(){
        return getCategory().getCount();
    }
    public int getNewsCount(){
        return getNews().getCount();
    }
    public Cursor getCategory(){

           Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CATEGORY, null);
           return cursor;


    }
    public Cursor getNewsByCategory(int Cat_ID){
        String query = "SELECT * FROM " + TABLE_NEWS + " WHERE " +
                NEWS_CATEGORY_ID + "=" + Cat_ID + ";";
        Cursor  c = db.rawQuery(query, null);

        return c;
    }
    public boolean isDBOpen(){
        return db.isOpen();
    }
    public Category getCategoryByID(int ID){

        String query21 = "SELECT * FROM " + TABLE_CATEGORY + " WHERE " + CATEGORY_ID + "=" + ID + ";";
        Cursor c = null;
        try {
        c  = db.rawQuery(query21, null);
        if(c.moveToFirst()){

            do{
                int Cat_ID = c.getInt(c.getColumnIndex(CATEGORY_ID));
                String Cat_NAME = c.getString(c.getColumnIndex(CATEGORY_NAME));
                String Cat_DESC = c.getString(c.getColumnIndex(CATEGORY_DESCRIPTION));
                Category category = new Category(Cat_ID, Cat_NAME, Cat_DESC);

                return category;
            }while (c.moveToNext());
        }

        }finally {
            if(c!=null){
                c.close();
            }

        }

        return null;
    }
    public News getNewsByID(int ID){

        Cursor c = null;
        String query = "SELECT * FROM " + TABLE_NEWS + " WHERE " + NEWS_ID + "=" + ID + ";";
        try {
        c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            do{
                int News_ID = c.getInt(c.getColumnIndex(NEWS_ID));
                String News_TITLE = c.getString(c.getColumnIndex(NEWS_TITLE));
                String News_Content = c.getString(c.getColumnIndex(NEWS_CONTENT));
                String News_Date = c.getString(c.getColumnIndex(NEWS_DATE));
                String News_IMGURL = c.getString(c.getColumnIndex(NEWS_IMGURL));
                int News_Category_ID = c.getInt(c.getColumnIndex(NEWS_CATEGORY_ID));
                News news = new News(News_ID, News_TITLE, News_Content, News_Date, News_IMGURL, News_Category_ID);
                return news;
            }while (c.moveToNext());
        }

        }finally {
            if(c!=null){
                c.close();
            }
        }

        return null;
    }


    public SQLiteDatabase getDB() {
        return helper.getWritableDatabase();
    }
}
