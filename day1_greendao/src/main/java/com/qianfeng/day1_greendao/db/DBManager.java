package com.qianfeng.day1_greendao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.qianfeng.day1_greendao.bean.User;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by xray on 16/9/22.
 */
public class DBManager {

    private final DaoMaster.DevOpenHelper mOpenHelper;
    private Context mContext;
    public static final String DB_NAME = "test_db";
    private static DBManager mManager = null;

    private DBManager(Context mContext) {
        this.mContext = mContext;
        mOpenHelper = new DaoMaster.DevOpenHelper(mContext,DB_NAME,null);
    }

    public static DBManager getInstance(Context context){
        if(mManager == null){
            synchronized (DBManager.class){
                if(mManager == null){
                    mManager = new DBManager(context);
                }
            }
        }
        return mManager;
    }

    public SQLiteDatabase getWritableDatabase(){
        return mOpenHelper.getWritableDatabase();
    }

    public SQLiteDatabase getReadableDatabase(){
        return mOpenHelper.getReadableDatabase();
    }

    public UserDao getUserDao(){
        SQLiteDatabase db = getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        UserDao userDao = daoMaster.newSession().getUserDao();
        return userDao;
    }

    public AddressDao getAddressDao(){
        SQLiteDatabase db = getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        AddressDao dao = daoMaster.newSession().getAddressDao();
        return dao;
    }

    public void insertUser(User user){
        getUserDao().insert(user);
    }

    public List<User> getUsers(){
        QueryBuilder<User> queryBuilder = getUserDao().queryBuilder();
        return queryBuilder.list();
    }

    public void deleteUser(User user){
        getUserDao().delete(user);
    }

    public void updateUser(User user){
        getUserDao().update(user);
    }
}
