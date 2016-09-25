package com.qianfeng.day1_butterknife;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BlankFragment extends Fragment {

    @BindView(R.id.list_view)
    ListView mListView;

    private List<String> mList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        ButterKnife.bind(this,view);
        initData();
        initViews();
        return view;
    }

    private void initViews() {
        mListView.setAdapter(new MyAdapter());
    }

    private void initData() {
        String item = getArguments().getCharSequence("tag", "item").toString();
        mList = new ArrayList<>();
        for(int i = 0;i < 20;i++){
            mList.add(item + i);
        }
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder vh;
            if(view == null){
                view = LayoutInflater.from(getActivity()).inflate(R.layout.item,parent,false);
                vh = new ViewHolder(view);
            }else{
                vh = (ViewHolder) view.getTag();
            }
            vh.mItemTv.setText(mList.get(position));
            return view;
        }

        class ViewHolder{
            @BindView(R.id.item_tv)
            TextView mItemTv;

            public ViewHolder(View view){
                ButterKnife.bind(this,view);
                view.setTag(this);
            }
        }
    }

}
