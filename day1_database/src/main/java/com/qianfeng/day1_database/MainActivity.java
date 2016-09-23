package com.qianfeng.day1_database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.qianfeng.day1_ormlite.bean.Student;
import com.qianfeng.day1_ormlite.bean.db.MySqlHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mStudentAgeEt;
    private EditText mStudentNameEt;
    private EditText mStudentScoreEt;
    private Button mSubmitBtn;
    private SQLiteDatabase mDatabase;
    private ListView mStudentInfoLv;
    private List<Student> mStudents;
    private StudentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initViews();
    }

    private void initData() {
        mDatabase = new MySqlHelper(this).getWritableDatabase();
        mStudents = getStudents();
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
        mDatabase.execSQL("insert into student_tb(name,age,score) values(?,?,?)",
                new Object[]{mStudentNameEt.getText().toString(),
                mStudentAgeEt.getText().toString(),
                mStudentScoreEt.getText().toString()});
        mAdapter.refresh();
    }

    private List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("select * from student_tb",null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            float score = cursor.getFloat(cursor.getColumnIndex("score"));
            students.add(new Student(id,name,age,score));
        }
        cursor.close();
        return students;
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
