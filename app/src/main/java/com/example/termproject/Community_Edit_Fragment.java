package com.example.termproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.IOException;

public class Community_Edit_Fragment extends Fragment {
    private ViewGroup rootView;
    private Community_Activity activity;

    private Button community_edit_complete_btn,community_edit_cancel_btn;
    private EditText community_edit_title,community_edit_content;
    private TextView community_add_title;
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
            item = (Community_Item) bundle.getSerializable("item");
            adapter=(CommunityDataAdapter) bundle.getSerializable("adapter");
        }

        community_add_title =(TextView)rootView.findViewById(R.id.community_add_title0);
        community_edit_title = (EditText)rootView.findViewById(R.id.community_add_title);
        community_edit_content = (EditText)rootView.findViewById(R.id.community_add_content);
        community_edit_complete_btn = (Button)rootView.findViewById(R.id.community_add_complete_btn);
        community_edit_cancel_btn = (Button)rootView.findViewById(R.id.community_add_cancel_btn);

        community_add_title.setText("글수정");
        community_edit_title.setText(item.getTitle());
        community_edit_content.setText(item.getContents());
        community_edit_complete_btn.setText("수정하기");

        //완료버튼 클릭시
        community_edit_complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setTitle(community_edit_title.getText().toString());
                item.setContents(community_edit_content.getText().toString());
                adapter.update(item);
                Toast.makeText(rootView.getContext().getApplicationContext(),"글을 수정하였습니다.",Toast.LENGTH_LONG).show();
                Community_Fragment community_fragment = new Community_Fragment();
                Community_Activity.community_Fragment_Manager.beginTransaction().replace(R.id.container,community_fragment).commit();
            }
        });

        //취소버튼 클릭시
        community_edit_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Community_Fragment community_fragment=new Community_Fragment();
                Community_Activity.community_Fragment_Manager.beginTransaction().replace(R.id.container,community_fragment).commit();
            }
        });

        return rootView;
    }
}