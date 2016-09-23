package com.qianfeng.day1_greendao;

import android.test.InstrumentationTestCase;

import com.qianfeng.day1_greendao.bean.User;
import com.qianfeng.day1_greendao.db.DBManager;

/**
 * Created by xray on 16/9/23.
 */
public class GreenDaoTest extends InstrumentationTestCase {

    public void testInsertUser(){
        User user = new User();
//        user.setId(0L);
        user.setUsername("admin");
        user.setPassword("123");
        DBManager.getInstance(getInstrumentation().getTargetContext())
                .insertUser(user);
    }
}
