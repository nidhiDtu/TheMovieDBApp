package com.example.user.themoviedbapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailActivity extends Activity {

    private Movie movie;

    private ImageView backdrop;
    private ImageView poster;
    private TextView title;
    private TextView overView;
    private int id;

    private Images images;
    private RecyclerView imageListView;
    private ImageRecyclerAdapter adapter;
    private ArrayList<Images.Backdrop> backdrops=new ArrayList<>();
    private ArrayList<String> paths1=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        backdrop=findViewById(R.id.backdrop);
        poster=findViewById(R.id.poster);
        title=findViewById(R.id.title);
        overView=findViewById(R.id.overview);
        imageListView=findViewById(R.id.imageList);

        Intent intent=getIntent();
        id=intent.getIntExtra("id",-1);

        if(id!=-1){
            fetchMovie();
        }else{
            Toast.makeText(MovieDetailActivity.this,"Intent transfer is failed !",Toast.LENGTH_LONG).show();
        }
    }

    private void fetchMovie() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IMDBapi imdBapi=retrofit.create(IMDBapi.class);
        Call<Movie> call=imdBapi.getMovie(id+"");

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie1=response.body();
                if(movie1!=null){
                    movie=movie1;
                    Picasso.get().load("https://image.tmdb.org/t/p/w500/"+movie.getBackdropPath()).into(backdrop);
                    Picasso.get().load("https://image.tmdb.org/t/p/w500/"+movie.getPosterPath()).into(poster);
                    title.setText(movie.getMovieName());
                    overView.setText("  Overview :  "+movie.getOverView());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(MovieDetailActivity.this,"Failure",Toast.LENGTH_LONG).show();
            }
        });

        Call<Images> call1=imdBapi.getImages(id+"");
        call1.enqueue(new Callback<Images>() {
            @Override
            public void onResponse(Call<Images> call, Response<Images> response) {
                images=response.body();
                if(images!=null){
                    backdrops=images.getBackDrops();
                    getListViewDone();
                }
            }

            @Override
            public void onFailure(Call<Images> call, Throwable t) {
                Toast.makeText(MovieDetailActivity.this,"Failure",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getListViewDone() {
        paths1.clear();

        for(int i=0;i<backdrops.size();i++){
            paths1.add(backdrops.get(i).getBackdroppath());
        }
        adapter=new ImageRecyclerAdapter(MovieDetailActivity.this,paths1,id);
        imageListView.setAdapter(adapter);
        imageListView.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL));
        adapter.notifyDataSetChanged();
        imageListView.setVisibility(View.VISIBLE);
    }
}
