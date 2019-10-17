package com.example.nihal.imdb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Detail_Movie extends AppCompatActivity {

    TextView Tmoviename;
    TextView Toverview;

    TextView Ttagline;
    TextView Tbudget;
     TextView Tadult;
     TextView Tstatus;
     TextView Tvideo;
     TextView Tvote;
     TextView Tlanguage;
     TextView Trelease;

       ImageView TbackdropPath;
       ImageView TposterPath;
       Button load;

       long idMovie;
       Context context;

       Retrofit retrofit;
    Movie_Api movie_api;

       ArrayList<Image.backdrops> List = new ArrayList<>();
       ArrayList<TopCast.cast> ListCast = new ArrayList<>();

       RecyclerView recyclerview;
       UserRecyclerImageAdapter adapter;

        RecyclerView castRecycler;
        UserCastRecyclerAdapter castRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__movie);

        Intent intent=getIntent();
        idMovie=intent.getLongExtra("ID",0);

        castRecycler=findViewById(R.id.castRecycler);

        Tadult=findViewById(R.id.adult);
        Tstatus=findViewById(R.id.status);
        Tlanguage=findViewById(R.id.language);
        Trelease=findViewById(R.id.date);
        Tvote=findViewById(R.id.vote);
        Tvideo=findViewById(R.id.video);
        Tmoviename=findViewById(R.id.moviename);
        Toverview=findViewById(R.id.overview);
        Toverview.setMovementMethod(new ScrollingMovementMethod());
        Ttagline=findViewById(R.id.tagline);
        Tbudget=findViewById(R.id.budget);
       load=findViewById(R.id.loadMore);

        TbackdropPath=findViewById(R.id.backdrop);
        TposterPath=findViewById(R.id.posterpath);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) Toverview.getLayoutParams();
                layoutParams.height = 600;
                Toverview.setLayoutParams(layoutParams);
                Toast.makeText(Detail_Movie.this," load more clicked",Toast.LENGTH_SHORT).show();
                load.setText("");
            }
        });

        recyclerview=findViewById(R.id.imageList);

        adapter = new UserRecyclerImageAdapter(Detail_Movie.this , List);   // if contex is udes error occured
        recyclerview.setAdapter(adapter);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerview.addItemDecoration(new DividerItemDecoration(this , DividerItemDecoration.HORIZONTAL));

        castRecyclerAdapter = new UserCastRecyclerAdapter(Detail_Movie.this, ListCast, new UserCastRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(long castid) {
                Toast.makeText(Detail_Movie.this,"call control inside detail activity not on adapter and position is"+castid+"",Toast.LENGTH_SHORT).show();

                Intent intent1=new Intent(Detail_Movie.this,Person_cast.class);
                intent1.putExtra("person_id",castid);
                startActivity(intent1);
            }
        });
        castRecycler.setAdapter(castRecyclerAdapter);
        castRecycler.setItemAnimator(new DefaultItemAnimator());
        castRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        castRecycler.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));

        retrofitDeclaration();
    }


    private void retrofitDeclaration() {


        retrofit = new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        movie_api = retrofit.create(Movie_Api.class);

        fetchDetailsOfMOVIES();
        fetchImagesOfMovie();
        fetchCastDetails();

    }

    private void fetchDetailsOfMOVIES() {

        Call<Movie> call = movie_api.getMovieDetails(idMovie);
        call.enqueue(new Callback<Movie>() {
                     @Override
                     public void onResponse(Call<Movie> call, Response<Movie> response) {

                         Movie MovieDetail = response.body();
                         if(MovieDetail!=null)
                         {
                             // get all values

                            String moviename = MovieDetail.getMovieName();
                             String overview = MovieDetail.getOverview();
                             String tagline = MovieDetail.getTagline();
                             Long budget = MovieDetail.getBudget();
                           Boolean adult = MovieDetail.getAdult();
                             String status = MovieDetail.getStatus();
                             Boolean video = MovieDetail.getVideo();
                             double vote = MovieDetail.getVote();
                             String language = MovieDetail.getLanguage();
                             String release = MovieDetail.getRelease();


                             String backdropPath = MovieDetail.getBackdropPath();
                            String posterPath = MovieDetail.getPosterPath();

                         // set all values in views


                            Tstatus.setText(status);
                            Ttagline.setText(tagline);
                            Tvideo.setText(video+"");
                            Tlanguage.setText(language);
                            Tvote.setText(vote+"");
                            Trelease.setText(release);
                            Tadult.setText(adult+"");
                            Tbudget.setText(budget+"");
                            Tmoviename.setText(moviename);
                            Toverview.setText(overview);

                             Picasso.with(context).load("https://image.tmdb.org/t/p/w500/"+backdropPath).into(TbackdropPath);

                             Picasso.with(context).load("https://image.tmdb.org/t/p/w500/"+posterPath).into(TposterPath);

                             Toast.makeText(Detail_Movie.this, "Downloaded successfully", Toast.LENGTH_SHORT).show();
                         }
                         else{
                             Toast.makeText(Detail_Movie.this, "NULL !!!", Toast.LENGTH_SHORT).show();
                         }
                     }

                     @Override
                     public void onFailure(Call<Movie> call, Throwable t) {

                         Toast.makeText(Detail_Movie.this, "FAILED TO LOAD", Toast.LENGTH_SHORT).show();
                     }

                 });
    }

    private void fetchImagesOfMovie() {

        // retrofit already declared in common function

        Call<Image> call = movie_api.getImagesOfMovie(idMovie);
   call.enqueue(new Callback<Image>() {
       @Override
       public void onResponse(Call<Image> call, Response<Image> response) {

       Image object = response.body();
       if(object!=null) {
           ArrayList<Image.backdrops> list = object.getBackdrops();
           if(list!=null)
           {
               List.clear();
               List.addAll(list);
               adapter.notifyDataSetChanged();
               Toast.makeText(Detail_Movie.this,"IMAGES downloaded",Toast.LENGTH_SHORT).show();
           }
           else{
               Toast.makeText(Detail_Movie.this,"NULL !!!" , Toast.LENGTH_SHORT).show();
           }
         }
       }

       @Override
       public void onFailure(Call<Image> call, Throwable t) {
           Toast.makeText(Detail_Movie.this,"failed to load IMAGES",Toast.LENGTH_LONG).show();
       }
   });
    }

    private void fetchCastDetails() {

        Call<TopCast> call = movie_api.getTopCast(idMovie);
        call.enqueue(new Callback<TopCast>() {
            @Override
            public void onResponse(Call<TopCast> call, Response<TopCast> response) {

                TopCast object1 = response.body();
                if(object1!=null)
                {
                    ArrayList<TopCast.cast> castlist = object1.getList();
                    if(castlist!=null)
                    {
                        ListCast.clear();
                        ListCast.addAll(castlist);
                       // Log.d("TAG",response.body().cast.get(2).getCharacter());
                        castRecyclerAdapter.notifyDataSetChanged();
                        Toast.makeText(Detail_Movie.this, "CAST downloaded", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(Detail_Movie.this, "NULL !!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<TopCast> call, Throwable t) {

                Toast.makeText(Detail_Movie.this,"failed to download",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
