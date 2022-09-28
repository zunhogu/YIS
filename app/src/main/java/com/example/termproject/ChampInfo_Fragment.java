package com.example.termproject;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ChampInfo_Fragment extends Fragment {
    private ChampInfo_Activity activity;
    private ChampInfo_Detail_Fragment champDetailFragment;
    private ListView TopChampInfoListView;
    private ListView JunggleChampInfoListView;
    private ListView MidChampInfoListView;
    private ListView AdChampInfoListView;
    private ListView SupportChampInfoListView;
    private ChampInfo_View_Adapter TopChampInfoAdapter;
    private ChampInfo_View_Adapter JunggleChampInfoAdapter;
    private ChampInfo_View_Adapter MidChampInfoAdapter;
    private ChampInfo_View_Adapter AdChampInfoAdapter;
    private ChampInfo_View_Adapter SupportChampInfoAdapter;
    public static ChampInfoDataAdapter adapter;
    private ArrayList<ChampInfo_Item> champinfo_list;
    private String selectLine="top";


    private Button TopButton;
    private Button JunggleButton;
    private Button MidButton;
    private Button AdButton;
    private Button SupportButton;
    private Button AddButton;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity=(ChampInfo_Activity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //프래그먼트 구성
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.champinfo_fragment,container,false);

        adapter = new ChampInfoDataAdapter(rootView.getContext().getApplicationContext());
        adapter.createDatabase();
        adapter.open();
        champinfo_list = adapter.getTableData();

        AddButton=(Button)rootView.findViewById(R.id.champinfo_add_btn);
        if (Home_Activity.current_login.getPOWER() == 1) {
            AddButton.setEnabled(false);
        }else {
            AddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Home_Activity.current_login.getPOWER() == 2) {
                        ChampInfo_Add_Fragment champInfoAddFragment = new ChampInfo_Add_Fragment();
                        Bundle bundle = new Bundle();
                        //bundle.putSerializable("adapter",adapter);
                        champInfoAddFragment.setArguments(bundle);
                        ChampInfo_Activity.champInfo_Fragment_Manager.beginTransaction().replace(R.id.container, champInfoAddFragment).commit();
                    }
                }
            });
        }

        TopButton=(Button)rootView.findViewById(R.id.champinfo_top_btn);
        TopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLine="top";
                ListViewChange(selectLine);
            }
        });
        JunggleButton=(Button)rootView.findViewById(R.id.champinfo_junggle_btn);
        JunggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLine="junggle";
                ListViewChange(selectLine);
            }
        });
        MidButton=(Button)rootView.findViewById(R.id.champinfo_mid_btn);
        MidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLine="mid";
                ListViewChange(selectLine);
            }
        });
        AdButton=(Button)rootView.findViewById(R.id.champinfo_ad_btn);
        AdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLine="ad";
                ListViewChange(selectLine);
            }
        });
        SupportButton=(Button)rootView.findViewById(R.id.champinfo_support_btn);
        SupportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLine="support";
                ListViewChange(selectLine);
            }
        });

        TopChampInfoListView=(ListView)rootView.findViewById(R.id.champinfo_top_listview);
        TopChampInfoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //선택된 리스트뷰에 해당하는 객체 생성
                ChampInfo_Item selectedItem = (ChampInfo_Item) TopChampInfoAdapter.getItem(position);
                //생성된 객체를 ChapmInfo_Detail_Fragment로 전달
                ChampInfo_Detail_Fragment champion_detail_fragment=new ChampInfo_Detail_Fragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", selectedItem);
                bundle.putSerializable("adapter",adapter);
                champion_detail_fragment.setArguments(bundle);
                ChampInfo_Activity.champInfo_Fragment_Manager.beginTransaction().replace(R.id.container,champion_detail_fragment).commit();
            }
        });
        JunggleChampInfoListView=(ListView)rootView.findViewById(R.id.champinfo_junggle_listview);
        JunggleChampInfoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //선택된 리스트뷰에 해당하는 객체 생성
                ChampInfo_Item selectedItem = (ChampInfo_Item) JunggleChampInfoAdapter.getItem(position);
                //생성된 객체를 ChapmInfo_Detail_Fragment로 전달
                ChampInfo_Detail_Fragment champion_detail_fragment=new ChampInfo_Detail_Fragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", selectedItem);
                bundle.putSerializable("adapter",adapter);
                champion_detail_fragment.setArguments(bundle);
                ChampInfo_Activity.champInfo_Fragment_Manager.beginTransaction().replace(R.id.container,champion_detail_fragment).commit();
            }
        });
        MidChampInfoListView=(ListView)rootView.findViewById(R.id.champinfo_mid_listview);
        MidChampInfoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChampInfo_Item selectedItem = (ChampInfo_Item) MidChampInfoAdapter.getItem(position);
                //생성된 객체를 ChapmInfo_Detail_Fragment로 전달
                ChampInfo_Detail_Fragment champion_detail_fragment=new ChampInfo_Detail_Fragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", selectedItem);
                bundle.putSerializable("adapter",adapter);
                champion_detail_fragment.setArguments(bundle);
                ChampInfo_Activity.champInfo_Fragment_Manager.beginTransaction().replace(R.id.container,champion_detail_fragment).commit();
            }
        });
        AdChampInfoListView=(ListView)rootView.findViewById(R.id.champinfo_ad_listview);
        AdChampInfoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChampInfo_Item selectedItem = (ChampInfo_Item) AdChampInfoAdapter.getItem(position);
                //생성된 객체를 ChapmInfo_Detail_Fragment로 전달
                ChampInfo_Detail_Fragment champion_detail_fragment=new ChampInfo_Detail_Fragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", selectedItem);
                bundle.putSerializable("adapter",adapter);
                champion_detail_fragment.setArguments(bundle);
                ChampInfo_Activity.champInfo_Fragment_Manager.beginTransaction().replace(R.id.container,champion_detail_fragment).commit();
            }
        });
        SupportChampInfoListView=(ListView)rootView.findViewById(R.id.champinfo_support_listview);
        SupportChampInfoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChampInfo_Item selectedItem = (ChampInfo_Item) SupportChampInfoAdapter.getItem(position);
                //생성된 객체를 ChapmInfo_Detail_Fragment로 전달
                ChampInfo_Detail_Fragment champion_detail_fragment=new ChampInfo_Detail_Fragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", selectedItem);
                bundle.putSerializable("adapter",adapter);
                champion_detail_fragment.setArguments(bundle);
                ChampInfo_Activity.champInfo_Fragment_Manager.beginTransaction().replace(R.id.container,champion_detail_fragment).commit();
            }
        });

        TopChampInfoAdapter=new ChampInfo_View_Adapter();
        JunggleChampInfoAdapter=new ChampInfo_View_Adapter();
        MidChampInfoAdapter=new ChampInfo_View_Adapter();
        AdChampInfoAdapter=new ChampInfo_View_Adapter();
        SupportChampInfoAdapter=new ChampInfo_View_Adapter();

        //각 라인별로 리스트 뷰가 존재하고, 라인별로 구분하여 리스트뷰에 데이터 입력(데이터베이스)
        for(int i=0; i<champinfo_list.size(); i++) {
            if(champinfo_list.get(i).getcLine().equals("탑"))
                TopChampInfoAdapter.addItem(champinfo_list.get(i));
            else if(champinfo_list.get(i).getcLine().equals("정글"))
                JunggleChampInfoAdapter.addItem(champinfo_list.get(i));
            else if(champinfo_list.get(i).getcLine().equals("미드"))
                MidChampInfoAdapter.addItem(champinfo_list.get(i));
            else if(champinfo_list.get(i).getcLine().equals("원딜"))
                AdChampInfoAdapter.addItem(champinfo_list.get(i));
            else if(champinfo_list.get(i).getcLine().equals("서폿"))
                SupportChampInfoAdapter.addItem(champinfo_list.get(i));
        }
        TopChampInfoListView.setAdapter(TopChampInfoAdapter);
        JunggleChampInfoListView.setAdapter(JunggleChampInfoAdapter);
        MidChampInfoListView.setAdapter(MidChampInfoAdapter);
        AdChampInfoListView.setAdapter(AdChampInfoAdapter);
        SupportChampInfoListView.setAdapter(SupportChampInfoAdapter);

        ListViewChange(selectLine);
        return rootView;
        //
    }

    //버튼에 따른 리스트뷰 전환
    public void ListViewChange(String selectLine){
        switch(selectLine){
            case "top":
                TopButton.setSelected(true);;
                JunggleButton.setSelected(false);
                MidButton.setSelected(false);
                AdButton.setSelected(false);
                SupportButton.setSelected(false);

                TopChampInfoListView.setVisibility(View.VISIBLE);
                JunggleChampInfoListView.setVisibility(View.INVISIBLE);
                MidChampInfoListView.setVisibility(View.INVISIBLE);
                AdChampInfoListView.setVisibility(View.INVISIBLE);
                SupportChampInfoListView.setVisibility(View.INVISIBLE);
                break;
            case "junggle":
                TopButton.setSelected(false);
                JunggleButton.setSelected(true);
                MidButton.setSelected(false);
                AdButton.setSelected(false);
                SupportButton.setSelected(false);

                TopChampInfoListView.setVisibility(View.INVISIBLE);
                JunggleChampInfoListView.setVisibility(View.VISIBLE);
                MidChampInfoListView.setVisibility(View.INVISIBLE);
                AdChampInfoListView.setVisibility(View.INVISIBLE);
                SupportChampInfoListView.setVisibility(View.INVISIBLE);
                break;
            case "mid":
                TopButton.setSelected(false);
                JunggleButton.setSelected(false);
                MidButton.setSelected(true);
                AdButton.setSelected(false);
                SupportButton.setSelected(false);

                TopChampInfoListView.setVisibility(View.INVISIBLE);
                JunggleChampInfoListView.setVisibility(View.INVISIBLE);
                MidChampInfoListView.setVisibility(View.VISIBLE);
                AdChampInfoListView.setVisibility(View.INVISIBLE);
                SupportChampInfoListView.setVisibility(View.INVISIBLE);
                break;
            case "ad":
                TopButton.setSelected(false);
                JunggleButton.setSelected(false);
                MidButton.setSelected(false);
                AdButton.setSelected(true);
                SupportButton.setSelected(false);

                TopChampInfoListView.setVisibility(View.INVISIBLE);
                JunggleChampInfoListView.setVisibility(View.INVISIBLE);
                MidChampInfoListView.setVisibility(View.INVISIBLE);
                AdChampInfoListView.setVisibility(View.VISIBLE);
                SupportChampInfoListView.setVisibility(View.INVISIBLE);
                break;
            case "support":
                TopButton.setSelected(false);
                JunggleButton.setSelected(false);
                MidButton.setSelected(false);
                AdButton.setSelected(false);
                SupportButton.setSelected(true);

                TopChampInfoListView.setVisibility(View.INVISIBLE);
                JunggleChampInfoListView.setVisibility(View.INVISIBLE);
                MidChampInfoListView.setVisibility(View.INVISIBLE);
                AdChampInfoListView.setVisibility(View.INVISIBLE);
                SupportChampInfoListView.setVisibility(View.VISIBLE);
                break;
        }
    }
}