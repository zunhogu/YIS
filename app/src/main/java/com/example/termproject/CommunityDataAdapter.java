package com.example.termproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CommunityDataAdapter implements Serializable {
    protected static final String TAG = "CommunityDataAdapter";

    // TODO : TABLE 이름을 명시해야함
    protected static final String TABLE_NAME = "Community_info";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public CommunityDataAdapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public CommunityDataAdapter createDatabase() throws SQLException
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

    public CommunityDataAdapter open() throws SQLException
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

    public ArrayList<Community_Item> getTableData()
    {
        try
        {
            // Table 이름 -> antpool_bitcoin 불러오기
            String sql ="SELECT * FROM " + TABLE_NAME;

            // 모델 넣을 리스트 생성
            ArrayList<Community_Item> userList = new ArrayList();

            // TODO : 모델 선언
            //User user = null;
            Community_Item item = null;

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                // 칼럼의 마지막까지
                while( mCur.moveToNext() ) {

                    // TODO : 커스텀 모델 생성
                    //user = new User();
                    item = new Community_Item();
                    // TODO : Record 기술
                    // id, name, account, privateKey, secretKey, Comment
                    item.setIndex(mCur.getString(0));
                    item.setNICKNAME(mCur.getString(1));
                    item.setTitle(mCur.getString(2));
                    item.setContents(mCur.getString(3));
                    item.setLike(mCur.getString(4));
                    item.setUnlike(mCur.getString(5));
                    item.setInteraction(mCur.getString(6));
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
    public void insert(Community_Item item) {
        if(mDb != null) {
            String sql = "insert into Community_info values(?, ?, ?, ?, ?, ?, ?)";
            String[] params = {item.getIndex(), item.getNICKNAME(), item.getTitle(), item.getContents(), item.getLike(), item.getUnlike(), "."};
            mDb.execSQL(sql, params);
        }
    }
    public void update(Community_Item item){
        if(mDb != null) {
            String sql = "update Community_info set Title=\""+item.getTitle()+"\", Contents=\""+item.getContents()+"\", \"Like\"="+item.getLike()+", \"UnLike\"="+item.getUnlike()+", Interaction=\""+item.getInteraction()+"\" where \"index\"="+item.getIndex();
            mDb.execSQL(sql);
        }
    }

    public void delete(Community_Item item){
        if(mDb != null) {
            String sql = "delete from Community_info where \"index\"="+item.getIndex();
            mDb.execSQL(sql);
        }
    }
}