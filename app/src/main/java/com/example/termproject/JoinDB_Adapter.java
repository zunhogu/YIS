package com.example.termproject;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JoinDB_Adapter
{
    protected static final String TAG = "DataAdapter";

    // TODO : TABLE 이름을 명시해야함
    protected static final String TABLE_NAME = "Login_Info";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public JoinDB_Adapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public JoinDB_Adapter createDatabase() throws SQLException
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

    public JoinDB_Adapter open() throws SQLException
    {
        try
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getWritableDatabase();
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

    public void join_writeDB(String id, String pwd, String nickname, int power){
        try{
            mDb.execSQL("INSERT INTO Login_Info VALUES ('"+ id +"', '"+ pwd +"'," +
                    "'"+ nickname +"', "+ 1 +");");
            mDb.close();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "join_writeDB >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public List getTableData()
    {
        try
        {
            // Table 이름 -> antpool_bitcoin 불러오기
            String sql ="SELECT * FROM " + TABLE_NAME;

            // 모델 넣을 리스트 생성
            List userList = new ArrayList();

            // TODO : 모델 선언
            Login_item login_item = null;

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                // 칼럼의 마지막까지
                while( mCur.moveToNext() ) {

                    // TODO : 커스텀 모델 생성
                    login_item = new Login_item();

                    // TODO : Record 기술
                    // id, name, account, privateKey, secretKey, Comment
                    login_item.setID(mCur.getString(0));
                    login_item.setPWD(mCur.getString(1));
                    login_item.setNICKNAME(mCur.getString(2));
                    login_item.setPOWER(mCur.getInt(3));

                    // 리스트에 넣기
                    userList.add(login_item);
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

}