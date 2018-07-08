package com.infiniteloop.newsmobile.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.infiniteloop.newsmobile.R;
import com.infiniteloop.newsmobile.data_models.Category;
import com.infiniteloop.newsmobile.data_models.News;
import com.infiniteloop.newsmobile.data_models.NewsManager;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

/**
 * Created by hp on 7/6/2018.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<Category> categories;
    private NewsManager manager;
    private HashMap<Category, List<News>> newsList;
    private static final String TAG = ExpandableListView.class.getSimpleName();
    public ExpandableListAdapter(Context context, List<Category> categories, HashMap<Category, List<News>> newsList) {

        this.context = context;
        this.categories = categories;
        this.newsList = newsList;
        manager = NewsManager.getInstance(context);

    }

    @Override
    public int getGroupCount() {
        return categories.size();
    }

    @Override
    public int getChildrenCount(int i) {

        return manager.getNewsByCategory(categories.get(i).getID()).size();
          //return newsList.get(categories.get(i)).size();
    }

    @Override
    public Category getGroup(int i) {
        return categories.get(i);
    }

    @Override
    public News getChild(int i, int i1) {
        return manager.getNewsByCategory(categories.get(i).getID()).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        String NAME = getGroup(i).getNAME();
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_category, null);
        }
        TextView textView = view.findViewById(R.id.category_name);
        textView.setText(NAME);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        NewsViewHolder holder;
        String TITLE = getChild(i,i1).getTITLE();
        String CONTENT = getChild(i, i1).getCONTENT();
        String DATE = getChild(i,i1).getDATE();
        String IMGURL = getChild(i, i1).getIMGURL();
        if(view==null){
            holder = new NewsViewHolder();
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_news, null);
            holder.setTITLE((TextView) view.findViewById(R.id.news_title));
            holder.setCONTENT((TextView)view.findViewById(R.id.news_content));
            holder.setDATE((TextView)view.findViewById(R.id.news_date));
            holder.setIMAGE((ImageView)view.findViewById(R.id.news_banner));
            view.setTag(holder);
        }else {
            holder = (NewsViewHolder)view.getTag();
        }
        holder.getTITLE().setText(TITLE);
        holder.getCONTENT().setText(CONTENT.substring(0,120)+"...");
        holder.getDATE().setText(DATE);
        ImageView banner = holder.getIMAGE();
        Picasso.get().load(IMGURL).into(banner);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
