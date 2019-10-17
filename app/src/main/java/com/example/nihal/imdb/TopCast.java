package com.example.nihal.imdb;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class TopCast {

    private long id;

     class cast {

         @SerializedName("cast_id")
        private int castId;
       private int id;
       private String name;
       private String character;

       @SerializedName("profile_path")
       private String castImage;
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getCastId() {
            return castId;
        }

        public String getCastImage() {
            return castImage;
        }

        public String getCharacter() {
            return character;
        }

    }

    public long getId() {
        return id;
    }

    ArrayList<cast> cast;

    public ArrayList<cast> getList() {
        return cast;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setList(ArrayList<cast> list) {
        this.cast= list;
    }
}



