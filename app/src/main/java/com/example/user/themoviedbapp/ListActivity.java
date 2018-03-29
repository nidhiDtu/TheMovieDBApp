package com.example.user.themoviedbapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListActivity extends AppCompatActivity implements MovieListFragment.UserSelectedCallback{

    private FragmentManager fragmentManager1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getLists();
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
