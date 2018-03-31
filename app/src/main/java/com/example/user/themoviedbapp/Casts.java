package com.example.user.themoviedbapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by user on 3/29/2018.
 */

public class Casts {

    class Cast{
        private String character;
        private String name;

        @SerializedName("profile_path")
        private String profilePath;

        public String getName() {
            return name;
        }

        public String getCharacter() {
            return character;
        }

        public String getProfilePath() {
            return profilePath;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCharacter(String character) {
            this.character = character;
        }

        public void setProfilePath(String profilePath) {
            this.profilePath = profilePath;
        }
    }

    private int id;

    @SerializedName("cast")
    private ArrayList<Cast> castList;

    public int getId() {
        return id;
    }

    public ArrayList<Cast> getCastList() {
        return castList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCastList(ArrayList<Cast> castList) {
        this.castList = castList;
    }
}
