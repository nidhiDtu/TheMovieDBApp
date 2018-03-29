package com.example.user.themoviedbapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by user on 3/27/2018.
 */

public class Images {

    class Backdrop{
        @SerializedName("file_path")
        private String backdroppath;

        public String getBackdroppath() {
            return backdroppath;
        }

        public void setBackdroppath(String backdroppath) {
            this.backdroppath = backdroppath;
        }
    }

    class Poster{
        @SerializedName("file_path")
        private String posterpath;

        public String getPosterpath() {
            return posterpath;
        }

        public void setPosterpath(String posterpath) {
            this.posterpath = posterpath;
        }
    }

    private int id;

    @SerializedName("backdrops")
    private ArrayList<Backdrop> backDrops;

    @SerializedName("posters")
    private ArrayList<Poster> posters;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Backdrop> getBackDrops() {
        return backDrops;
    }

    public ArrayList<Poster> getPosters() {
        return posters;
    }

    public void setPosters(ArrayList<Poster> posters) {
        this.posters = posters;
    }

    public void setBackDrops(ArrayList<Backdrop> backDrops) {
        this.backDrops = backDrops;
    }
}
