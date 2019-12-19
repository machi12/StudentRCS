package com.example.teacherrcs;

/**
 * 自定义学生类
 */
public class Student {

    private String name;

    private int imageId;

    public Student(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }

}
