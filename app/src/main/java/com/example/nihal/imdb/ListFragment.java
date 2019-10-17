package com.example.nihal.imdb;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */

public class ListFragment extends Fragment {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    UserRecyclerAdapter recyclerAdapter;
    ArrayList<Movie> detaillist= new ArrayList<>();
    String type;


     public interface UserSelectedCallBack
     {
         void OnUserSelected(Movie movie);
     }

   UserSelectedCallBack mCallBack;


    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mCallBack = (UserSelectedCallBack)context;
        }catch(ClassCastException e){
           throw new ClassCastException("class should implement method");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View row= inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = row.findViewById(R.id.recyclerview);
         progressBar= row.findViewById(R.id.pg);


        Bundle bundle=getArguments();

        type=bundle.getString("type");

        FetchPopularMovies();

        recyclerAdapter=new UserRecyclerAdapter(getContext(), detaillist, new UserRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Movie movie=detaillist.get(position);
                mCallBack.OnUserSelected(movie);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerAdapter);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));
        return row;

    }

    private void FetchPopularMovies() {

        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);


        Retrofit retrofit = new Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create()).
                baseUrl("https://api.themoviedb.org/3/movie/").
                build();

        Movie_Api movie_api = retrofit.create(Movie_Api.class);

        Call<PopularMovies> call=movie_api.getPopularMovies(type);

        call.enqueue(new Callback<PopularMovies>() {
            @Override

            public void onResponse(Call<PopularMovies> call, Response<PopularMovies> response) {
                PopularMovies popularMovies=response.body();
                if(popularMovies!= null){
                    ArrayList<Movie> Movieslist=popularMovies.getMovies();
                    if(Movieslist!=null){
                        detaillist.clear();
                        detaillist.addAll(Movieslist);
                        recyclerAdapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Download successfull", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(getContext(),"Null !",Toast.LENGTH_LONG).show();
                    }
                }
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFailure(Call<PopularMovies> call, Throwable t) {

                Toast.makeText(getContext(),"failed to load",Toast.LENGTH_LONG).show();
            }
        });
    }
}