package com.example.user.themoviedbapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by user on 3/25/2018.
 */

public class Movie {

    class Genre{
        int id;
        String name;

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    private int id;

    @SerializedName("original_title")
    private String movieName;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("overview")
    private String overView;

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    private ArrayList<Genre> genres;

    public int getId() {
        return id;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }
}
