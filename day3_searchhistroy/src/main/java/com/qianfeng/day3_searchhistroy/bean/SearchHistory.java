package com.qianfeng.day3_searchhistroy.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xray on 16/9/26.
 */
@Entity(nameInDb = "history_tb")
public class SearchHistory {

    @Id(autoincrement = true)
    private Long id;
    private int searchCount;
    @NotNull
    private String searchWord;
    @Generated(hash = 1094202392)
    public SearchHistory(Long id, int searchCount, @NotNull String searchWord) {
        this.id = id;
        this.searchCount = searchCount;
        this.searchWord = searchWord;
    }
    public SearchHistory(int searchCount, @NotNull String searchWord) {
        this.searchCount = searchCount;
        this.searchWord = searchWord;
    }
    @Generated(hash = 1905904755)
    public SearchHistory() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getSearchCount() {
        return this.searchCount;
    }
    public void setSearchCount(int searchCount) {
        this.searchCount = searchCount;
    }
    public String getSearchWord() {
        return this.searchWord;
    }
    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
