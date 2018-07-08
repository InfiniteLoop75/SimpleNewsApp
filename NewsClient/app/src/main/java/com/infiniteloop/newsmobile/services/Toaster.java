package com.infiniteloop.newsmobile.services;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hp on 7/5/2018.
 */

public class Toaster {
    public static void printLog(Context c, String message){
        Toast.makeText(c, message, Toast.LENGTH_SHORT);
    }
}
