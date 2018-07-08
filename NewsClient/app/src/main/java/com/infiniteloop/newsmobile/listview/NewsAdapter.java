package com.infiniteloop.newsmobile.listview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.infiniteloop.newsmobile.data_models.News;

import java.util.List;

/**
 * Created by hp on 7/6/2018.
 */

public class NewsAdapter extends ArrayAdapter {
    Context mContext;
    int layout;
    public NewsAdapter(@NonNull Context context, int resource, List<News> newsList) {
        super(context, resource, newsList);
        mContext = context;
        layout = resource;
    }

}
