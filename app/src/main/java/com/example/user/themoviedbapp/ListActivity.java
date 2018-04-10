package com.example.user.themoviedbapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity implements MovieListFragment.UserSelectedCallback{

    private FragmentManager fragmentManager1;

    private RecyclerView recyclerView;
    static Genres genres;
    private ArrayList<Genre> genreList;
    private GenreListAdapter genreListAdapter;

    private TextView seeAllGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView=findViewById(R.id.genreRecyclerView);
        seeAllGenre=findViewById(R.id.see_all_genres);

        getLists();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IMDBapi imdBapi=retrofit.create(IMDBapi.class);

        Call<Genres> call3=imdBapi.getGenreLists();
        call3.enqueue(new Callback<Genres>() {
            @Override
            public void onResponse(Call<Genres> call, Response<Genres> response) {
                Genres body=response.body();
                if(body!=null){
                    genres=body;
                    if(genres.getGenresList()!=null){
                        genreList=genres.getGenresList();
                        genreListAdapter=new GenreListAdapter(ListActivity.this,genreList);
                        recyclerView.setAdapter(genreListAdapter);
                        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL));
                    }
                }
            }

            @Override
            public void onFailure(Call<Genres> call, Throwable t) {

            }
        });

        seeAllGenre.setOnClickListener(new View.OnClickListener() {  //see all genre textview click handle
            @Override
            public void onClick(View v) {
                if (genres!=null){
                    Intent intent=new Intent(ListActivity.this,GenreDetailActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void getLists() {
        MovieListFragment popularListFragment = new MovieListFragment();
        MovieListFragment nowPlayingFragment = new MovieListFragment();
        MovieListFragment topRatedFragment = new MovieListFragment();
        MovieListFragment upComingFragment = new MovieListFragment();

        Bundle bundle1=new Bundle();
        Bundle bundle2=new Bundle();
        Bundle bundle3=new Bundle();
        Bundle bundle4=new Bundle();

        bundle1.putString("type","popular");
        bundle2.putString("type","now_playing");
        bundle3.putString("type","top_rated");
        bundle4.putString("type","upcoming");

        popularListFragment.setArguments(bundle1);
        nowPlayingFragment.setArguments(bundle2);
        topRatedFragment.setArguments(bundle3);
        upComingFragment.setArguments(bundle4);

        fragmentManager1=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager1.beginTransaction();

        fragmentTransaction.replace(R.id.container1,popularListFragment);
        fragmentTransaction.replace(R.id.container2,nowPlayingFragment);
        fragmentTransaction.replace(R.id.container3,topRatedFragment);
        fragmentTransaction.replace(R.id.container4,upComingFragment).commit();

    }

    @Override
    public void onUserSelected(Movie movie) {
        Intent intent=new Intent(ListActivity.this,MovieDetailActivity.class);
        int id=movie.getId();
        intent.putExtra("id",id);
        startActivity(intent);
    }
}
