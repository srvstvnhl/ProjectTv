package com.example.nihal.imdb;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface Movie_Api {

     @GET("{type}?api_key=240b752ab9c0cb8011560c2f8338adc1")

     Call<PopularMovies> getPopularMovies(@Path("type") String type);
    // Call<Movie> getMovie();

    @GET("{ID}?api_key=240b752ab9c0cb8011560c2f8338adc1")
    Call<Movie> getMovieDetails(@Path("ID") Long idMovie);


    @GET("{ID}/images?api_key=240b752ab9c0cb8011560c2f8338adc1")
    Call<Image> getImagesOfMovie(@Path("ID") Long idMovie);


    @GET("{ID}/credits?api_key=240b752ab9c0cb8011560c2f8338adc1")
      Call<TopCast> getTopCast(@Path("ID") Long idMovie);


    @GET("{ID}?api_key=240b752ab9c0cb8011560c2f8338adc1")
    Call<Person> getPersonDetail(@Path("ID") Long personId);


    @GET("{ID}/images?api_key=240b752ab9c0cb8011560c2f8338adc1")
    Call<PersonImages> getPersonImages(@Path("ID") Long id);

   }
