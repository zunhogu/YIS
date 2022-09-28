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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.IOException;

public class Ranking_Edit_Fragment extends Fragment {
    private ViewGroup rootView, exView;
    private Ranking_Activity activity;
    private Ranking_Fragment rankingFragment;
    private TextView title;
    private EditText editName;
    private EditText editLP;
    private Button AddCompleteButton;
    private Button AddCancelButton;
    private String name;
    private int img_id;
    private int lp;
    private Ranking_Item item;
    private RankingDataAdapter adapter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity=(Ranking_Activity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //프래그먼트 구성
        rootView = (ViewGroup)inflater.inflate(R.layout.ranking_edit_fragment,container,false);
        exView = (ViewGroup)inflater.inflate(R.layout.ranking_fragment,container,false);

        title=(TextView)rootView.findViewById(R.id.rank_edit_title);
        title.setText("유저 수정");

        Bundle bundle = getArguments();

        if(bundle != null){
            item = (Ranking_Item) bundle.getSerializable("item");
            adapter=(RankingDataAdapter) bundle.getSerializable("adapter");
        }



        editName=(EditText)rootView.findViewById(R.id.ranking_edit_name);
        editLP=(EditText)rootView.findViewById(R.id.ranking_edit_lp);



        //완료버튼 클릭시
        AddCompleteButton=(Button)rootView.findViewById(R.id.ranking_edit_complete_btn);
        AddCompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //수정한 데이터를 데이터베이스에 저장
                item.setrName((editName.getText().toString()));
                item.setrLp(Integer.parseInt(editLP.getText().toString()));
                adapter.update(item);
                //Toast.makeText(rootView.getContext().getApplicationContext(),"글을 수정하였습니다.",Toast.LENGTH_LONG).show();
                rankingFragment=new Ranking_Fragment();
                Ranking_Activity.ranking_Fragment_Manager.beginTransaction().replace(R.id.container,rankingFragment).commit();
            }
        });



        //취소버튼 클릭시
        AddCancelButton=(Button)rootView.findViewById(R.id.ranking_edit_cancel_btn);
        AddCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rankingFragment=new Ranking_Fragment();
                Ranking_Activity.ranking_Fragment_Manager.beginTransaction().replace(R.id.container,rankingFragment).commit();
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 &&  data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(rootView.getContext().getContentResolver(), uri);
                //ImageView imageView = (ImageView) rootView.findViewById(R.id.ranking_add_img);
                //imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
