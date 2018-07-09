package com.infiniteloop.newsmobile.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.infiniteloop.newsmobile.services.Toaster;

import static com.infiniteloop.newsmobile.db.Constants.CATEGORY_CREATE;
import static com.infiniteloop.newsmobile.db.Constants.DB_NAME;
import static com.infiniteloop.newsmobile.db.Constants.DB_VERSION;
import static com.infiniteloop.newsmobile.db.Constants.NEWS_CREATE;


public class NewsHelper extends SQLiteOpenHelper {

    private Context c;
    public NewsHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        c = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            sqLiteDatabase.execSQL(CATEGORY_CREATE);
            sqLiteDatabase.execSQL(NEWS_CREATE);
        }catch (SQLException ex){
            Toaster.printToast(c, "Unable to create");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            sqLiteDatabase.execSQL(Constants.DROP_NEWS);
            sqLiteDatabase.execSQL(Constants.DROP_CATEGORY);
            onCreate(sqLiteDatabase);
        }catch (SQLException ex){
            Toaster.printToast(c, "Something went wrong " + ex);
        }
    }

}
