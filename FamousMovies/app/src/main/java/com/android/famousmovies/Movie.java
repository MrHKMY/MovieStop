package com.android.famousmovies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
/**
 * Created by Hakimi on 22/2/2020.
 */
public class Movie implements Serializable {
    public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500/";

    @SerializedName("title")
    private String title;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("overview")
    private String overview;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("vote_average")
    private String vote;

    @SerializedName("name")
    private String tvShowName;

    public String getTvShowName() {
        return tvShowName;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @SerializedName("id")
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return TMDB_IMAGE_PATH + posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdropPath() {
        return TMDB_IMAGE_PATH + backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

}
