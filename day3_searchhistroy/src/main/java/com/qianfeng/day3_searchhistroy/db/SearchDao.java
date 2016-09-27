package com.qianfeng.day3_searchhistroy.db;

import com.qianfeng.day3_searchhistroy.bean.SearchHistory;

import java.util.List;

/**
 * Created by xray on 16/9/26.
 */

public interface SearchDao {

    public void recordSearchHistory(SearchHistory history);
    public List<SearchHistory> getSearchHistory(String word);
    public void clearSearchHistroy();
}
