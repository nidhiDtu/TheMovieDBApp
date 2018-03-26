package com.example.user.themoviedbapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by user on 3/25/2018.
 */

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder> {

    public interface OnItemClickListner{
        void onItemClick(int position);
    }

    private ArrayList<Movie> list;
    private Context context;
    private OnItemClickListner mListner;

    public UserRecyclerViewAdapter(Context context,ArrayList<Movie> list,OnItemClickListner mListner){
        this.context=context;
        this.list=list;
        this.mListner=mListner;
    }

    @Override
    public UserRecyclerViewAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.movie_row_detail,parent,false);
        UserViewHolder userViewHolder=new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, int position) {
        Movie movie=list.get(position);
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+movie.getPosterPath()).into(holder.poster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListner.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        ImageView poster;
        View itemView;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            poster=itemView.findViewById(R.id.poster);
        }
    }
}
