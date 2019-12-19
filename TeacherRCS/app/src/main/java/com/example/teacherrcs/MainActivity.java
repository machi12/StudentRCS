package com.example.teacherrcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 教师端的登陆页面
 * 主要实现了跨应用的数据库共享
 */
public class MainActivity extends AppCompatActivity {

    private EditText name;

    private EditText password;

    private Button login;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.name);

        password = (EditText)findViewById(R.id.password);

        login = (Button)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Machi", "start");
                String tid = name.getText().toString();
                String pwd = password.getText().toString();
                Log.d("Machi", tid);
                Log.d("Machi", pwd);
                Uri uri = Uri.parse("content://com.example.studentrcs.contentprovider.MyDatabaseProvider/teacher/" + tid);
//                int id = Integer.parseInt(tid);
//                Uri resultUri = ContentUris.withAppendedId(uri, id);

                Cursor cursor = getContentResolver().query(uri, null, null,
                        null, null);
                Log.d("Machi", "end");

                if(cursor.moveToFirst()){
                    String db_tid = cursor.getString(cursor.getColumnIndex("tid"));
                    String db_tname = cursor.getString(cursor.getColumnIndex("tname"));
                    String db_pwd = cursor.getString(cursor.getColumnIndex("pwd"));
                    Log.d("Machi", "" + db_tid);
                    Log.d("Machi", "" + db_tname);
                    if(pwd.equals(db_pwd)) {
                        Intent intent = new Intent(MainActivity.this, IndexActivity.class);
                        intent.putExtra("tname", db_tname);
                        intent.putExtra("tid", tid);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                    }
                    cursor.close();
                }
                else{
                    Toast.makeText(MainActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
//                if(cursor != null){
//                    String tname = cursor.getString(cursor.getColumnIndex("tname"));
//                    Intent intent = new Intent(MainActivity.this, IndexActivity.class);
//                    intent.putExtra("tname", tname);
//                    startActivity(intent);
//                }
            }
        });
    }
}
