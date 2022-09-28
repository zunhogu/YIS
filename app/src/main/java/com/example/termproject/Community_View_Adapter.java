package com.example.termproject;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Community_View_Adapter extends BaseAdapter {
    private ArrayList<Community_Item> items=new ArrayList<Community_Item>();

    public void addItem(Community_Item item){   //외부에서 item을 어댑터에 추가할수 있는 메소드
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
    public View getView(int position, View convertView, ViewGroup parent) { Community_Item_View view =null;
        if(convertView==null)
            view =new Community_Item_View(parent.getContext().getApplicationContext());
        else
            view=(Community_Item_View)convertView;

        Community_Item item=items.get(position);
        view.setIndex(item.getIndex());
        view.setTitle(item.getTitle());
        view.setLike(item.getLike());
        view.setUnLike(item.getUnlike());

        return view;
    }
}
