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
    public JSONObject getJSONData(HttpURLConnection con){
        String result = "";
        try {

            int status = con.getResponseCode();
            con.setConnectTimeout(7000);
            con.setReadTimeout(15000);
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
    public HttpURLConnection connection(String sourceURL){
        URL url;
        HttpURLConnection con;
        try {
            url = new URL(sourceURL);
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(10000);
            return  con;
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
