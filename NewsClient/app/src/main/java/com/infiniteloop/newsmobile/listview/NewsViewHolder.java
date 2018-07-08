package com.infiniteloop.newsmobile.listview;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hp on 7/5/2018.
 */

public class NewsViewHolder {
    private TextView TITLE;
    private TextView CONTENT;
    private TextView DATE;
    private ImageView IMAGE;

//    public NewsViewHolder(View view){
//        TITLE = view.findViewById(R.id.news_title);
//        CONTENT = view.findViewById(R.id.news_content);
//        DATE = view.findViewById(R.id.news_date);
//        IMAGE = view.findViewById(R.id.news_banner);
//    }

    public void setTITLE(TextView TITLE) {
        this.TITLE = TITLE;
    }

    public void setCONTENT(TextView CONTENT) {
        this.CONTENT = CONTENT;
    }

    public void setDATE(TextView DATE) {
        this.DATE = DATE;
    }

    public void setIMAGE(ImageView IMAGE) {
        this.IMAGE = IMAGE;
    }

    public TextView getTITLE() {
        return TITLE;
    }

    public TextView getCONTENT() {
        return CONTENT;
    }

    public TextView getDATE() {
        return DATE;
    }

    public ImageView getIMAGE() {
        return IMAGE;
    }
}
