package com.qianfeng.day3_searchhistroy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qianfeng.day3_searchhistroy.bean.SearchHistory;
import com.qianfeng.day3_searchhistroy.db.SearchDao;
import com.qianfeng.day3_searchhistroy.db.SearchDaoImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.history_search_sv)
    SearchView mSearchView;

    @BindView(R.id.search_histroy_lv)
    ListView mSearchLv;

    @BindView(R.id.search_histroy_tv)
    TextView mSearchTv;

    List<SearchHistory> mSearchHistoryList;

    private SearchDao mSearchDao;
    private SearchHistoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mSearchDao = new SearchDaoImpl(this);

        initViews();
    }

    private void initViews() {
        mSearchHistoryList = new ArrayList<>();
        mAdapter = new SearchHistoryAdapter();
        mSearchLv.setAdapter(mAdapter);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchHistory history = new SearchHistory(0,query);
                mSearchDao.recordSearchHistory(history);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.refreshHistory(newText);
                return false;
            }
        });
    }

    public void onClearHistory(View view) {
        mSearchDao.clearSearchHistroy();
        mSearchHistoryList.clear();
        mAdapter.notifyDataSetChanged();
    }

    class SearchHistoryAdapter extends BaseAdapter{

        public void refreshHistory(String word){
            mSearchHistoryList = mSearchDao.getSearchHistory(word);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mSearchHistoryList == null ? 0 :
                    mSearchHistoryList.size();
        }

        @Override
        public Object getItem(int position) {
            return mSearchHistoryList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder holder = null;
            if(view == null){
                view = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.search_item,parent,false);
                holder = new ViewHolder(view);
            }else{
                holder = (ViewHolder) view.getTag();
            }
            holder.mSearchText.setText(mSearchHistoryList.get(position).getSearchWord());
            return view;
        }

        class ViewHolder {
            @BindView(R.id.search_tv)
            TextView mSearchText;
            public ViewHolder(View view){
                ButterKnife.bind(this,view);
                view.setTag(this);
            }
        }
    }
}
