package com.example.nihal.imdb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class UserRecyclerImageAdapter extends RecyclerView.Adapter<UserRecyclerImageAdapter.UserViewHolder> {


  Context context;
  ArrayList<Image.backdrops> list;


    public UserRecyclerImageAdapter(Context context, ArrayList<Image.backdrops> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.single_image_layout,parent,false);

        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

       Image.backdrops details = list.get(position);

       String path = details.getImagePath();

        Picasso.with(context).load("https://image.tmdb.org/t/p/w500"+path).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        View itemView;

        public UserViewHolder(View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.image);
            this.itemView=itemView;

        }
    }
}
