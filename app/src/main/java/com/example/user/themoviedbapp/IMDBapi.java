package com.example.user.themoviedbapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by user on 3/25/2018.
 */

public interface IMDBapi {
    @GET("{type}?api_key=8bfd44e46001a638f840aa0f6c5e5b7a&language=en-US&page=1")
    Call<ListMovies> getMoviesList(@Path("type") String type);

    @GET
    Call<Movie> getMovie();
}
