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

public class RankingDataAdapter implements Serializable {
    protected static final String TAG = "CommunityDataAdapter";

    // TODO : TABLE 이름을 명시해야함
    protected static final String TABLE_NAME = "Ranking_info";
    // 등수, 이름, lp 순서

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public RankingDataAdapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public RankingDataAdapter createDatabase() throws SQLException
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

    public RankingDataAdapter open() throws SQLException
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

    public ArrayList<Ranking_Item> getTableData()
    {
        try
        {
            // Table 이름 -> antpool_bitcoin 불러오기
            String sql ="SELECT * FROM " + TABLE_NAME;

            // 모델 넣을 리스트 생성
            ArrayList<Ranking_Item> userList = new ArrayList();

            // TODO : 모델 선언
            //User user = null;
            //Community_Item item = null;
            Ranking_Item item = null;
            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                // 칼럼의 마지막까지
                while( mCur.moveToNext() ) {

                    // TODO : 커스텀 모델 생성
                    //item = new Ranking_Item(mCur.getString(0),mCur.getString(1),mCur.getString(3));
                    item = new Ranking_Item();
                    // TODO : Record 기술

                    item.setrNum(Integer.parseInt(mCur.getString(0)));
                    item.setrName(mCur.getString(1));
                    item.setrLp(Integer.parseInt(mCur.getString(2)));
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
    /*
    public void insert(Ranking_Item item) {
        if(mDb != null) {
            String sql = "insert into Ranking_info values(?, ?, ?)";
            String[] params = {Integer.toString(item.getrNum()), item.getrName(), Integer.toString(item.getrLp())};
            mDb.execSQL(sql, params);
        }
    }
    */
    public void update(Ranking_Item item){
        if(mDb != null) {
            String sql = "update Ranking_info set rName=\""+item.getrName()+"\", lp=\""+item.getrLp()+"\" where ranking="+item.getrNum();
            mDb.execSQL(sql);
        }
    }
/*
    public void delete(Ranking_Item item){
        if(mDb != null) {
            String sql = "delete from Community_info where \"index\"="+item.getrNum();
            mDb.execSQL(sql);
        }
    }*/
}