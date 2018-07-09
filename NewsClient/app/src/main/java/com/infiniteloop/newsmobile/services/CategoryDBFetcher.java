package com.infiniteloop.newsmobile.services;

import android.content.Context;

import com.infiniteloop.newsmobile.db.NewsDBAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hp on 7/6/2018.
 */

public class CategoryDBFetcher {
    NewsDBAdapter adapter;
    Context context;
    public CategoryDBFetcher(Context c) {
        context = c;

        this.adapter =  new NewsDBAdapter(context);
    }

    public void fillCategoryTable(JSONObject jsonObject, String arrayName){
        adapter.openWritable();
        if(adapter.getCategoryCount()>0){
            adapter.clearCategories();
        }
        try {

            JSONArray array = jsonObject.getJSONArray(arrayName);
            for (int i = 0; i<array.length(); i++){
                JSONObject object = array.getJSONObject(i);
                int ID = object.getInt("ID");
                String name = object.getString("NAME");
                String description = object.getString("DESCRIPTION");
                adapter.addCategories(ID, name, description);
            }

        }catch (JSONException ex){
            ex.printStackTrace();
        }finally {
            adapter.closeDB();
        }
    }
}
