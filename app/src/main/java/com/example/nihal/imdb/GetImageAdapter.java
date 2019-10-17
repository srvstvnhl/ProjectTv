package com.example.nihal.imdb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class GetImageAdapter extends RecyclerView.Adapter<GetImageAdapter.HolderView> {

    ArrayList<Profiles> list;
    Context context;

    public GetImageAdapter(ArrayList<Profiles> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.image_only,parent,false);

        HolderView holder = new HolderView(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderView holder, int position) {

        Profiles object = list.get(position);
        String path = object.getFile_path();
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500" + path).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Image clicked", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HolderView extends RecyclerView.ViewHolder {

        ImageView image;
        View view;

        public HolderView(View itemView) {

            super(itemView);
           this.view=itemView;
            image = itemView.findViewById(R.id.image);
        }
    }
}
