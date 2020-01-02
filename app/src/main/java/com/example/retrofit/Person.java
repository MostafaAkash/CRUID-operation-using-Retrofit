package com.example.retrofit;

import com.google.gson.annotations.SerializedName;

public class Person {
    @SerializedName("Id")
    private int id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Roll")
    private String roll;
    @SerializedName("Age")
    private int age;

    public Person(int id, String name, String roll, int age, String imagePath) {
        this.id = id;
        this.name = name;
        this.roll = roll;
        this.age = age;
        this.imagePath = imagePath;
    }

    @SerializedName("Image Path")
    private String imagePath;

    public Person(int id, String name, String roll, int age) {
        this.id = id;
        this.name = name;
        this.roll = roll;
        this.age = age;
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
