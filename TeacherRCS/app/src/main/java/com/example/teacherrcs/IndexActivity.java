package com.example.teacherrcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * 教师端的主页面
 * 主要实现了自定义的ListView和随机点名功能
 */
public class IndexActivity extends AppCompatActivity {

    //private TextView text_tname;
    private List<Student> studentList = new ArrayList<>();

    private static final String TAG = "IndexActivity";

    private Button call;

    private ListView listView;

    private TextView textCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        //接收从上个页面传过来的教师名
        Intent intent=getIntent();
        final String tname=intent.getStringExtra("tname");

        call = (Button)findViewById(R.id.call);
        textCall = (TextView)findViewById(R.id.text_call);

        call.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int len = studentList.size();
                int random = (int)(Math.random() * len);
                String thisName = studentList.get(random).getName();
                textCall.setText("当前点到: " + thisName);
            }
        }));

//        text_tname = (TextView)findViewById(R.id.tname);
//
//        text_tname.setText(tname + "老师");

        initStudents();

        StudentAdapter adapter = new StudentAdapter(IndexActivity.this,
                R.layout.student_item, studentList);
        listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);


        Toast.makeText(IndexActivity.this, "欢迎" + tname + "老师", Toast.LENGTH_SHORT).show();


    }

    //初始化学生数据
    private void initStudents(){
        Log.d(TAG, "start");
        Uri uri = Uri.parse("content://com.example.studentrcs.contentprovider.MyDatabaseProvider/student/1");
        Cursor cursor = getContentResolver().query(uri, null, null,
                null, null);
        Log.d(TAG, "end");
        if(cursor != null){
            while(cursor.moveToNext()){
                String sname = cursor.getString(cursor.getColumnIndex("sname"));
                Log.d(TAG, sname);
                Student student = new Student(sname, R.drawable.student);
                studentList.add(student);
            }
        }
        else{

        }
    }
}
