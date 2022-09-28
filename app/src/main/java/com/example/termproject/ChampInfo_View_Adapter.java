package com.example.termproject;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class ChampInfo_View_Adapter extends BaseAdapter {
    private ArrayList<ChampInfo_Item> items=new ArrayList<ChampInfo_Item>();

    public void addItem(ChampInfo_Item item){   //외부에서 item을 어댑터에 추가할수 있는 메소드
        items.add(item);
    }

    @Override
    public int getCount() {   //데이터의 개수 알려주는 함수
        return items.size();
    }

    @Override
    public Object getItem(int position) {   //데이터의 인덱스 알려주는 함수
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {   //데이터의 Id 알려주는 함수
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChampInfo_Item_View view =null;
        if(convertView==null)
            view =new ChampInfo_Item_View(parent.getContext().getApplicationContext());
        else
            view=(ChampInfo_Item_View)convertView;

        //Collections.sort(items);  //ArrayList 정렬
        ChampInfo_Item item=items.get(position);
        view.setChampNum(position+1);
        view.setChampName(item.getcName());
        view.setChampImg(item.getcImage());
        view.setChampWinRate(item.getcWinRate());
        view.setChampPickRate(item.getcPickRate());
        //view.setChampBanRate(item.getBanRate());

        return view;
    }
}

