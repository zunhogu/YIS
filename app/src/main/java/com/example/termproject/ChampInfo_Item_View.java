package com.example.termproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ChampInfo_Item_View extends LinearLayout {
    private TextView ChampNumView;
    private TextView ChampNameView;
    private ImageView ChampImg;
    private TextView WinRateView;
    private TextView PickRateView;
    private TextView BanRateView;

    public ChampInfo_Item_View(Context context) {
        super(context);
        init(context);
    }
    public ChampInfo_Item_View(Context context,@Nullable AttributeSet attrs){
        super(context);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.champinfo_item,this,true);

        ChampNumView=(TextView)findViewById(R.id.champinfo_num);
        ChampNameView=(TextView)findViewById(R.id.champinfo_name);
        ChampImg=(ImageView)findViewById(R.id.champinfo_img);
        WinRateView=(TextView)findViewById(R.id.champinfo_winrate);
        PickRateView=(TextView)findViewById(R.id.champinfo_pickrate);
        //BanRateView=(TextView)findViewById(R.id.champinfo_banrate);
    }
    public void setChampNum(int num){ ChampNumView.setText(Integer.toString(num)); }
    public void setChampName(String name){ ChampNameView.setText(name); }
    public void setChampImg(Bitmap img){
        ChampImg.setImageBitmap(img);
    }
    public void setChampWinRate(String winRate){ WinRateView.setText(winRate+"%"); }
    public void setChampPickRate(String pickRate){ PickRateView.setText(pickRate+"%"); }
    //public void setChampBanRate(double banRate){ BanRateView.setText(Double.toString(banRate)+"%"); }
}
