package com.infiniteloop.newsmobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.infiniteloop.newsmobile.async.CategoriesTask;
import com.infiniteloop.newsmobile.data_models.NewsManager;
import com.infiniteloop.newsmobile.db.NewsHelper;
import com.infiniteloop.newsmobile.listview.ExpandableListAdapter;
import com.infiniteloop.newsmobile.services.NetworkManager;
import com.infiniteloop.newsmobile.services.Toaster;

import java.io.IOException;

public class NewsList extends AppCompatActivity {


    private static final String TAG = NewsList.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        NewsHelper helper = new NewsHelper(this);
            initializeView();
        helper.close();

    }
    private void initializeView(){
        try {
            RefreshData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NewsManager manager = NewsManager.getInstance(this);
            ExpandableListAdapter adapter = new ExpandableListAdapter(this, manager.getCategories(), manager.getListHashMap());
            ExpandableListView view = findViewById(R.id.news_list);
            view.setAdapter(adapter);

    }
    private void RefreshData() throws IOException {
        final String urlString = "http://10.0.2.2:3000/api/categories";
           final NetworkManager nManager = new NetworkManager();

           if(nManager.isOnline(this)) {

               final CategoriesTask task = new CategoriesTask(this, this);
               task.execute(urlString);
           }else {
               Toaster.printToast(this, "Please switch on INTERNET to update news");
           }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_update) {
            initializeView();
        }

        return super.onOptionsItemSelected(item);
    }
}

