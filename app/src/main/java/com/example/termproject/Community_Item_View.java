package com.example.termproject;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Community_Item_View extends LinearLayout {
    private TextView communityNumView;
    private TextView communityTitleView;
    private TextView communityLikeView;
    private TextView communityUnlikeView;

    public Community_Item_View(Context context) {
        super(context);
        init(context);
    }

    public Community_Item_View(Context context, @Nullable AttributeSet attrs) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.community_item, this, true);

        communityNumView = (TextView) findViewById(R.id.community_num);
        communityTitleView = (TextView) findViewById(R.id.community_title);
        communityLikeView = (TextView) findViewById(R.id.community_good);
        communityUnlikeView = (TextView) findViewById(R.id.community_bad);

    }

    public void setIndex(String index) {
        communityNumView.setText(index);
    }

    public void setTitle(String title) {
        communityTitleView.setText(title);
    }

    public void setLike(String like) {
        communityLikeView.setText(like);
    }

    public void setUnLike(String unlike) {
        communityUnlikeView.setText(unlike);
    }
}

