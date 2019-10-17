package com.example.nihal.imdb;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class UserCastRecyclerAdapter extends RecyclerView.Adapter<UserCastRecyclerAdapter.UserCastViewHolder>{

    interface OnItemClickListener{

        public void onItemClick(long id);
    }

    OnItemClickListener listener;


    Context context;
    ArrayList<TopCast.cast> list;

    public UserCastRecyclerAdapter(Context context, ArrayList<TopCast.cast> list,OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener=listener;
    }

    @Override
    public UserCastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.cast_layout,parent,false);

        UserCastViewHolder holder = new UserCastViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserCastViewHolder holder, final int position) {

        final TopCast.cast castList =  list.get(position);

        final long castid=castList.getId();

        String path = castList.getCastImage();

        holder.name.setText(castList.getName());
        holder.character.setText(castList.getCharacter());
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500" + path).into(holder.imageview);
        Log.d("TAGGER",path);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  Toast.makeText(context,"cast new acitivty detail should be opened",Toast.LENGTH_SHORT).show();

               // int position = holder.getAdapterPosition();
                listener.onItemClick(castid);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class UserCastViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        ImageView imageview;
        TextView name;
        TextView character;

        public UserCastViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            imageview=itemView.findViewById(R.id.castImage);
            name=itemView.findViewById(R.id.name);
            character=itemView.findViewById(R.id.character);
        }
    }
}
