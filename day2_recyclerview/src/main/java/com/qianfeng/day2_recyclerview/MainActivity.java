package com.qianfeng.day2_recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener{


    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnGridView).setOnClickListener(this);
        findViewById(R.id.btnListView).setOnClickListener(this);
        findViewById(R.id.btnStaggered).setOnClickListener(this);
        initDatas();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setOnItemClickListener(new MyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,((TextView)view).getText(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this,((TextView)view).getText(),Toast.LENGTH_LONG).show();
                mAdapter.remove(position);
            }
        });
    }

    private void initDatas(){
        mDatas = new ArrayList<String>();
        for(int i = 0;i < 1000;i++){
            mDatas.add("Number "+i);
        }
    }

    interface MyOnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.item,parent,false));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.textView.setText(mDatas.get(position));
            holder.textView.setLayoutParams(new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    (int)(Math.random() * 200 + 50)));
            if(onItemClickListener != null){
                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(v,position);
                    }
                });
                holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        onItemClickListener.onItemLongClick(v,position);
                        return false;
                    }
                });
            }
        }

        private MyOnItemClickListener onItemClickListener;
        public void setOnItemClickListener(MyOnItemClickListener listener){
            this.onItemClickListener = listener;
        }

        public void add(int position,String text){
            mDatas.add(position, text);
            this.notifyItemInserted(position);
        }

        public void remove(int position){
            mDatas.remove(position);
            this.notifyItemRemoved(position);
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            public MyViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.id_num);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnListView:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.btnGridView:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
                break;
            case R.id.btnStaggered:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
                break;
            case R.id.add_btn:
                mAdapter.add(1,"new");
                break;
            case R.id.remove_btn:
                mAdapter.remove(0);
                break;
        }
    }




}
