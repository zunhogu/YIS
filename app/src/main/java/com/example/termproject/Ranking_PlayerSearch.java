package com.example.termproject;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.concurrent.ExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

//SUMMONER-V4 정보를 불러옴
class Ranking_SummonerTask extends AsyncTask<String, Void, String> {
    private String clientKey = "#########################";

    private String str, receiveMsg;
    private final String ID = "########";
    private String name, api_key;

    Ranking_SummonerTask(String name, String api_key) {
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

            InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuffer buffer = new StringBuffer();
            while ((str = reader.readLine()) != null) {
                buffer.append(str);
            }
            receiveMsg = buffer.toString();
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receiveMsg;
    }
}

//LEAGUE-V4 정보를 불러옴
class Ranking_LeagueTask extends AsyncTask<String, Void, String> {
    String clientKey = "#########################";

    private String str, receiveMsg;
    private final String ID = "########";
    String id, api_key;

    Ranking_LeagueTask(String id, String api_key) {
        this.id = id;
        this.api_key = api_key;
    }

    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL("https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/" + id + "?api_key=" + api_key);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("x-waple-authorization", clientKey);
            InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuffer buffer = new StringBuffer();
            while ((str = reader.readLine()) != null) {
                buffer.append(str);
            }
            receiveMsg = buffer.toString();
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receiveMsg;
    }
}

//MATCH-V5 by-puuid 정보를 불러옴
class Ranking_matchIDTask extends AsyncTask<String, Void, String> {
    private String clientKey = "#########################";

    private String str, receiveMsg;
    private final String ID = "########";
    private String puuid, api_key;

    Ranking_matchIDTask(String puuid, String api_key) {
        this.puuid = puuid;
        this.api_key = api_key;
    }

    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL("https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/" + puuid + "/ids?start=0&count=10&api_key=" + api_key);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("x-waple-authorization", clientKey);

            InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuffer buffer = new StringBuffer();
            while ((str = reader.readLine()) != null) {
                buffer.append(str);
            }
            receiveMsg = buffer.toString();
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receiveMsg;
    }
}

//MATCH-V5 matchId 정보를 불러옴
class Ranking_matchInfoTask extends AsyncTask<String, Void, String> {
    private String clientKey = "#########################";

    private String str, receiveMsg;
    private final String ID = "########";
    private String matchID, api_key;

    Ranking_matchInfoTask(String matchID, String api_key) {
        this.matchID = matchID;
        this.api_key = api_key;
    }

    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL("https://asia.api.riotgames.com/lol/match/v5/matches/" + matchID + "?api_key=" + api_key);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("x-waple-authorization", clientKey);

            InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuffer buffer = new StringBuffer();
            while ((str = reader.readLine()) != null) {
                buffer.append(str);
            }
            receiveMsg = buffer.toString();
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receiveMsg;
    }
}

public class Ranking_PlayerSearch extends AppCompatActivity {
    private int i, j, idx = 99;

    private String api_key = "RGAPI-ca87803a-d2aa-49ce-9bfb-c6400295cd2c";
    private String name; //
    private String id;
    private String puuid;
    private String tier; //
    private String rank; //
    private String leaguePoints; //
    private String wins; //
    private String losses; //

    private String queueType = "";
    private String summonerTotal = "";
    private String matchIdTotal = "";
    private String leagueTotal = "";
    private String matchInfoTotal = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_detail_fragment);
        TextView summoner = (TextView) findViewById(R.id.search_detail_name);
        TextView tiertv = (TextView) findViewById(R.id.search_detail_ranktier);
        TextView rankWL = (TextView) findViewById(R.id.search_detail_rankWL);
        ImageView imgview = (ImageView) findViewById(R.id.imageView);


        Button BackBtn = (Button) findViewById(R.id.search_detail_back_btn);
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ranking_Fragment fragment = new Ranking_Fragment();
                Ranking_Activity.ranking_Fragment_Manager.beginTransaction().replace(R.id.container,fragment).commit();
            }
        });

        TextView[] iswintv = new TextView[10];
        iswintv[0] = (TextView) findViewById(R.id.iswin0);
        iswintv[1] = (TextView) findViewById(R.id.iswin1);
        iswintv[2] = (TextView) findViewById(R.id.iswin2);
        iswintv[3] = (TextView) findViewById(R.id.iswin3);
        iswintv[4] = (TextView) findViewById(R.id.iswin4);
        iswintv[5] = (TextView) findViewById(R.id.iswin5);
        iswintv[6] = (TextView) findViewById(R.id.iswin6);
        iswintv[7] = (TextView) findViewById(R.id.iswin7);
        iswintv[8] = (TextView) findViewById(R.id.iswin8);
        iswintv[9] = (TextView) findViewById(R.id.iswin9);

        TextView[] matchinfotv = new TextView[10];
        matchinfotv[0] = (TextView) findViewById(R.id.matchinfo0);
        matchinfotv[1] = (TextView) findViewById(R.id.matchinfo1);
        matchinfotv[2] = (TextView) findViewById(R.id.matchinfo2);
        matchinfotv[3] = (TextView) findViewById(R.id.matchinfo3);
        matchinfotv[4] = (TextView) findViewById(R.id.matchinfo4);
        matchinfotv[5] = (TextView) findViewById(R.id.matchinfo5);
        matchinfotv[6] = (TextView) findViewById(R.id.matchinfo6);
        matchinfotv[7] = (TextView) findViewById(R.id.matchinfo7);
        matchinfotv[8] = (TextView) findViewById(R.id.matchinfo8);
        matchinfotv[9] = (TextView) findViewById(R.id.matchinfo9);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        try {
            JSONParser jsonParser = new JSONParser();
            summonerTotal = new SummonerTask(name, api_key).execute().get();
            JSONObject summonerJson = (JSONObject) jsonParser.parse(summonerTotal);
            id = (String) summonerJson.get("id");
            puuid = (String) summonerJson.get("puuid");
            name = (String) summonerJson.get("name");


            leagueTotal = new LeagueTask(id, api_key).execute().get();
            JSONArray leagueArr = (JSONArray) jsonParser.parse(leagueTotal);
            int a = leagueArr.size();

            for (i = 0; i < a; i++) {
                JSONObject lJson = (JSONObject) leagueArr.get(i);
                queueType = (String) lJson.get("queueType");
                if (queueType.equals("RANKED_SOLO_5x5")) {
                    idx = i;
                    break;
                }
            }
            if (idx == 99) {
                tier = "UnRanked";
                imgview.setImageResource(R.drawable.unrank);
                rank = "";
                leaguePoints = "";
                wins = "";
                losses = "";
            } else {
                JSONObject leagueJson = (JSONObject) leagueArr.get(idx);
                tier = (String) leagueJson.get("tier");
                switch (tier) {
                    case "IRON":
                        imgview.setImageResource(R.drawable.iron);
                        break;
                    case "BRONZE":
                        imgview.setImageResource(R.drawable.bronze);
                        break;
                    case "SILVER":
                        imgview.setImageResource(R.drawable.silver);
                        break;
                    case "GOLD":
                        imgview.setImageResource(R.drawable.gold);
                        break;
                    case "PLATINUM":
                        imgview.setImageResource(R.drawable.platinum);
                        break;
                    case "DIAMOND":
                        imgview.setImageResource(R.drawable.diamond);
                        break;
                    case "MASTER":
                        imgview.setImageResource(R.drawable.master);
                        break;
                    case "GRANDMASTER":
                        imgview.setImageResource(R.drawable.grandmaster);
                        break;
                    case "CHALLENGER":
                        imgview.setImageResource(R.drawable.challenger);
                        break;
                }
                rank = (String) leagueJson.get("rank");
                int leaguepoints = Integer.parseInt(String.valueOf(leagueJson.get("leaguePoints")));
                leaguePoints = Integer.toString(leaguepoints) + "점";
                int winss = Integer.parseInt(String.valueOf(leagueJson.get("wins")));
                wins = Integer.toString(winss) + "승 ";
                int loss = Integer.parseInt(String.valueOf(leagueJson.get("losses")));
                losses = Integer.toString(loss) + "패";
            }
            summoner.setText(name);
            tiertv.setText(tier + " " + rank + " " + leaguePoints);
            rankWL.setText(wins + losses);


            matchIdTotal = new matchIDTask(puuid, api_key).execute().get();
            JSONArray matchIDArr = (JSONArray) jsonParser.parse(matchIdTotal);
            int b = matchIDArr.size();
            if (b == 0) {
                //전적 없음
            } else {
                String[] matchId = new String[b];
                String[] kills = new String[b];
                String[] assists = new String[b];
                String[] deaths = new String[b];
                String[] KDA = new String[b];
                String[] championName = new String[b];
                String[] goldEarned = new String[b];
                String[] gameMode = new String[b];
                String[] totalDamageDealtToChampions = new String[b];
                String[] totalMinionsKilled = new String[b];
                String[] iswin = new String[b];
                String[] gameTime = new String[b];

                for (i = 0; i < b; i++) {
                    matchId[i] = (String) matchIDArr.get(i);
                }

                for (i = 0; i < b; i++) {
                    matchInfoTotal = new matchInfoTask(matchId[i], api_key).execute().get();

                    JSONObject matchInfoJson = (JSONObject) jsonParser.parse(matchInfoTotal);
                    JSONObject matchInfoResult = (JSONObject) matchInfoJson.get("info");
                    long time = (long) matchInfoResult.get("gameCreation");
                    Date date = new Date(time);
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    gameTime[i] = format.format(date);
                    int gamemode = Integer.parseInt(String.valueOf(matchInfoResult.get("queueId")));
                    if (gamemode == 420) {
                        gameMode[i] = "랭크 게임";
                    } else if (gamemode == 450) {
                        gameMode[i] = "칼바람 나락";
                    } else {
                        gameMode[i] = "일반 게임";
                    }


                    JSONArray Res = (JSONArray) matchInfoResult.get("participants");
                    for (j = 0; j < 10; j++) {
                        JSONObject matchResJson = (JSONObject) Res.get(j);
                        String sumName = (String) matchResJson.get("summonerName");
                        if (sumName.equals(name)) {
                            idx = j;
                            break;
                        }
                    }
                    JSONObject jsonRes = (JSONObject) Res.get(idx);
                    championName[i] = (String) jsonRes.get("championName");
                    int kill = Integer.parseInt(String.valueOf(jsonRes.get("kills")));
                    int dth = Integer.parseInt(String.valueOf(jsonRes.get("deaths")));
                    int assi = Integer.parseInt(String.valueOf(jsonRes.get("assists")));
                    assists[i] = Integer.toString(assi);
                    deaths[i] = Integer.toString(dth);
                    kills[i] = Integer.toString(kill);
                    float d = (float) dth;
                    if (d == 0.0) {
                        KDA[i] = "Perfect";
                    } else {
                        float kda = (kill + assi) / d;
                        KDA[i] = String.format("%.2f", kda);
                    }
                    int ge = Integer.parseInt(String.valueOf(jsonRes.get("goldEarned")));
                    goldEarned[i] = Integer.toString(ge);
                    int tdtc = Integer.parseInt(String.valueOf(jsonRes.get("totalDamageDealtToChampions")));
                    totalDamageDealtToChampions[i] = Integer.toString(tdtc);
                    int tmk = Integer.parseInt(String.valueOf(jsonRes.get("totalMinionsKilled")));
                    totalMinionsKilled[i] = Integer.toString(tmk);
                    boolean IsWin = Boolean.parseBoolean(String.valueOf(jsonRes.get("win")));
                    iswin[i] = String.valueOf(IsWin);
                }
                for (i = 0; i < b; i++) {
                    if (iswin[i] == "true") {
                        iswintv[i].setBackgroundColor(Color.BLUE);
                        iswintv[i].setText("승");
                    } else {
                        iswintv[i].setBackgroundColor(Color.RED);
                        iswintv[i].setText("패");
                    }
                    matchinfotv[i].setText("게임모드=" + gameMode[i] + "\n플레이한 시간=" + gameTime[i] + "\n챔피언=" + championName[i] + "\n킬=" + kills[i] +
                            " 데스=" + deaths[i] + " 어시스트=" + assists[i] + "\nkda=" + KDA[i] + "\n골드=" + goldEarned[i] +
                            "\n데미지=" + totalDamageDealtToChampions[i] + "\ncs=" + totalMinionsKilled[i]);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
