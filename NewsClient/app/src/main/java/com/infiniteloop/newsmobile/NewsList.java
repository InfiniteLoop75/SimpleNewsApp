package com.infiniteloop.newsmobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListView;

import com.infiniteloop.newsmobile.async.CategoriesTask;
import com.infiniteloop.newsmobile.data_models.NewsManager;
import com.infiniteloop.newsmobile.db.NewsHelper;
import com.infiniteloop.newsmobile.listview.ExpandableListAdapter;

public class NewsList extends AppCompatActivity {


    private static final String TAG = NewsList.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        NewsHelper helper = new NewsHelper(this);
        RefreshCategories();
        NewsManager manager = NewsManager.getInstance(this);
        ExpandableListAdapter adapter = new ExpandableListAdapter(getApplication(), manager.getCategories(), manager.getListHashMap());
        ExpandableListView view = findViewById(R.id.news_list);
        view.setAdapter(adapter);
        helper.close();

        Log.i(TAG, "onCreate: " + adapter.getChildrenCount(2));
    }
    private void RefreshCategories(){
           new CategoriesTask(this, this).execute("http://10.0.2.2:3000/api/categories");
    }
}

