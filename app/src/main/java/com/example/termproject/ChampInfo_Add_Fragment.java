package com.example.termproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.InputStream;

public class ChampInfo_Add_Fragment extends Fragment {
    private ViewGroup rootView;
    private ChampInfo_Activity activity;
    private ChampInfo_Fragment champInfoFragment;
    private ChampInfoDataAdapter adapter;
    private ChampInfo_Item item;
    private Button AddCompleteButton;
    private Button AddCancelButton;

    private ImageView champAddImg;
    private EditText champAddName;
    private EditText champLine;
    private EditText champAddHardChamp;
    private EditText champAddEasyChamp;
    private EditText champAddItem1;
    private EditText champAddItem2;
    private EditText champAddItem3;
    private EditText champAddSpell1;
    private EditText champAddSpell2;
    private EditText champAddSkillTree1;
    private EditText champAddSkillTree2;
    private EditText champAddSkillTree3;
    private ImageView champAddPassiveSkillImg;
    private ImageView champAddQSkillImg;
    private ImageView champAddWSkillImg;
    private ImageView champAddESkillImg;
    private ImageView champAddRSkillImg;
    private EditText champAddRune1;
    private EditText champAddRune2;
    private EditText champAddWinRate;
    private EditText champAddPickRate;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity=(ChampInfo_Activity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.champinfo_add_fragment,container,false);
//        Bundle bundle = getArguments();
//        if(bundle != null){
//            adapter=(ChampInfoDataAdapter) bundle.getSerializable("adapter");
//        }
        //ImageView 클릭시 앨범에서 사진가져오기
        champAddImg=(ImageView)rootView.findViewById(R.id.champ_add_img);
        champAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });
        champAddName=(EditText)rootView.findViewById(R.id.champinfo_add_name_input);
        champLine=(EditText)rootView.findViewById(R.id.champinfo_line_input);
        champAddHardChamp=(EditText) rootView.findViewById(R.id.champinfo_easy_input);
        champAddEasyChamp=(EditText) rootView.findViewById(R.id.champinfo_hard_input);
        champAddItem1=(EditText) rootView.findViewById(R.id.champinfo_add_item_input1);
        champAddItem2=(EditText) rootView.findViewById(R.id.champinfo_add_item_input2);
        champAddItem3=(EditText) rootView.findViewById(R.id.champinfo_add_item_input3);
        champAddSpell1=(EditText) rootView.findViewById(R.id.champinfo_add_item_spell1);
        champAddSpell2=(EditText) rootView.findViewById(R.id.champinfo_add_item_spell2);
        champAddSkillTree1=(EditText)rootView.findViewById(R.id.champinfo_skill_1);
        champAddSkillTree2=(EditText)rootView.findViewById(R.id.champinfo_skill_2);
        champAddSkillTree3=(EditText)rootView.findViewById(R.id.champinfo_skill_3);
        champAddPassiveSkillImg=(ImageView)rootView.findViewById(R.id.champinfo_add_passive_img);
        champAddPassiveSkillImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 2);
            }
        });
        champAddQSkillImg=(ImageView)rootView.findViewById(R.id.champinfo_add_Qskill_img);
        champAddQSkillImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 3);
            }
        });
        champAddWSkillImg=(ImageView)rootView.findViewById(R.id.champinfo_add_Wskill_img);
        champAddWSkillImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 4);
            }
        });
        champAddESkillImg=(ImageView)rootView.findViewById(R.id.champinfo_add_Eskill_img);
        champAddESkillImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 5);
            }
        });
        champAddRSkillImg=(ImageView)rootView.findViewById(R.id.champinfo_add_Rskill_img);
        champAddRSkillImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 6);
            }
        });
        champAddRune1=(EditText) rootView.findViewById(R.id.champinfo_rune_1);
        champAddRune2=(EditText) rootView.findViewById(R.id.champinfo_rune_2);
        champAddWinRate=(EditText) rootView.findViewById(R.id.champinfo_add_winrate_input);
        champAddPickRate=(EditText) rootView.findViewById(R.id.champinfo_add_pickrate_input);

        //완료버튼 클릭시
        AddCompleteButton=(Button)rootView.findViewById(R.id.champinfo_add_complete_btn);
        AddCompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable1 = null, drawable2 = null, drawable3 = null, drawable4 = null, drawable5 = null, drawable6 = null;
                //입력받은 값을 데이터베이스에 저장후 뒤로이동
                champInfoFragment = new ChampInfo_Fragment();
                try {
                    drawable1 = champAddImg.getDrawable();
                    drawable2 = champAddImg.getDrawable();
                    drawable3 = champAddImg.getDrawable();
                    drawable4 = champAddImg.getDrawable();
                    drawable5 = champAddImg.getDrawable();
                    drawable6 = champAddImg.getDrawable();
                    Bitmap bitmap1 = ((BitmapDrawable) drawable1).getBitmap();
                    Bitmap bitmap2 = ((BitmapDrawable) drawable2).getBitmap();
                    Bitmap bitmap3 = ((BitmapDrawable) drawable3).getBitmap();
                    Bitmap bitmap4 = ((BitmapDrawable) drawable4).getBitmap();
                    Bitmap bitmap5 = ((BitmapDrawable) drawable5).getBitmap();
                    Bitmap bitmap6 = ((BitmapDrawable) drawable6).getBitmap();

//                  BitmapDrawable drawable2 = (BitmapDrawable) champAddPassiveSkillImg.getDrawable();
//                  Bitmap bitmap2 = drawable2.getBitmap();
//                  BitmapDrawable drawable3 = (BitmapDrawable) champAddQSkillImg.getDrawable();
//                  Bitmap bitmap3 = drawable3.getBitmap();
//                  BitmapDrawable drawable4 = (BitmapDrawable) champAddWSkillImg.getDrawable();
//                  Bitmap bitmap4 = drawable4.getBitmap();
//                  BitmapDrawable drawable5 = (BitmapDrawable) champAddESkillImg.getDrawable();
//                  Bitmap bitmap5 = drawable5.getBitmap();
//                  BitmapDrawable drawable6 = (BitmapDrawable) champAddRSkillImg.getDrawable();
//                  Bitmap bitmap6 = drawable6.getBitmap();
                    item = new ChampInfo_Item(champAddName.getText().toString(), bitmap1, champLine.getText().toString(), champAddSpell1.getText().toString(), champAddSpell2.getText().toString(), bitmap2, bitmap3, bitmap4, bitmap5, bitmap6, champAddSkillTree1.getText().toString() + champAddSkillTree2.getText().toString() + champAddSkillTree3.getText().toString(), champAddRune1.getText().toString(), champAddRune2.getText().toString(), champAddItem1.getText().toString(), champAddItem2.getText().toString(), champAddItem3.getText().toString(), champAddHardChamp.getText().toString(), champAddEasyChamp.getText().toString(), champAddWinRate.getText().toString(), champAddPickRate.getText().toString());
                    ChampInfo_Fragment.adapter.insert(item);
                    Toast.makeText(rootView.getContext().getApplicationContext(), "챔피언을 등록하였습니다.", Toast.LENGTH_LONG).show();
                    ChampInfo_Activity.champInfo_Fragment_Manager.beginTransaction().replace(R.id.container, champInfoFragment).commit();

                }
                catch(Exception e){
                    Toast.makeText(rootView.getContext().getApplicationContext(), "빈 칸이 있습니다.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //취소버튼 클릭시
        AddCancelButton=(Button)rootView.findViewById(R.id.champinfo_add_cancel_btn);
        AddCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                champInfoFragment=new ChampInfo_Fragment();
                ChampInfo_Activity.champInfo_Fragment_Manager.beginTransaction().replace(R.id.container,champInfoFragment).commit();
            }
        });

        return rootView;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 &&  data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(rootView.getContext().getContentResolver(), uri);
                ImageView imageView = (ImageView) rootView.findViewById(R.id.champ_add_img);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == 2 &&  data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(rootView.getContext().getContentResolver(), uri);
                ImageView imageView = (ImageView) rootView.findViewById(R.id.champinfo_add_passive_img);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == 3 &&  data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(rootView.getContext().getContentResolver(), uri);
                ImageView imageView = (ImageView) rootView.findViewById(R.id.champinfo_add_Qskill_img);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == 4 &&  data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(rootView.getContext().getContentResolver(), uri);
                ImageView imageView = (ImageView) rootView.findViewById(R.id.champinfo_add_Wskill_img);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == 5 &&  data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(rootView.getContext().getContentResolver(), uri);
                ImageView imageView = (ImageView) rootView.findViewById(R.id.champinfo_add_Eskill_img);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == 6 &&  data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(rootView.getContext().getContentResolver(), uri);
                ImageView imageView = (ImageView) rootView.findViewById(R.id.champinfo_add_Rskill_img);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

