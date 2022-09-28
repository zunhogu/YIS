package com.example.termproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.IOException;

public class Community_Add_Fragment extends Fragment {
    private ViewGroup rootView;
    private Community_Activity activity;
    private Fragment community_add_fragment;

    private Button community_add_complete_btn,community_add_image_btn,community_add_cancel_btn;
    private EditText community_add_title,community_add_content;

    private Community_Item item;
    private CommunityDataAdapter adapter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity=(Community_Activity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.community_add_fragment,container,false);

        Bundle bundle = getArguments();
        if(bundle != null){
            adapter=(CommunityDataAdapter) bundle.getSerializable("adapter");
        }

        community_add_title = (EditText)rootView.findViewById(R.id.community_add_title);
        community_add_content = (EditText)rootView.findViewById(R.id.community_add_content);
        community_add_complete_btn = (Button)rootView.findViewById(R.id.community_add_complete_btn);
        community_add_cancel_btn = (Button)rootView.findViewById(R.id.community_add_cancel_btn);

        //완료버튼 클릭시
        community_add_complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(community_add_title.getText().toString().isEmpty()){
                    Toast.makeText(rootView.getContext().getApplicationContext(),"제목을 입력하세요",Toast.LENGTH_LONG).show();
                }
                else if(community_add_content.getText().toString().isEmpty()){
                    Toast.makeText(rootView.getContext().getApplicationContext(),"내용을 입력하세요",Toast.LENGTH_LONG).show();
                }
                else {
                    Community_Fragment.communityCount++;
                    item = new Community_Item(Integer.toString(Community_Fragment.communityCount),Home_Activity.current_login.getNICKNAME(),community_add_title.getText().toString(),community_add_content.getText().toString(),Integer.toString(0),Integer.toString(0),".");
                    adapter.insert(item);
                    Toast.makeText(rootView.getContext().getApplicationContext(),"글을 등록하였습니다.",Toast.LENGTH_LONG).show();
                    community_add_fragment = new Community_Fragment();
                    Community_Activity.community_Fragment_Manager.beginTransaction().replace(R.id.container, community_add_fragment).commit();
                }
            }
        });

        //뒤로가기 버튼 클릭시
        community_add_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                community_add_fragment=new Community_Fragment();
                Community_Activity.community_Fragment_Manager.beginTransaction().replace(R.id.container,community_add_fragment).commit();
            }
        });
        return rootView;
    }
}