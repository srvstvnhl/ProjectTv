package com.example.nihal.imdb;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


 public class PopularMovies {

    @SerializedName("results")
    private ArrayList<Movie> movies;

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
