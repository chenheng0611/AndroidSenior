package com.qianfeng.day1_greendao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;

import com.qianfeng.day1_greendao.bean.Address;
import com.qianfeng.day1_greendao.bean.User;
import com.qianfeng.day1_greendao.db.DBManager;
import com.qianfeng.day1_greendao.db.UserDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "xray";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        insertTest();
//        updateTest();
//        deleteTest();
        insertListTest();
//        queryTest();
//        queryWhereTest();
//        insertAddressList();
        queryWhereTest2();
//        queryWhereTest3();
    }

    private void updateTest() {
        DBManager.getInstance(this)
                .updateUser(new User(2L,"bobo","bobo123"));
    }

    private void deleteTest() {
        User user = new User();
        user.setId(0L);
        DBManager.getInstance(this)
                .deleteUser(user);
    }

    private void queryTest() {
        List<User> users = DBManager.getInstance(this).getUsers();
        Log.i(TAG, "queryTest: "+users.size());
        for(User user : users){
            Log.i(TAG, "queryTest: "+user);
        }
    }

    private void queryWhereTest() {
        List<User> users = DBManager.getInstance(this).getUserDao()
                .queryBuilder()
                .where(UserDao.Properties.Id.between(5,10),
                        UserDao.Properties.Username.like("admin%"))
                .orderAsc(UserDao.Properties.Id)
                .list();
        Log.i(TAG, "queryTestWhere: "+users.size());
        for(User user : users){
            Log.i(TAG, "queryTestWhere: "+user);
        }
    }

    private void insertTest() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123");
        DBManager.getInstance(this)
                .insertUser(user);
    }

    private void insertListTest(){
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername("admin"+i);
            user.setPassword("pwd"+i);
            users.add(user);
        }
        DBManager.getInstance(this)
                .getUserDao().insertInTx(users);
    }

    private void insertAddressList(){
        List<Address> addresses = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Address address = new Address(5L,"China","Wuhan","Street"+i);
            addresses.add(address);
        }
        DBManager.getInstance(this)
                .getAddressDao().insertInTx(addresses);
    }

    private void queryWhereTest2() {
        List<User> users = DBManager.getInstance(this).getUserDao()
                .queryBuilder()
                .where(UserDao.Properties.Id.eq(5L))
                .list();
        Log.i(TAG, "queryTestWhere2: "+users.size());
        for(User user : users){
            Log.i(TAG, "user: "+user);
            Log.i(TAG, "getAddressList: "+user.getAddressList());
        }
    }

    private void queryWhereTest3() {
        List<Address> users = DBManager.getInstance(this).getAddressDao()
                .queryBuilder()
                .list();
        Log.i(TAG, "queryTestWhere3: "+users.size());
        for(Address user : users){
            Log.i(TAG, "queryTestWhere3: "+user);
        }
    }
}
