package com.example.termproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Community_Fragment extends Fragment {
    private Community_Activity activity;
    private ListView communityListView;
    private Community_View_Adapter communityAdapter;
    private Button AddButton;
    private ArrayList<Community_Item> communityList;
    public static int communityCount=0;
    private CommunityDataAdapter adapter;
    private static boolean isCreateDataBase=false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity=(Community_Activity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //프래그먼트 구성
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.community_fragment,container,false);
        communityListView=(ListView)rootView.findViewById(R.id.community_listview);

        //DB 생성 후 데이터받아오기
        adapter = new CommunityDataAdapter(rootView.getContext().getApplicationContext());
        adapter.createDatabase();
        adapter.open();
        communityList = adapter.getTableData();

        AddButton=(Button)rootView.findViewById(R.id.community_add_btn);
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Community_Add_Fragment communityAddFragment =new Community_Add_Fragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("adapter",adapter);
                communityAddFragment.setArguments(bundle);
                Community_Activity.community_Fragment_Manager.beginTransaction().replace(R.id.container,communityAddFragment).commit();
            }
        });


        communityAdapter=new Community_View_Adapter();
        for(int i=0; i<communityList.size(); i++) {
            communityAdapter.addItem(communityList.get(i));
        }
        communityListView.setAdapter(communityAdapter);
        if(!isCreateDataBase) {
            communityCount = communityList.size();
            isCreateDataBase=true;
        }

        communityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Community_Item item = (Community_Item) communityAdapter.getItem(position);
                Community_Detail_Fragment community_detail_fragment=new Community_Detail_Fragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", item);
                bundle.putSerializable("adapter",adapter);
                community_detail_fragment.setArguments(bundle);
                Community_Activity.community_Fragment_Manager.beginTransaction().replace(R.id.container,community_detail_fragment).commit();
            }
        });

        return rootView;
    }
}