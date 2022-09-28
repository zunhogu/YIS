package com.example.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    private List<Login_item> login_itemList;
    private EditText inputId;
    private EditText inputPassWord;
    private Button login_btn;
    private Button join_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("LIS");

        join_btn = (Button)findViewById(R.id.join_btn);
        login_btn=(Button)findViewById(R.id.login_btn);
        inputId=(EditText)findViewById(R.id.id_input);
        inputPassWord=(EditText)findViewById(R.id.password_input);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //로그인정보 받아와서 id, password에 저장
                String id=inputId.getText().toString();
                String password=inputPassWord.getText().toString();
                //로그인 정보를 데이터베이스와 비교
                initLoadDB();
                int power = 0;
                for(int i=0; i<login_itemList.size(); i++){
                    if(id.compareTo(login_itemList.get(i).getID())==0) {
                        if(password.compareTo(login_itemList.get(i).getPWD())==0){
                            String nicknmae = login_itemList.get(i).getNICKNAME();
                            power = login_itemList.get(i).getPOWER();

                            Intent intent = new Intent(getApplicationContext(), Home_Activity.class);
                            //로그인 액티비티에서 홈 액티비티로 이동
                            intent.putExtra("ID",id);
                            intent.putExtra("PASSWORD", password);
                            intent.putExtra("NICKNAME", nicknmae);
                            intent.putExtra("POWER", power);
                            if(power==2){
                                Toast.makeText(getApplicationContext(), "관리자 로그인입니다!", Toast.LENGTH_SHORT).show();
                            }
                            if(power==1) {
                                Toast.makeText(getApplicationContext(), "사용자 로그인 완료!", Toast.LENGTH_SHORT).show();
                            }
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    }
                }
                if(power==0){
                    Toast.makeText(getApplicationContext(), "아이디, 비밀번호를 확인하세요!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Join_Activity.class);
                Toast.makeText(getApplicationContext(), "회원가입 페이지 이동!", Toast.LENGTH_SHORT).show();

                //로그인 액티비티에서 회원가입 액티비티로 이동
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
    private void initLoadDB() {

        LoginDB_Adapter login_DbHelper = new LoginDB_Adapter(getApplicationContext());
        login_DbHelper.createDatabase();
        login_DbHelper.open();

        // db에 있는 값들을 model을 적용해서 넣는다.
        login_itemList = login_DbHelper.getTableData();

        // db 닫기
        login_DbHelper.close();
    }
}