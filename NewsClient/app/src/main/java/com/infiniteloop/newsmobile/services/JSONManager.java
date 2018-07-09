package com.infiniteloop.newsmobile.services;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hp on 7/5/2018.
 */

public class JSONManager {
    private static final String TAG = JSONManager.class.getSimpleName();
    public JSONObject getJSONData(String sourceURL){
        String result = "";
        try {
            URL url = new URL(sourceURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            int status = con.getResponseCode();
            if(status == 200) {
                InputStream stream = con.getInputStream();
                InputStreamReader reader = new InputStreamReader(stream);
                int data = reader.read();
                while (data != -1) {
                    char charset = (char) data;
                    result += charset;
                    data = reader.read();
                }
                JSONObject jsonObject = new JSONObject(result);
                return jsonObject;
            }else {
                return null;
            }
        }catch (MalformedURLException ex){
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
