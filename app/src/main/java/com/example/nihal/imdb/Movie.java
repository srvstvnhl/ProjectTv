
  package com.example.nihal.imdb;

  import com.google.gson.annotations.SerializedName;

  import java.util.ArrayList;

   public class Movie {

      private class Genre
       {
        String name;
        int id;

        public Genre(String name, int id) {
            this.name = name;
            this.id = id;
        }

           public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }
    }

       ArrayList<Genre> genre;

          String status;
          Boolean adult;

         private long id;

    @SerializedName("original_title")
    String movieName;

    @SerializedName("poster_path")
   String PosterPath;

      private String tagline;
       private String overview;

       @SerializedName("release_date")
       private String release;

          @SerializedName("backdrop_path")
          private String backdropPath;

        private long budget;

        @SerializedName("original_language")
        private String language;

        private boolean video;

      @SerializedName("vote_average")
       private double vote;



    public Boolean getAdult() {
        return adult;
    }

       public Movie(ArrayList<Genre> genre, String status, Boolean adult, long id, String movieName, String posterPath, String tagline, String overview, String backdropPath, long budget, String language, boolean video, double vote) {
           this.genre = genre;
           this.status = status;
           this.adult = adult;
           this.id = id;
           this.movieName = movieName;
           PosterPath = posterPath;
           this.tagline = tagline;
           this.overview = overview;
           this.backdropPath = backdropPath;
           this.budget = budget;
           this.language = language;
           this.video = video;
           this.vote = vote;
       }

       public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getPosterPath() {
        return PosterPath;
    }

       public String getRelease() {
           return release;
       }

       public void setRelease(String release) {
           this.release = release;
       }

       public void setId(long id) {
        this.id = id;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setPosterPath(String posterPath) {
        PosterPath = posterPath;
    }

    public long getId() {
        return id;
    }


    public boolean getVideo()
    {
        return video;
    }

    public String getMovieName() {
        return movieName;
    }

       public String getStatus() {
           return status;
       }

       public String getOverview() {
           return overview;
       }

       public double getVote() {
           return vote;
       }

       public long getBudget() {
           return budget;
       }

       public String getBackdropPath() {
           return backdropPath;
       }

       public String getLanguage() {
           return language;
       }


       public String getTagline() {
           return tagline;
       }

       public void setBudget(long budget) {
           this.budget = budget;
       }

       public void setBackdropPath(String backdropPath) {
           this.backdropPath = backdropPath;
       }

       public void setLanguage(String language) {
           this.language = language;
       }

       public void setOverview(String overview) {
           this.overview = overview;
       }

       public void setStatus(String status) {
           this.status = status;
       }

       public void setTagline(String tagline) {
           this.tagline = tagline;
       }

       public void setVideo(boolean video) {
           this.video = video;
       }

       public void setVote(double vote) {
           this.vote = vote;
       }

       public ArrayList<Genre> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<Genre> genre) {
        this.genre = genre;
    }

 }
