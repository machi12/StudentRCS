package com.example.teacherrcs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * 自定义适配器
 */
public class StudentAdapter extends ArrayAdapter<Student> {

    private int resourceId;

    public StudentAdapter(Context context, int textViewResourceId, List<Student> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Student student = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView studentName = (TextView)view.findViewById(R.id.text_view);
        ImageView studentImage = (ImageView)view.findViewById(R.id.image);
        studentImage.setImageResource(student.getImageId());
        studentName.setText(student.getName());
        return view;
    }
}
