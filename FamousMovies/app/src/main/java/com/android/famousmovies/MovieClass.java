package com.android.famousmovies;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Hakimi on 22/2/2020.
 */
    public class MovieClass {
        public static final String TMDB_IMAGE_PATH ="http://image.tmdb.org/t/p/w500/";

        private String title;

        @SerializedName("poster_path")
        private String poster;

        @SerializedName("overview")
        private String description;

        @SerializedName("backdrop_path")
        private String backdrop;


        private  ArrayList<MovieClass> movieResults;
        public MovieClass() {
            this.movieResults = movieResults;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPoster() {
            return TMDB_IMAGE_PATH + poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getBackdrop() {
            return TMDB_IMAGE_PATH + backdrop;
        }

        public void setBackdrop(String backdrop) {
            this.backdrop = backdrop;
        }

    public ArrayList<MovieClass> getMovieResult() {
        return movieResults;
    }

    public void setMovieResult(ArrayList<MovieClass> movieResult) {
        this.movieResults = movieResult;
    }

    }