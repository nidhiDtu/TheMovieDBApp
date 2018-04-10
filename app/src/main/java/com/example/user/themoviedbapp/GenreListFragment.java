package com.example.user.themoviedbapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class GenreListFragment extends Fragment {

    public interface UserSelectedCallback{
        void onUserSelected(Movie movie);
    }

    UserSelectedCallback mCallback;

    private int id;
    private RecyclerView recyclerView;
    private UserRecyclerViewAdapter adapter;
    private GenreMovieList genreMovieList;
    private ArrayList<Movie> movies;

    @Override
    public void onAttach(Context context) {
        try {
            mCallback=(UserSelectedCallback)context;
        }catch (ClassCastException e){
            throw new ClassCastException("The parent activity should implement the listener interface..");
        }
        super.onAttach(context);
    }

    public GenreListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_genre_list, container, false);

        recyclerView=view.findViewById(R.id.recyclerview);

        Bundle bundle=getArguments();
        id=bundle.getInt("id");

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IMDBapi imdBapi=retrofit.create(IMDBapi.class);
        Call<GenreMovieList> call=imdBapi.getGenreMovieList(id+"");

        call.enqueue(new Callback<GenreMovieList>() {
            @Override
            public void onResponse(Call<GenreMovieList> call, Response<GenreMovieList> response) {
                if(response.body()!=null){
                    genreMovieList=response.body();
                    if(genreMovieList.getMovies()!=null){
                        movies=genreMovieList.getMovies();

                        adapter=new UserRecyclerViewAdapter(getContext(), movies, new UserRecyclerViewAdapter.OnItemClickListner() {
                            @Override
                            public void onItemClick(int position) {
                                Movie movie=movies.get(position);
                                mCallback.onUserSelected(movie);
                            }
                        });

                        Log.d("1","SUCCESFUL FETCHING DONE");

                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL));
                    }
                }
            }

            @Override
            public void onFailure(Call<GenreMovieList> call, Throwable t) {

            }
        });

        return view;
    }

}
