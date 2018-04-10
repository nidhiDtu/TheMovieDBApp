package com.example.user.themoviedbapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by user on 3/25/2018.
 */

public interface IMDBapi {

    @GET("{type}?api_key=8bfd44e46001a638f840aa0f6c5e5b7a")
    Call<ListMovies> getMoviesList(@Path("type") String type);

    @GET("{id}?api_key=8bfd44e46001a638f840aa0f6c5e5b7a")
    Call<Movie> getMovie(@Path("id") String id);

    @GET("{id}/images?api_key=8bfd44e46001a638f840aa0f6c5e5b7a")
    Call<Images> getImages(@Path("id") String id);

    @GET("{id}/credits?api_key=8bfd44e46001a638f840aa0f6c5e5b7a")
    Call<Casts> getCasts(@Path("id") String id);

    @GET("https://api.themoviedb.org/3/genre/movie/list?api_key=8bfd44e46001a638f840aa0f6c5e5b7a")
    Call<Genres> getGenreLists();

    @GET("genre/{genre_id}/movies?api_key=8bfd44e46001a638f840aa0f6c5e5b7a&sort_by=created_at.asc\n")
    Call<GenreMovieList> getGenreMovieList(@Path("genre_id") String id);
}
