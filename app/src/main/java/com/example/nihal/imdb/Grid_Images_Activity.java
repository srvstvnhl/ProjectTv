package com.example.nihal.imdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Grid_Images_Activity extends AppCompatActivity {


    long id;
   RecyclerView recyclerView;
   GetImageAdapter adapter;

   ArrayList<Profiles> List = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid__images_);

        Intent data=getIntent();
        id=data.getLongExtra("id",0);

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new GetImageAdapter(List,this);
        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.addItemDecoration(new DividerItemDecoration(this , DividerItemDecoration.HORIZONTAL));


        RetroFunc();

    }

    private void RetroFunc() {

        Toast.makeText(Grid_Images_Activity.this,"id is : "+id,Toast.LENGTH_SHORT).show();


        Retrofit retrofit = new Retrofit.Builder().
                            addConverterFactory(GsonConverterFactory.create()).
                          baseUrl("https://api.themoviedb.org/3/person/")
                            .build();

           Movie_Api movie_api = retrofit.create(Movie_Api.class);

         Call<PersonImages> call = movie_api.getPersonImages(id);
           call.enqueue(new Callback<PersonImages>() {
               @Override
               public void onResponse(Call<PersonImages> call, Response<PersonImages> response) {

                   PersonImages object = response.body();
                   if(object!=null) {
                       ArrayList<Profiles> imageList = object.getProfileList();
                       if (imageList != null) {
                           List.clear();
                           List.addAll(imageList);
                           adapter.notifyDataSetChanged();
                           Toast.makeText(Grid_Images_Activity.this, "images downloaded", Toast.LENGTH_SHORT).show();
                       }
                   }
                       else{
                           Toast.makeText(Grid_Images_Activity.this, "NULL !!", Toast.LENGTH_SHORT).show();
                       }
               }

               @Override
               public void onFailure(Call<PersonImages> call, Throwable t) {
                   Toast.makeText(Grid_Images_Activity.this, "Failed to load !!!", Toast.LENGTH_SHORT).show();

               }
           });
    }
}
