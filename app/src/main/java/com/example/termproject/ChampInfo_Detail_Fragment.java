package com.example.termproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class ChampInfo_Detail_Fragment extends Fragment {
    private ChampInfo_Activity activity;
    private ChampInfo_Fragment champInfoFragment;
    private Button backButton;
    private Button deleteButton;
    private TextView champName;
    private TextView champLine;
    private TextView champinfo_skill_1, champinfo_skill_2, champinfo_skill_3;
    private ChampInfo_Item item;
    private ChampInfoDataAdapter adapter;

    private ImageView champDetailImg;
    private ImageView champDetailHardChampImg;
    private ImageView champDetailEasyCahmpImg;
    private ImageView champDetailItemImg1;
    private ImageView champDetailItemImg2;
    private ImageView champDetailItemImg3;
    private ImageView champDetailSpellImg1;
    private ImageView champDetailSpellImg2;
    private ImageView champDetailPassiveSkillImg;
    private ImageView champDetailQSkillImg;
    private ImageView champDetailWSkillImg;
    private ImageView champDetailESkillImg;
    private ImageView champDetailRSkillImg;
    private ImageView champDetailRuneImg1;
    private ImageView champDetailRuneImg2;

    private String cName, cLine, cDspell, cFspell, cSkillTree, cMainRune, cSubRune, cItem_1, cItem_2,
            cItem_3, cHardChampion, cEasyChampion;
    private Bitmap cImage, cPassive, cQskill, cWskill, cEskill, cRskill;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity=(ChampInfo_Activity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.champinfo_detail_fragment, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            item = (ChampInfo_Item) bundle.getSerializable("item");
            item = (ChampInfo_Item) bundle.getSerializable("item");
            cName = item.getcName();
            cImage = item.getcImage();
            cLine = item.getcLine();
            cDspell = item.getcDspell();
            cFspell = item.getcFspell();
            cPassive = item.getcPassive();
            cQskill = item.getcQskill();
            cWskill = item.getcWskill();
            cEskill = item.getcEskill();
            cRskill = item.getcRskill();
            cSkillTree = item.getcSkillTree();
            cMainRune = item.getcMainRune();
            cSubRune = item.getcSubRune();
            cItem_1 = item.getcItem_1();
            cItem_2 = item.getcItem_2();
            cItem_3 = item.getcItem_3();
            cHardChampion = item.getcHardChampion();
            cEasyChampion = item.getcEasyChampion();


            adapter = (ChampInfoDataAdapter) bundle.getSerializable("adapter");
        }

        //ChampInfo_Fragment에서 전달 받은 객체를 화면에 뿌려줌
        champinfo_skill_1 = (TextView) rootView.findViewById(R.id.champinfo_skill_1);
        champinfo_skill_2 = (TextView) rootView.findViewById(R.id.champinfo_skill_2);
        champinfo_skill_3 = (TextView) rootView.findViewById(R.id.champinfo_skill_3);
        champName = (TextView) rootView.findViewById(R.id.champinfo_detail_name);
        champLine = (TextView) rootView.findViewById(R.id.champinfo_detail_line);
        champDetailImg = (ImageView) rootView.findViewById(R.id.champ_detail_img);
        champDetailHardChampImg = (ImageView) rootView.findViewById(R.id.champinfo_detail_hard_img);
        champDetailEasyCahmpImg = (ImageView) rootView.findViewById(R.id.champinfo_detail_easy_img);
        champDetailItemImg1 = (ImageView) rootView.findViewById(R.id.champinfo_detail_item_img1);
        champDetailItemImg2 = (ImageView) rootView.findViewById(R.id.champinfo_detail_item_img2);
        champDetailItemImg3 = (ImageView) rootView.findViewById(R.id.champinfo_detail_item_img3);
        champDetailSpellImg1 = (ImageView) rootView.findViewById(R.id.champinfo_detail_spell_img1);
        champDetailSpellImg2 = (ImageView) rootView.findViewById(R.id.champinfo_detail_spell_img2);
        champDetailPassiveSkillImg = (ImageView) rootView.findViewById(R.id.champinfo_detail_passive_img);
        champDetailQSkillImg = (ImageView) rootView.findViewById(R.id.champinfo_detail_Qskill_img);
        champDetailWSkillImg = (ImageView) rootView.findViewById(R.id.champinfo_detail_Wskill_img);
        champDetailESkillImg = (ImageView) rootView.findViewById(R.id.champinfo_detail_Eskill_img);
        champDetailRSkillImg = (ImageView) rootView.findViewById(R.id.champinfo_detail_Rskill_img);
        champDetailRuneImg1 = (ImageView) rootView.findViewById(R.id.champinfo_detail_rune_img1);
        champDetailRuneImg2 = (ImageView) rootView.findViewById(R.id.champinfo_detail_rune_img2);

        champName.setText(cName);
        champLine.setText(cLine);
        champDetailImg.setImageBitmap(cImage);
        champDetailPassiveSkillImg.setImageBitmap(cPassive);
        champDetailQSkillImg.setImageBitmap(cQskill);
        champDetailWSkillImg.setImageBitmap(cWskill);
        champDetailESkillImg.setImageBitmap(cEskill);
        champDetailRSkillImg.setImageBitmap(cRskill);
        String[] cSkillTree2 = cSkillTree.split("");
        champinfo_skill_1.setText(cSkillTree2[0]);
        champinfo_skill_2.setText(cSkillTree2[1]);
        champinfo_skill_3.setText(cSkillTree2[2]);
        champDetailItemImg1.setImageBitmap(adapter.getJoinItem(cItem_1));
        champDetailItemImg2.setImageBitmap(adapter.getJoinItem(cItem_2));
        champDetailItemImg3.setImageBitmap(adapter.getJoinItem(cItem_3));
        champDetailSpellImg1.setImageBitmap(adapter.getJoinSpell(cDspell));
        champDetailSpellImg2.setImageBitmap(adapter.getJoinSpell(cFspell));
        champDetailRuneImg1.setImageBitmap(adapter.getJoinRune(cMainRune));
        champDetailRuneImg2.setImageBitmap(adapter.getJoinRune(cSubRune));
        champDetailHardChampImg.setImageBitmap(adapter.getJoinChampion(cHardChampion));
        champDetailEasyCahmpImg.setImageBitmap(adapter.getJoinChampion(cEasyChampion));

        backButton = (Button) rootView.findViewById(R.id.champinfo_detail_back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //뒤로 이동
                champInfoFragment = new ChampInfo_Fragment();
                ChampInfo_Activity.champInfo_Fragment_Manager.beginTransaction().replace(R.id.container, champInfoFragment).commit();
            }
        });
        deleteButton = (Button) rootView.findViewById(R.id.champinfo_detail_delete_btn);
        final String[] select = {"삭제하기", "취소하기"};
        if (Home_Activity.current_login.getPOWER() == 1) {
            deleteButton.setEnabled(false);
        } else {
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(activity);
                    dlg.setTitle("정말 삭제하시겠습니까?");
                    dlg.setItems(select, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (select[which] == "삭제하기" && Home_Activity.current_login.getPOWER() == 2) {
                                adapter.delete(item);
                                Toast.makeText(rootView.getContext().getApplicationContext(), "삭제하였습니다.", Toast.LENGTH_LONG).show();
                                //데이터베이스에서 삭제후 뒤로 이동
                                champInfoFragment = new ChampInfo_Fragment();
                                ChampInfo_Activity.champInfo_Fragment_Manager.beginTransaction().replace(R.id.container, champInfoFragment).commit();
                            } else if (select[which] == "취소하기") {

                            }
                        }
                    });
                    dlg.show();
                }
            });
        }
        return rootView;
    }

}