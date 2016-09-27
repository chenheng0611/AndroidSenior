package com.qianfeng.day3_searchhistroy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by xray on 16/9/26.
 */

public class DBManager {

    public static final String DB_NAME = "history.db";
    private static SearchHistoryDao sSearchHistroyDao;

    public static SearchHistoryDao getSearchHistroyDao(Context context){
        if(sSearchHistroyDao == null){
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
            SQLiteDatabase database = devOpenHelper.getWritableDatabase();
            DaoMaster daoMaster = new DaoMaster(database);
            sSearchHistroyDao = daoMaster.newSession().getSearchHistoryDao();
        }
        return sSearchHistroyDao;
    }
}
