package com.example.user.themoviedbapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 3/31/2018.
 */

public class Genres {

    @SerializedName("genres")
    private ArrayList<Genre> genresList;

    public ArrayList<Genre> getGenresList() {
        return genresList;
    }

    public void setGenresList(ArrayList<Genre> genresList) {
        this.genresList = genresList;
    }
}
