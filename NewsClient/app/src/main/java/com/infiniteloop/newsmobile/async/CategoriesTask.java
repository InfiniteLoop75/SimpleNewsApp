package com.infiniteloop.newsmobile.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.infiniteloop.newsmobile.data_models.NewsManager;
import com.infiniteloop.newsmobile.services.CategoryDBFetcher;
import com.infiniteloop.newsmobile.services.JSONManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hp on 7/6/2018.
 */

public class CategoriesTask extends AsyncTask<String, Void, Void> {
    private Activity activity;
    private Context context;
    private ProgressDialog dialog;
    NewsManager nManager;
    String TAG = CategoriesTask.class.getSimpleName();

    public CategoriesTask(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;


    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(activity);
        dialog.setMessage("Loading data...");
        dialog.show();
        nManager = NewsManager.getInstance(context);

    }

    @Override
    protected Void doInBackground(String... strings) {

        JSONManager manager = new JSONManager();
        JSONObject object = manager.getJSONData(strings[0]);
        try {
            JSONArray array = object.getJSONArray("categories");
            Log.i(TAG, "doInBackground: \n" +
                    "Array Count: " + array.length());
            Log.i(TAG, "doInBackground: \n" +
                    "dbCount: " + nManager.getCategoryCount());
            if((array.length()- nManager.getNewsCount())>=1){
                CategoryDBFetcher fetcher = new CategoryDBFetcher(context);
                fetcher.fillCategoryTable(object, "categories");
                new NewsTask(context, activity).execute("http://10.0.2.2:3000/api");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }




        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.i(TAG, "doInBackground: " + nManager.getCategories().size());
        dialog.dismiss();



    }
}
