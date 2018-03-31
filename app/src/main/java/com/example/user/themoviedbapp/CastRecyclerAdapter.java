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
 * Created by user on 3/30/2018.
 */

public class CastRecyclerAdapter extends RecyclerView.Adapter <CastRecyclerAdapter.UserViewHolder>{

    private ArrayList<Casts.Cast> list;
    private Context context;

    public CastRecyclerAdapter(Context context, ArrayList<Casts.Cast> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public CastRecyclerAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.cast_row_item,parent,false);
        UserViewHolder userViewHolder=new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder( CastRecyclerAdapter.UserViewHolder holder, int position) {
        Casts.Cast cast=list.get(holder.getAdapterPosition());

        if(cast.getProfilePath()!=null)
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+cast.getProfilePath()).into(holder.image);

        holder.character.setText(cast.getCharacter());
        holder.name.setText(cast.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        View view;
        TextView character;
        TextView name;
        ImageView image;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.view=itemView;
            this.image=itemView.findViewById(R.id.poster);
            this.character=itemView.findViewById(R.id.character);
            this.name=itemView.findViewById(R.id.name);
        }
    }
}
