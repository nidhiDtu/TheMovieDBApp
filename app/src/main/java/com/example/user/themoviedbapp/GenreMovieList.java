package com.example.user.themoviedbapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by user on 4/1/2018.
 */

public class GenreMovieList {

    @SerializedName("id")
    private int genreId;

    @SerializedName("results")
    private ArrayList<Movie> movies;

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }
}
