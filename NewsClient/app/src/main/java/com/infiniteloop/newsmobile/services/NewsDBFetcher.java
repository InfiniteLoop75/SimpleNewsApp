package com.infiniteloop.newsmobile.services;

import android.content.Context;

import com.infiniteloop.newsmobile.db.NewsDBAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hp on 7/5/2018.
 */

public class NewsDBFetcher {
    String TAG = NewsDBFetcher.class.getSimpleName();
    NewsDBAdapter adapter;
    Context context;
    public NewsDBFetcher(Context c) {
        context = c;
        this.adapter =  new NewsDBAdapter(context);
    }

    public void fillNewsTable(JSONObject jsonObject){

        adapter.openWritable();
        if(adapter.getNewsCount()>0){
            adapter.clearNews();
        }

        try {

            JSONArray array = jsonObject.getJSONArray("news");

            for (int i = 0; i<array.length(); i++){
                JSONObject object = array.getJSONObject(i);
                int ID = object.getInt("ID");
                String title = object.getString("TITLE");
                String paragraph = object.getString("PARAGRAPH");
                String imgUrl = object.getString("IMGURL");
                String date = object.getString("DateCreated");
                int categoryID = object.getInt("categoryID");
                adapter.addNews(ID, title,imgUrl,paragraph,date,categoryID);
            }

        }catch (JSONException ex){
            ex.printStackTrace();
        }finally {
            adapter.closeDB();
        }

    }
}
