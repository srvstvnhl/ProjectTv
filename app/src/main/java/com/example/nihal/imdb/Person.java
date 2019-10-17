package com.example.nihal.imdb;

import com.google.gson.annotations.SerializedName;



public class Person {


    private String birthday;

    @SerializedName("id")
    private long person_id;
    private String name;
    private String biography;

    private String place_of_birth;
    private String profile_path;


    public String getName() {
        return name;
    }

    public long getPerson_id() {
        return person_id;
    }

    public String getBiography() {
        return biography;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public String getProfile_path() {
        return profile_path;
    }
}
