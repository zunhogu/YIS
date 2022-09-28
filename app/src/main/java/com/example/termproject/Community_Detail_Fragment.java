package com.example.termproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.StringTokenizer;

public class Community_Detail_Fragment extends Fragment {
    private ViewGroup rootView;
    private Community_Activity activity;
    private Fragment community_detail_fragment;
    private Community_Edit_Fragment community_Edit_Fragment;
    private Button communtiy_detail_edit_btn,communtiy_detail_delete_btn;
    private Button community_detail_back_btn,community_detail_good_btn,community_detail_bad_btn;
    private TextView community_detail_title,community_detail_nickname,community_detail_content;
    private TextView community_detail_like_num,community_detail_unlike_num;
    private Community_Item item;
    private CommunityDataAdapter adapter;
    private boolean isInteraction=false;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity=(Community_Activity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.community_detail_fragment,container,false);

        community_detail_title = (TextView) rootView.findViewById(R.id.community_detail_title);
        community_detail_content = (TextView) rootView.findViewById(R.id.community_detail_content);
        community_detail_nickname= (TextView) rootView.findViewById(R.id.community_detail_nickname);
        community_detail_back_btn = (Button)rootView.findViewById(R.id.community_detail_back_btn);
        community_detail_good_btn = (Button)rootView.findViewById(R.id.community_detail_good_btn);
        community_detail_bad_btn = (Button)rootView.findViewById(R.id.community_detail_bad_btn);
        communtiy_detail_edit_btn =(Button)rootView.findViewById(R.id.community_detail_edit_btn);
        communtiy_detail_delete_btn = (Button)rootView.findViewById(R.id.community_detail_delete_btn);
        community_detail_like_num = (TextView)rootView.findViewById(R.id.community_detail_like_num);
        community_detail_unlike_num = (TextView)rootView.findViewById(R.id.community_detail_unlike_num);

        Bundle bundle = getArguments();
        if(bundle != null){
            item = (Community_Item) bundle.getSerializable("item");
            community_detail_title.setText(item.getTitle());
            community_detail_nickname.setText(item.getNICKNAME());
            community_detail_content.setText(item.getContents());
            community_detail_like_num.setText(item.getLike());
            community_detail_unlike_num.setText(item.getUnlike());
            adapter=(CommunityDataAdapter) bundle.getSerializable("adapter");
        }

        //좋아요 기록이 있다면
        String interaction = item.getInteraction();
        StringTokenizer token = new StringTokenizer(interaction, ".");
        while (token.hasMoreElements()) {
            if(token.nextToken().equals(Home_Activity.current_login.getNICKNAME())) {
                isInteraction = true;
                break;
            }
        }
        if(isInteraction){
            community_detail_good_btn.setEnabled(false);
            community_detail_bad_btn.setEnabled(false);
        }

        //이전 클릭시
        community_detail_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                community_detail_fragment=new Community_Fragment();
                Community_Activity.community_Fragment_Manager.beginTransaction().replace(R.id.container,community_detail_fragment).commit();
            }
        });

        community_detail_good_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setLike(Integer.toString(Integer.parseInt(item.getLike())+1));
                community_detail_like_num.setText(Integer.toString(Integer.parseInt(community_detail_like_num.getText().toString())+1));
                item.setInteraction(item.getInteraction()+Home_Activity.current_login.getNICKNAME()+".");
                adapter.update(item);
                Toast.makeText(rootView.getContext().getApplicationContext(),"좋아요",Toast.LENGTH_LONG).show();
                community_detail_good_btn.setEnabled(false);
                community_detail_bad_btn.setEnabled(false);
            }
        });
        community_detail_bad_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setUnlike(Integer.toString(Integer.parseInt(item.getUnlike())+1));
                community_detail_unlike_num.setText(Integer.toString(Integer.parseInt(community_detail_unlike_num.getText().toString())+1));
                item.setInteraction(item.getInteraction()+Home_Activity.current_login.getNICKNAME()+".");
                adapter.update(item);
                Toast.makeText(rootView.getContext().getApplicationContext(),"싫어요.",Toast.LENGTH_LONG).show();
                community_detail_good_btn.setEnabled(false);
                community_detail_bad_btn.setEnabled(false);
            }
        });

        if(!Home_Activity.current_login.getNICKNAME().equals(item.getNICKNAME())&&Home_Activity.current_login.getPOWER()==1){
            communtiy_detail_edit_btn.setEnabled(false);
            communtiy_detail_delete_btn.setEnabled(false);
        }

        communtiy_detail_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                community_Edit_Fragment=new Community_Edit_Fragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", item);
                bundle.putSerializable("adapter",adapter);
                community_Edit_Fragment.setArguments(bundle);
                Community_Activity.community_Fragment_Manager.beginTransaction().replace(R.id.container,community_Edit_Fragment).commit();
            }
        });
        final String[] select = {"삭제하기","취소하기"};
        communtiy_detail_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                AlertDialog.Builder dlg = new AlertDialog.Builder(activity);
                dlg.setTitle("정말 삭제하시겠습니까?");
                dlg.setItems(select, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(select[which]=="삭제하기"){
                            adapter.delete(item);
                            Toast.makeText(rootView.getContext().getApplicationContext(),"삭제하였습니다.",Toast.LENGTH_LONG).show();
                            community_detail_fragment=new Community_Fragment();
                            Community_Activity.community_Fragment_Manager.beginTransaction().replace(R.id.container,community_detail_fragment).commit();
                        }
                        else if(select[which]=="취소하기"){
                        }
                    }
                });
                dlg.show();
            }
        });
        return rootView;
    }
}