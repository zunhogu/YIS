package com.example.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ChampInfo_Activity extends AppCompatActivity {
    private Intent intent;
    private String id;
    private int index;
    private Button home_btn;
    private Button search_btn;
    private Button champinfo_btn;
    private Button ranking_btn;
    private Button community_btn;
    public static FragmentManager champInfo_Fragment_Manager;
    private Fragment fragment;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_activity);

        intent=getIntent();
        id=intent.getStringExtra("id");
        index=intent.getIntExtra("type",0);

        champInfo_Fragment_Manager=getSupportFragmentManager();
        fragment=new ChampInfo_Fragment();
        champInfo_Fragment_Manager.beginTransaction().replace(R.id.container,fragment).commit();

        home_btn=(Button)findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), Home_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        search_btn=(Button)findViewById(R.id.search_btn);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index=1;
                onActivityChange(index);
            }
        });
        champinfo_btn=(Button)findViewById(R.id.champinfo_btn);
        champinfo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index=2;
                onActivityChange(index);
            }
        });
        ranking_btn=(Button)findViewById(R.id.rank_btn);
        ranking_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index=3;
                onActivityChange(index);
            }
        });
        community_btn=(Button)findViewById(R.id.board_btn);
        community_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index=4;
                onActivityChange(index);
            }
        });
        onActivityChange(index);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        intent = new Intent(getApplicationContext(), Home_Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void onActivityChange(int index){
        switch(index){
            case 1:
                intent = new Intent(this, Search_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("type", index);
                startActivity(intent);
                search_btn.setSelected(true);
                champinfo_btn.setSelected(false);
                ranking_btn.setSelected(false);
                community_btn.setSelected(false);
                break;
            case 2:
                search_btn.setSelected(false);
                champinfo_btn.setSelected(true);
                ranking_btn.setSelected(false);
                community_btn.setSelected(false);
                break;
            case 3:
                intent = new Intent(this, Ranking_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("type", index);
                startActivity(intent);
                search_btn.setSelected(false);
                champinfo_btn.setSelected(false);
                ranking_btn.setSelected(true);
                community_btn.setSelected(false);
                break;
            case 4:
                intent = new Intent(this, Community_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("type", index);
                startActivity(intent);
                search_btn.setSelected(false);
                champinfo_btn.setSelected(false);
                ranking_btn.setSelected(false);
                community_btn.setSelected(true);
                break;
        }
    }
}
