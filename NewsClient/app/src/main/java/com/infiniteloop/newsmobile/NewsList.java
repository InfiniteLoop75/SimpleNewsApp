package com.infiniteloop.newsmobile;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.infiniteloop.newsmobile.async.CategoriesTask;
import com.infiniteloop.newsmobile.data_models.NewsManager;
import com.infiniteloop.newsmobile.db.NewsHelper;
import com.infiniteloop.newsmobile.listview.ExpandableListAdapter;
import com.infiniteloop.newsmobile.services.NetworkManager;
import com.infiniteloop.newsmobile.services.TaskCanceller;
import com.infiniteloop.newsmobile.services.Toaster;

public class NewsList extends AppCompatActivity {


    private static final String TAG = NewsList.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        NewsHelper helper = new NewsHelper(this);
        initializeListView();
        helper.close();

    }
    private void initializeListView(){
        RefreshCategories();
        NewsManager manager = NewsManager.getInstance(this);
        ExpandableListAdapter adapter = new ExpandableListAdapter(getApplication(), manager.getCategories(), manager.getListHashMap());
        ExpandableListView view = findViewById(R.id.news_list);
        view.setAdapter(adapter);
    }
    private void RefreshCategories(){
           final NetworkManager nManager = new NetworkManager();
           if(nManager.isOnline(this)) {
               CategoriesTask task = new CategoriesTask(this, this);
               Handler handler = new Handler();
               TaskCanceller canceller = new TaskCanceller(task);
               handler.postDelayed(canceller, 7000);
               task.execute("http://10.0.2.2:3000/api/categories");
           }else {
               Toaster.printToast(this, "Please switch on INTERNET to update news");
           }
    }
}

