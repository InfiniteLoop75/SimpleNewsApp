package com.infiniteloop.newsmobile.data_models;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.infiniteloop.newsmobile.db.NewsDBAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.infiniteloop.newsmobile.db.Constants.CATEGORY_DESCRIPTION;
import static com.infiniteloop.newsmobile.db.Constants.CATEGORY_ID;
import static com.infiniteloop.newsmobile.db.Constants.CATEGORY_NAME;
import static com.infiniteloop.newsmobile.db.Constants.NEWS_CATEGORY_ID;
import static com.infiniteloop.newsmobile.db.Constants.NEWS_CONTENT;
import static com.infiniteloop.newsmobile.db.Constants.NEWS_DATE;
import static com.infiniteloop.newsmobile.db.Constants.NEWS_ID;
import static com.infiniteloop.newsmobile.db.Constants.NEWS_IMGURL;
import static com.infiniteloop.newsmobile.db.Constants.NEWS_TITLE;

public class NewsManager {
    private static final String TAG = NewsManager.class.getSimpleName();
    private static Context context;
    private static NewsManager instance;
    private List<Category> categories;
    private List<News> newsList;
    private HashMap<Category, List<News>> listHashMap;
    private NewsDBAdapter adapter;
    public static NewsManager getInstance(Context c) {
        if(instance==null){
            instance = new NewsManager(c);
        }
        return instance;
    }

    private NewsManager(Context c) {
        categories = new ArrayList<>();
        newsList = new ArrayList<>();
        listHashMap = new HashMap<>();
        this.context = c;
        initializeAdapter();
        fetchCategory();
        fetchNews();
        bindHashMap();
    }
    private void initializeAdapter(){
        adapter = new NewsDBAdapter(context);
    }
    private void fetchNews(){

        Cursor cursor = null;
        try {
            if(!adapter.isDBOpen()){
                adapter.openDB();
            }
            cursor = adapter.getNews();
            if (cursor.moveToFirst()) {
                do {
                    int ID = cursor.getInt(cursor.getColumnIndex(NEWS_ID));
                    String TITLE = cursor.getString(cursor.getColumnIndex(NEWS_TITLE));
                    String CONTENT = cursor.getString(cursor.getColumnIndex(NEWS_CONTENT));
                    String DATE = cursor.getString(cursor.getColumnIndex(NEWS_DATE));
                    String IMGURL = cursor.getString(cursor.getColumnIndex(NEWS_IMGURL));
                    int cat_id = cursor.getInt(cursor.getColumnIndex(NEWS_CATEGORY_ID));
                    News news = new News(ID, TITLE, CONTENT, DATE, IMGURL, cat_id);
                    newsList.add(news);
                } while (cursor.moveToNext());

            }
        }finally {
            if(cursor!=null){
                cursor.close();
            }

        }

    }
    private void fetchCategory(){
        adapter.openDB();
        Cursor cursor = adapter.getCategory();
        if(cursor.moveToFirst()){
            do {
                int ID = cursor.getInt(cursor.getColumnIndex(CATEGORY_ID));
                String NAME = cursor.getString(cursor.getColumnIndex(CATEGORY_NAME));
                String DESC = cursor.getString(cursor.getColumnIndex(CATEGORY_DESCRIPTION));
                Category category = new Category(ID, NAME, DESC);
                categories.add(category);
            }while (cursor.moveToNext());

        }
        cursor.close();
    }
    public Category getCategoryByID(int ID){
        adapter.openDB();
        Category category = adapter.getCategoryByID(ID);
        adapter.closeDB();
        return category;
    }
    public List<News> getNewsByCategory(int categoryID){

        List<News> newsList = new ArrayList<>();
        Cursor c = null;
        try {

            if(!adapter.isDBOpen()){
                adapter.openDB();
            }
            c = adapter.getNewsByCategory(categoryID);
            Log.i(TAG, "getNewsByCategory: " +  c);
            if (c.moveToFirst()) {
                do {
                    int News_ID = c.getInt(c.getColumnIndex(NEWS_ID));
                    String News_TITLE = c.getString(c.getColumnIndex(NEWS_TITLE));
                    String News_Content = c.getString(c.getColumnIndex(NEWS_CONTENT));
                    String News_Date = c.getString(c.getColumnIndex(NEWS_DATE));
                    String News_IMGURL = c.getString(c.getColumnIndex(NEWS_IMGURL));
                    int News_Category_ID = c.getInt(c.getColumnIndex(NEWS_CATEGORY_ID));
                    News news = new News(News_ID, News_TITLE, News_Content, News_Date, News_IMGURL, News_Category_ID);
                    newsList.add(news);
                } while (c.moveToNext());
            }
            c.close();
            Log.i(TAG, "getNewsByCategory: " + newsList.size());
            return newsList;
        }finally {
            if (c!=null){
                c.close();
            }
        }


    }
    public News getNewsByID(int ID){
        adapter.openDB();
        News news = adapter.getNewsByID(ID);
        return news;
    }
    private void bindHashMap(){
        for (Category category:
             categories) {
            listHashMap.put(category, getNewsByCategory(category.getID()));
        }
    }

    public List<Category> getCategories() {
        return categories;
    }

    public int getCategoryCount(){
        return adapter.getCategoryCount();
    }
    public int getNewsCount(){
        return adapter.getNewsCount();
    }
    public List<News> getNewsList() {
        return newsList;
    }

    public HashMap<Category, List<News>> getListHashMap() {
        return listHashMap;
    }
}
