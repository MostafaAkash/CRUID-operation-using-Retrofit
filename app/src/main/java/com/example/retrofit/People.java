package com.example.retrofit;

import com.google.gson.annotations.SerializedName;

public class People {

    @SerializedName("Id")
     private int id;

    @SerializedName("Name")
    private String name;

    @SerializedName("Roll")
    private String roll;

    @SerializedName("Age")
    private int age;

    @SerializedName("Image Path")
    private String imagePath;

    public People(int id, String name, String roll, int age, String imagePath) {
        this.id = id;
        this.name = name;
        this.roll = roll;
        this.age = age;
        this.imagePath = imagePath;
    }


    public String getImagePath() {
        return imagePath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRoll() {
        return roll;
    }

    public int getAge() {
        return age;
    }
}
