package com.example.nihal.imdb;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class All_Movie extends AppCompatActivity implements ListFragment.UserSelectedCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__movie);
        getLists();
    }

    private void getLists() {

        ListFragment popularListFragment = new ListFragment();
        ListFragment nowPlayingFragment = new ListFragment();
        ListFragment topRatedFragment = new ListFragment();
        ListFragment  upcomingFragment = new ListFragment();


        Bundle popularBundle = new Bundle();
        Bundle nowPlayingBundle = new Bundle();
        Bundle topRatedBundle = new Bundle();
        Bundle upcomingBundle = new Bundle();


        popularBundle.putString("type","popular");
        nowPlayingBundle.putString("type","now_playing");
        topRatedBundle.putString("type","top_rated");
        upcomingBundle.putString("type","upcoming");


        popularListFragment.setArguments(popularBundle);
        nowPlayingFragment.setArguments(nowPlayingBundle);
        topRatedFragment.setArguments(topRatedBundle);
        upcomingFragment.setArguments(upcomingBundle);


        FragmentManager fragmentManager1 = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager1.beginTransaction();


        fragmentTransaction.replace(R.id.container1,popularListFragment);
        fragmentTransaction.replace(R.id.container2,nowPlayingFragment);
        fragmentTransaction.replace(R.id.container3,topRatedFragment);
        fragmentTransaction.replace(R.id.container4,upcomingFragment).commit();
    }

    @Override
    public void OnUserSelected(Movie movie) {

        long idMovie = movie.getId();
        Toast.makeText(All_Movie.this,"clicked on item",Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(All_Movie.this,Detail_Movie.class);
        intent.putExtra("ID",idMovie);
        startActivity(intent);
    }
}