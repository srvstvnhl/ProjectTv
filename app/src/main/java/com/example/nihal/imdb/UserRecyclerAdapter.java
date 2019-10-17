 package com.example.nihal.imdb;

 import android.content.Context;
 import android.support.v7.widget.RecyclerView;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.ImageView;


 import com.squareup.picasso.Picasso;

 import java.util.ArrayList;



  public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder> {

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    OnItemClickListener listener;
    Context context;
    ArrayList<Movie> list;


    public UserRecyclerAdapter(Context context, ArrayList<Movie> list , OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener=listener;
    }

    @Override
    public UserRecyclerAdapter.UserViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.single_row_layout,parent,false);
        UserViewHolder holder=new UserViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final UserRecyclerAdapter.UserViewHolder holder, int position) {

     Movie detail = list.get(position);

    // holder.textview.setText(detail.getMovieName());


     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             listener.onItemClick(holder.getAdapterPosition());
         }
     });
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500"+detail.getPosterPath()).into(holder.imageview);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        View itemView;
       // TextView textview;
        ImageView imageview;

        public UserViewHolder(View itemView) {

            super(itemView);
           // textview = itemView.findViewById(R.id.textview);
            this.itemView = itemView;
            imageview = itemView.findViewById(R.id.imageview);
        }
    }
}