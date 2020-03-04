package com.android.famousmovies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Hakimi on 4/3/2020.
 */
public class ReviewPageResult implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("page")
    private int page;
    @SerializedName("result")
    private ArrayList<Review> movieReview;

    public ReviewPageResult(int id, int page, ArrayList<Review> movieReview) {
        this.id = id;
        this.page = page;
        this.movieReview = movieReview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<Review> getMovieReview() {
        return movieReview;
    }

    public void setMovieReview(ArrayList<Review> movieReview) {
        this.movieReview = movieReview;
    }
}
