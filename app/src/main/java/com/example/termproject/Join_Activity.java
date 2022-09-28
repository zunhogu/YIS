package com.example.termproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Join_Activity extends AppCompatActivity {
    public Login_item current_login;
    private EditText make_id, make_pwd, make_nickname;
    private Button make_btn, back_btn;
    private SQLiteDatabase sqlDB;
    private DataBaseHelper myHelper;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_activity);
        setTitle("LIS");

        make_id = (EditText) findViewById(R.id.make_id);
        make_pwd = (EditText) findViewById(R.id.make_pwd);
        make_nickname = (EditText) findViewById(R.id.make_name);
        make_btn = (Button) findViewById(R.id.make_btn);
        back_btn = (Button) findViewById(R.id.back_btn);

        make_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String new_id = make_id.getText().toString();
                String new_pwd = make_pwd.getText().toString();
                String new_nickname = make_nickname.getText().toString();
                if(new_id==null||new_id.isEmpty()||new_id.contains(" ")||
                        new_pwd==null|| new_pwd.isEmpty()|| new_pwd.contains(" ")||
                        new_nickname==null ||new_nickname.isEmpty()||new_nickname.contains(" "))
                    Toast.makeText(getApplicationContext(), "공백을 확인하세요.", Toast.LENGTH_SHORT).show();
                else {
                    JoinDB_Adapter join_DBHelper = new JoinDB_Adapter(getApplicationContext());
                    join_DBHelper.createDatabase();
                    join_DBHelper.open();
                    join_DBHelper.join_writeDB(new_id, new_pwd, new_nickname, 1);
                    // db 닫기
                    join_DBHelper.close();
                    Toast.makeText(getApplicationContext(), "회원가입 완료!", Toast.LENGTH_SHORT).show();

                    //회원가입 종료하고 로그인 액티비티로 이동
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

}


