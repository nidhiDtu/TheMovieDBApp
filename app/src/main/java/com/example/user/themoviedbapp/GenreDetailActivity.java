package com.example.user.themoviedbapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class GenreDetailActivity extends AppCompatActivity implements GenreListFragment.UserSelectedCallback{

    private Genres genres;
    private ArrayList<Genre> genreArrayList=new ArrayList<>();
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_detail);

        Intent intent=getIntent();

        genres=ListActivity.genres;

        if(genres!=null){
            genreArrayList=genres.getGenresList();
            fetchMoviesLists();
        }else{
            Toast.makeText(GenreDetailActivity.this,"Genre List is Empty!",Toast.LENGTH_LONG).show();
        }
    }

    private void fetchMoviesLists() {

        fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        for(int i=0;i<genreArrayList.size();i++){
            Genre genre=genreArrayList.get(i);
            if(genre!=null){
                int id=genre.getId();

                GenreListFragment fragment=new GenreListFragment();
                Bundle bundle=new Bundle();
                bundle.putInt("id",id);

                fragment.setArguments(bundle);

                fragmentTransaction.add(R.id.container1,fragment);
            }
        }
    }

    @Override
    public void onUserSelected(Movie movie) {

    }
}
