package com.example.termproject;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;

public class ChampInfoDataAdapter implements Serializable {
    protected static final String TAG = "ChampInfoDataAdapter";

    // TODO : TABLE 이름을 명시해야함
    protected static final String TABLE_NAME = "Champion_Info";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public ChampInfoDataAdapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public ChampInfoDataAdapter createDatabase() throws SQLException
    {
        try
        {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException)
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public ChampInfoDataAdapter open() throws SQLException
    {
        try
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }

    public ArrayList<ChampInfo_Item> getTableData()
    {
        try
        {
            // Table 이름 -> antpool_bitcoin 불러오기
            String sql ="SELECT * FROM " + TABLE_NAME;

            // 모델 넣을 리스트 생성
            ArrayList<ChampInfo_Item> userList = new ArrayList();

            // TODO : 모델 선언
            //User user = null;
            ChampInfo_Item item = null;

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                // 칼럼의 마지막까지
                while( mCur.moveToNext() ) {
                    // TODO : 커스텀 모델 생성
                    //user = new User();
                    item = new ChampInfo_Item();
                    // TODO : Record 기술
                    // id, name, account, privateKey, secretKey, Comment
                    item.setcName(mCur.getString(0));
                    item.setcImage(mCur.getBlob(1));
                    item.setcLine(mCur.getString(2));
                    item.setcDspell(mCur.getString(3));
                    item.setcFspell(mCur.getString(4));
                    item.setcPassive(mCur.getBlob(5));
                    item.setcQskill(mCur.getBlob(6));
                    item.setcWskill(mCur.getBlob(7));
                    item.setcEskill(mCur.getBlob(8));
                    item.setcRskill(mCur.getBlob(9));
                    item.setcSkillTree(mCur.getString(10));
                    item.setcMainRune(mCur.getString(11));
                    item.setcSubRune(mCur.getString(12));
                    item.setcItem_1(mCur.getString(13));
                    item.setcItem_2(mCur.getString(14));
                    item.setcItem_3(mCur.getString(15));
                    item.setcHardChampion(mCur.getString(16));
                    item.setcEasyChampion(mCur.getString(17));
                    item.setcWinRate(mCur.getString(18));
                    item.setcPickRate(mCur.getString(19));
                    // 리스트에 넣기
                    userList.add(item);
                }

            }
            return userList;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
    public void insert(ChampInfo_Item item) {
        if(mDb != null) {
            String sql;
            //이미지받아오기
            ByteArrayOutputStream stream1 =new ByteArrayOutputStream();
            item.getcImage().compress(Bitmap.CompressFormat.PNG,100,stream1);
            byte[] cImage = stream1.toByteArray();

            ByteArrayOutputStream stream2 =new ByteArrayOutputStream();
            item.getcImage().compress(Bitmap.CompressFormat.PNG,100,stream2);
            byte[] cPassive = stream2.toByteArray();

            ByteArrayOutputStream stream3 =new ByteArrayOutputStream();
            item.getcImage().compress(Bitmap.CompressFormat.PNG,100,stream3);
            byte[] cQskill = stream3.toByteArray();

            ByteArrayOutputStream stream4 =new ByteArrayOutputStream();
            item.getcImage().compress(Bitmap.CompressFormat.PNG,100,stream4);
            byte[] cWskill = stream4.toByteArray();

            ByteArrayOutputStream stream5 =new ByteArrayOutputStream();
            item.getcImage().compress(Bitmap.CompressFormat.PNG,100,stream5);
            byte[] cEskill = stream5.toByteArray();

            ByteArrayOutputStream stream6 =new ByteArrayOutputStream();
            item.getcImage().compress(Bitmap.CompressFormat.PNG,100,stream6);
            byte[] cRskill = stream6.toByteArray();

            sql = "insert into Champion_Info values(?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            Object[] params = {item.getcName(),cImage,item.getcLine(),item.getcDspell(),item.getcFspell(),cPassive,cQskill,cWskill,cEskill,cRskill,item.getcSkillTree(),item.getcMainRune(),item.getcSubRune(),item.getcItem_1(),item.getcItem_2(),item.getcItem_3(),item.getcHardChampion(),item.getcEasyChampion(),item.getcWinRate(),item.getcPickRate()};
            mDb.execSQL(sql, params);
        }
    }

    public void delete(ChampInfo_Item item){
        if(mDb != null) {
            String sql = "delete from Champion_Info where cName=\"" +item.getcName()+"\"";
            mDb.execSQL(sql);
        }
    }
    public Bitmap getJoinItem(String Items)
    {
        try
        {
            // Table 이름 -> antpool_bitcoin 불러오기
            String sql ="select distinct Iimage from Item_Info WHERE Iname='"+ Items+"'";

            Bitmap bitmap = null;
            byte[] bytes;
            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                while( mCur.moveToNext() ) {
                    // TODO : 커스텀 모델 생성
                    bytes = mCur.getBlob(0);
                    bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                }
                return bitmap;
            }
            return bitmap;

        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
    public Bitmap getJoinSpell(String Spells)
    {
        try
        {
            // Table 이름 -> antpool_bitcoin 불러오기
            String sql ="select distinct Simage from Spell_Info WHERE Sname='"+Spells+"'";

            Bitmap bitmap = null;
            byte[] bytes;
            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                while( mCur.moveToNext() ) {
                    // TODO : 커스텀 모델 생성
                    bytes = mCur.getBlob(0);
                    bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    return bitmap;
                }
            }
            return bitmap;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
    public Bitmap getJoinRune(String Runes)
    {
        try
        {
            // Table 이름 -> antpool_bitcoin 불러오기
            String sql ="select distinct Rimage from Rune_Info WHERE Rname='"+Runes+"'";

            Bitmap bitmap = null;
            byte[] bytes;
            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                while( mCur.moveToNext() ) {
                    // TODO : 커스텀 모델 생성
                    bytes = mCur.getBlob(0);
                    bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    return bitmap;
                }
            }
            return bitmap;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
    public Bitmap getJoinChampion(String item)
    {
        try
        {
            // Table 이름 -> antpool_bitcoin 불러오기
            String sql ="select distinct cImage from Champion_Info WHERE cName='"+item+"'";

            Bitmap bitmap = null;
            byte[] bytes;
            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                while( mCur.moveToNext() ) {
                    // TODO : 커스텀 모델 생성
                    bytes = mCur.getBlob(0);
                    bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    return bitmap;
                }
            }
            return bitmap;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
}