package com.example.user.themoviedbapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 4/1/2018.
 */

public class GenreListAdapter extends RecyclerView.Adapter<GenreListAdapter.UserHolderView> {

    private Context context;
    private ArrayList<Genre> genresList;

    public GenreListAdapter(Context context,ArrayList<Genre> genresList){
        this.context=context;
        this.genresList=genresList;
    }

    @NonNull
    @Override
    public UserHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.genre_item,parent,false);
        UserHolderView userHolderView=new UserHolderView(view);
        return userHolderView;
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolderView holder, int position) {
        Genre genre=genresList.get(holder.getAdapterPosition());

        if(genre!=null){
            holder.genreName.setText(genre.getName());
        }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return genresList.size();
    }

    class UserHolderView extends RecyclerView.ViewHolder{

        TextView genreName;
        ImageView previewImage;
        View view;

        public UserHolderView(View itemView) {
            super(itemView);
            this.view=itemView;
            this.genreName=view.findViewById(R.id.genre_name);
            this.previewImage=view.findViewById(R.id.preview);
        }
    }
}
