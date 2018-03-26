package com.example.user.themoviedbapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment {

    public interface UserSelectedCallback{
        void onUserSelected(Movie movie);
    }

    UserSelectedCallback mCallback;

    private String type;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private UserRecyclerViewAdapter adapter;
    private ArrayList<Movie> movies=new ArrayList<>();

    public MovieListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        try {
            mCallback=(UserSelectedCallback)context;
        }catch (ClassCastException e){
            throw new ClassCastException("The parent activity should implement the listener interface..");
        }
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_movie_list, container, false);

        recyclerView=view.findViewById(R.id.recyclerview);
        progressBar=view.findViewById(R.id.progressbar);

        Bundle bundle=getArguments();

        type=bundle.getString("type");

        fetchList();

        adapter=new UserRecyclerViewAdapter(getContext(), movies, new UserRecyclerViewAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(int position) {
                Movie movie=movies.get(position);
                mCallback.onUserSelected(movie);
                Toast.makeText(getContext(),"Item Touch is handled !",Toast.LENGTH_LONG).show();
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,LinearLayoutManager.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Inflate the layout for this fragment
        return view;
    }

    private void fetchList() {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IMDBapi imdBapi=retrofit.create(IMDBapi.class);
        Call<ListMovies> call=imdBapi.getMoviesList(type);

        call.enqueue(new Callback<ListMovies>() {
            @Override
            public void onResponse(Call<ListMovies> call, Response<ListMovies> response) {
                ListMovies popularMovies=response.body();
                if(popularMovies!=null ){
                    if(popularMovies.getMovies()!=null){
                        movies.clear();
                        movies.addAll(popularMovies.getMovies());
                        adapter.notifyDataSetChanged();
                    }

                }
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ListMovies> call, Throwable t) {
                Toast.makeText(getContext(),"Item Touch is handled !",Toast.LENGTH_LONG).show();
            }
        });
    }

}
