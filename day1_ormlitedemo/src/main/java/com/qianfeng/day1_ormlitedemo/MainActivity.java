package com.qianfeng.day1_ormlitedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mStudentAgeEt;
    private EditText mStudentNameEt;
    private EditText mStudentScoreEt;
    private Button mSubmitBtn;
    private ListView mStudentInfoLv;
    private List<Student> mStudents;
    private StudentAdapter mAdapter;
    private SQLHelper mSqlHelper;
    private Dao<Student, ?> mStudentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initViews();
    }

    private void initData() {
        mSqlHelper = new SQLHelper(this);
        try {
            mStudentDao = mSqlHelper.getDao(Student.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initViews() {
        mStudentAgeEt = (EditText)findViewById(R.id.student_age_et);
        mStudentNameEt = (EditText) findViewById(R.id.student_name_et);
        mStudentScoreEt = (EditText)findViewById(R.id.student_score_et);
        mSubmitBtn = (Button)findViewById(R.id.submit_btn);
        mStudentInfoLv = (ListView)findViewById(R.id.student_info_lv);
        mAdapter = new StudentAdapter();
        mStudentInfoLv.setAdapter(mAdapter);

        mSubmitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_btn:
                insertStudent();
                break;
        }
    }

    private void insertStudent() {
        try {
            int age = Integer.parseInt(mStudentAgeEt.getText().toString());
            String name = mStudentNameEt.getText().toString();
            float score = Float.parseFloat(mStudentScoreEt.getText().toString());
            mStudentDao.create(new Student(0,name,age,score));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mAdapter.refresh();
    }

    private List<Student> getStudents(){
        try {
            List<Student> students = mStudentDao.queryForAll();
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    class StudentAdapter extends BaseAdapter{

        public void refresh(){
            mStudents = getStudents();
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mStudents == null ? 0 : mStudents.size();
        }

        @Override
        public Object getItem(int position) {
            return mStudents.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder holder;
            if(view == null){
                view = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.student_info,parent,false);
                holder = new ViewHolder(view);
            }else{
                holder = (ViewHolder) view.getTag();
            }
            Student student = mStudents.get(position);
            holder.mStudentAgeTv.setText(student.getAge()+"");
            holder.mStudentIdTv.setText(student.getId()+"");
            holder.mStudentNameTv.setText(student.getName());
            holder.mStudentScoreTv.setText(student.getScore()+"");
            return view;
        }

        class ViewHolder{
            TextView mStudentIdTv;
            TextView mStudentNameTv;
            TextView mStudentAgeTv;
            TextView mStudentScoreTv;

            public ViewHolder(View view){
                mStudentAgeTv = (TextView) view.findViewById(R.id.student_age_tv);
                mStudentNameTv = (TextView) view.findViewById(R.id.student_name_tv);
                mStudentIdTv = (TextView) view.findViewById(R.id.student_id_tv);
                mStudentScoreTv = (TextView) view.findViewById(R.id.student_score_tv);
                view.setTag(this);
            }
        }
    }
}
