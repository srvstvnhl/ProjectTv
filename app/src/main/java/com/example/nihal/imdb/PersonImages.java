package com.example.nihal.imdb;

import java.util.ArrayList;


public class PersonImages {

   // @SerializedName("profiles")
    private ArrayList<Profiles> profiles;

    public ArrayList<Profiles> getProfileList() {
        return profiles;
    }

    public void setProfiles(ArrayList<Profiles> profiles) {
        this.profiles = profiles;
    }
}
