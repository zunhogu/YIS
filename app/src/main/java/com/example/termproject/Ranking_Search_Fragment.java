package com.example.termproject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;


class Ranking_SummonerNameTask extends AsyncTask<String, Void, String> {
    private String clientKey = "#########################";
    private String receiveMsg;
    private String name, api_key;

    Ranking_SummonerNameTask(String name, String api_key) {
        this.name = name;
        this.api_key = api_key;
    }

    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + name + "?api_key=" + api_key);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("x-waple-authorization", clientKey);

            if (conn.getResponseCode() == 200) { //검색 성공
                receiveMsg = "OK";
            } else { //존재하지 않는 소환사 검색
                receiveMsg = "NO";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receiveMsg;
    }
}


public class Ranking_Search_Fragment extends Fragment {
    private String api_key = "RGAPI-ca87803a-d2aa-49ce-9bfb-c6400295cd2c";
    private String name, Total;

    ViewGroup rootView;
    Ranking_Activity activity;

    Button search_go_btn;
    EditText search_name;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Ranking_Activity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.search_fragment, container, false);

        search_name = (EditText) rootView.findViewById(R.id.search_name);
        search_go_btn = (Button) rootView.findViewById(R.id.search_go_btn);

        Bundle bundle = getArguments();
        if(bundle != null){
            search_name.setText( bundle.getString("name"));
            name = bundle.getString("name");
        }


        try {
            Total = new SummonerNameTask(name, api_key).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (Total.equals("OK")) {
            Intent intent = new Intent(getActivity(), PlayerSearch.class);
            intent.putExtra("name", name);
            startActivity(intent);
        }else {
            Toast.makeText(getActivity(), "존재하지 않는 소환사", Toast.LENGTH_SHORT).show();
        }
        /*search_go_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = search_name.getText().toString();

                try {
                    Total = new SummonerNameTask(name, api_key).execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (Total.equals("OK")) {
                    Intent intent = new Intent(getActivity(), PlayerSearch.class);
                    intent.putExtra("name", name);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "존재하지 않는 소환사", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
        return rootView;
    }


}