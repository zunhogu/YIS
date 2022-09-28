package com.example.termproject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Ranking_Item_View extends LinearLayout {
    private TextView rankNumView;
    private TextView rankNameView;
    private ImageView rankTierImg;
    private TextView rankLpView;

    public Ranking_Item_View(Context context) {
        super(context);
        init(context);
    }
    public Ranking_Item_View(Context context,@Nullable AttributeSet attrs){
        super(context);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.ranking_item,this,true);

        rankNumView=(TextView)findViewById(R.id.rank_num);
        rankNameView=(TextView)findViewById(R.id.rank_name);
        //rankTierImg=(ImageView)findViewById(R.id.rank_tier);
        rankLpView=(TextView)findViewById(R.id.rank_lp);
    }
    public void setRankNum(int num){
        rankNumView.setText(Integer.toString(num));
    }
    public void setRankName(String name){
        rankNameView.setText(name);
    }
    public void setRankLP(int lp){
        rankLpView.setText(Integer.toString(lp)+" LP");
    }
    /*public void setRankTier(int resId){
        rankTierImg.setImageResource((resId));
    }*/

}

