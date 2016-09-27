package com.qianfeng.day3_searchhistroy.db;

import android.content.Context;

import com.qianfeng.day3_searchhistroy.bean.SearchHistory;

import java.util.List;

/**
 * Created by xray on 16/9/26.
 */

public class SearchDaoImpl implements SearchDao {

    private SearchHistoryDao mDao;

    public SearchDaoImpl(Context context){
        mDao = DBManager.getSearchHistroyDao(context);
    }

    @Override
    public void recordSearchHistory(SearchHistory history) {
        List<SearchHistory> list = mDao.queryBuilder().where(
                SearchHistoryDao.Properties.SearchWord.eq(history.getSearchWord()))
                .orderDesc(SearchHistoryDao.Properties.SearchCount)
                .build()
                .list();
        if(list.size() == 0){
            mDao.insert(history);
        }else{
            SearchHistory history1 = list.get(0);
            history1.setSearchCount(history1.getSearchCount() + 1);
            mDao.update(history1);
        }
    }

    @Override
    public List<SearchHistory> getSearchHistory(String word) {
        List<SearchHistory> list = mDao.queryBuilder()
                .where(SearchHistoryDao.Properties.SearchWord.like(word+"%"))
                .orderDesc(SearchHistoryDao.Properties.SearchCount)
                .build()
                .list();
        return list;
    }

    @Override
    public void clearSearchHistroy() {
        mDao.deleteAll();
    }
}
