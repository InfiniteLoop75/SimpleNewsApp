package com.infiniteloop.newsmobile.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.infiniteloop.newsmobile.data_models.NewsManager;
import com.infiniteloop.newsmobile.services.JSONManager;
import com.infiniteloop.newsmobile.services.NewsDBFetcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hp on 7/6/2018.
 */

public class NewsTask extends AsyncTask<String, Void, Void> {
    private Activity activity;
    private Context context;
    private ProgressDialog dialog;
    NewsManager nManager;

    public NewsTask(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;


    }
    @Override
    protected void onPreExecute() {
        nManager = NewsManager.getInstance(context);
    }

    @Override
    protected Void doInBackground(String... strings) {
        JSONManager manager = new JSONManager();
        JSONObject object = manager.getJSONData(strings[0]);
        try {
            JSONArray array = object.getJSONArray("news");
            if((array.length()-nManager.getNewsCount())>=1){
                NewsDBFetcher fetcher = new NewsDBFetcher(context);
                fetcher.fillNewsTable(object);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

    }
}
