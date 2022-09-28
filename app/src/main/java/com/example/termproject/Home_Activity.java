package com.example.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Home_Activity extends AppCompatActivity {
    public static Login_item current_login;
    private Button search_btn;
    private Button champInfo_btn;
    private Button ranking_btn;
    private Button community_btn;
    private Button logout_btn;

    private FragmentManager manager;
    private Fragment searchFragment;
    private Fragment champInfoFragment;
    private Fragment rankFragment;
    private Fragment boardFragment;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        setTitle("LIS");

        Intent intent=getIntent();
        if(current_login==null){
            current_login = new Login_item();
            current_login.setID(intent.getStringExtra("ID"));
            current_login.setPWD(intent.getStringExtra("PASSWORD"));
            current_login.setNICKNAME(intent.getStringExtra("NICKNAME"));
            current_login.setPOWER(intent.getIntExtra("POWER", 0));
        }



        search_btn=(Button)findViewById(R.id.home_serach_btn);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(getApplicationContext(),Search_Activity.class);
                intent.putExtra("id",current_login.getID());
                intent.putExtra("type", 1);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        champInfo_btn=(Button)findViewById(R.id.home_champinfo_btn);
        champInfo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(getApplicationContext(),ChampInfo_Activity.class);
                intent.putExtra("id",current_login.getID());
                intent.putExtra("type", 2);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        ranking_btn=(Button)findViewById(R.id.home_ranking_btn);
        ranking_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(getApplicationContext(),Ranking_Activity.class);
                intent.putExtra("id",current_login.getID());
                intent.putExtra("type", 3);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        community_btn=(Button)findViewById(R.id.home_community_btn);
        community_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(getApplicationContext(),Community_Activity.class);
                intent.putExtra("id",current_login.getID());
                intent.putExtra("type", 4);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        logout_btn = (Button) findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(getApplicationContext(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                current_login=null;
                startActivity(intent);
            }
        });
    }
}

