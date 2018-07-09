package com.infiniteloop.newsmobile.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.infiniteloop.newsmobile.data_models.NewsManager;
import com.infiniteloop.newsmobile.services.CategoryDBFetcher;
import com.infiniteloop.newsmobile.services.JSONManager;
import com.infiniteloop.newsmobile.services.Toaster;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

/**
 * Created by hp on 7/6/2018.
 */

public class CategoriesTask extends AsyncTask<String, Void, JSONObject> {
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
    protected JSONObject doInBackground(String... strings) {
        String urlString = "http://10.0.2.2:3000";


        JSONManager manager = new JSONManager();
        HttpURLConnection conn = manager.connection(strings[0]);
        JSONObject object = manager.getJSONData(conn);
        int status = 0;
        try {
            if(object!=null) {
                status = object.getInt("status");
            }else {
                status = 404;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "doInBackground: " + status);
        if(status==200) {
            CategoryDBFetcher fetcher = new CategoryDBFetcher(context);
            fetcher.fillCategoryTable(object, "categories");
            new NewsTask(context, activity).execute(urlString + "/api");
            return object;
        }else {
            this.cancel(true);
            return  null;
        }


    }

    @Override
    protected void onCancelled(){
        dialog.dismiss();
        Toaster.printToast(context, "Server Error");
    }

    @Override
    protected void onPostExecute(JSONObject object) {
        super.onPostExecute(object);
        dialog.dismiss();
        Toaster.printToast(context, "Data have been refreshed");
    }
}
