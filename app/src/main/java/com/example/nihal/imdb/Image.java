package com.example.nihal.imdb;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;



public class Image {

    private long id;



    // backdrop having image url and id of movie

    class backdrops
    {
        @SerializedName("file_path")
        private String imagePath;

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }
    }

    ArrayList<backdrops> backdrops;


    public long getId() {
        return id;
    }

    public ArrayList<Image.backdrops> getBackdrops() {
        return backdrops;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBackdrops(ArrayList<Image.backdrops> backdrops) {
        this.backdrops = backdrops;
    }
}
