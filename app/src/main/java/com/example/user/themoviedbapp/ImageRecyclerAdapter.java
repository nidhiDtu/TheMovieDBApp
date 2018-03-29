package com.example.user.themoviedbapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by user on 3/27/2018.
 */

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.UserHolder>{
    private int id;
    private ArrayList<String> paths;
    private Context context;

    public ImageRecyclerAdapter(Context context,ArrayList<String> paths,int movieId){
        this.id=movieId;
        this.context=context;
        this.paths=paths;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.movie_row_detail,parent,false);
        UserHolder userHolder=new UserHolder(view);
        return userHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        String path=paths.get(holder.getAdapterPosition());
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+id+path).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return paths.size();
    }

    class UserHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        View view;

        public UserHolder(View itemView) {
            super(itemView);
            view=itemView;
            imageView=view.findViewById(R.id.poster);
        }
    }
}
